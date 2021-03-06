package com.mjr.twitchframework;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import com.mjr.twitchframework.irc.TwitchIRCClient;
import com.mjr.twitchframework.irc.TwitchIRCEventHooks;
import com.mjr.twitchframework.pubsub.TwitchPubSubEventHooks;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;
import com.mjr.twitchframework.util.BasicKillableThread;

public class TwitchReconnectThread extends BasicKillableThread {
	private CopyOnWriteArrayList<TwitchIRCClient> twitchClients = new CopyOnWriteArrayList<TwitchIRCClient>();
	private CopyOnWriteArrayList<TwitchWebsocketClient> twitchPubSubs = new CopyOnWriteArrayList<TwitchWebsocketClient>();

	private int twitchClientIRCSleepTime;
	private int twitchClientPubSubSleepTime;

	public TwitchReconnectThread(int twitchClientIRCSleepTime, int twitchClientPubSubSleepTime) {
		super("Twitch Framework Reconnect Thread");
		this.twitchClientIRCSleepTime = twitchClientIRCSleepTime;
		this.twitchClientPubSubSleepTime = twitchClientPubSubSleepTime;
	}

	@Override
	public void run() {
		while (!this.isKillThread()) {
			try {
				ExecutorService threadPool = Executors.newCachedThreadPool();
				int attempt = 0;
				if (twitchClients.size() != 0) {
					Iterator<TwitchIRCClient> iterator = twitchClients.iterator();
					while (iterator.hasNext()) {
						TwitchIRCClient client = iterator.next();
						do {
							attempt = attempt + 1;
							TwitchIRCEventHooks.triggerOnInfoEvent("Reconnecting IRC client, Client ID: " + client.getID());
							Callable<Boolean> callable = () -> {
								client.reconnect();
								return true;
							};
							Future<Boolean> future = threadPool.submit(callable);
							try {
								future.get(30, TimeUnit.SECONDS);
							} catch (TimeoutException e) {
								TwitchIRCEventHooks.triggerOnErrorEvent("[Twitch Framework IRC Reconnect] Timeout", e);
								TwitchIRCEventHooks.triggerOnInfoEvent("[Twitch Framework IRC Reconnect] IRC reconnect has timed out for taking to long will skip and retry! Attempt " + attempt);
								if (!client.isClientConnected())
									client.disconnect();
							}
							Thread.sleep(twitchClientIRCSleepTime * 1000);
							if (client.isClientConnected()) {
								twitchClients.remove(client);
								TwitchIRCEventHooks.triggerOnInfoEvent("Reconnected IRC client, Client ID: " + client.getID());
								client.requestCapabilities();
								client.connectToChannels();
								TwitchIRCEventHooks.triggerOnInfoEvent("Joining back channels for IRC client, Client ID: " + client.getID());
							}
						} while (!client.isClientConnected() && attempt < 10);
						attempt = 0;
					}
				}
				if (twitchPubSubs.size() != 0) {
					Iterator<TwitchWebsocketClient> iterator = twitchPubSubs.iterator();
					while (iterator.hasNext()) {
						TwitchWebsocketClient client = iterator.next();
						do {
							attempt = attempt + 1;
							TwitchPubSubEventHooks.triggerInfoEvent(client,"Reconnecting PubSub client, Channel ID: " + client.getChannelID());
							Callable<Boolean> callable = () -> {
								client.reconnectClient();
								return true;
							};
							Future<Boolean> future = threadPool.submit(callable);
							try {
								future.get(30, TimeUnit.SECONDS);
							} catch (TimeoutException e) {
								TwitchPubSubEventHooks.triggerErrorEvent(client,"[Twitch Framework PubSub Reconnect] Timeout", e);
								TwitchPubSubEventHooks.triggerInfoEvent(client,"[Twitch Framework PubSub Reconnect] PubSub reconnect has timed out for taking to long will skip and retry! Attempt " + attempt);
								if (!client.isOpen())
									client.closeBlocking();
							}
							Thread.sleep(twitchClientPubSubSleepTime * 1000);
							if (client.isOpen()) {
								twitchPubSubs.remove(client);
								TwitchPubSubEventHooks.triggerInfoEvent(client,"Reconnected PubSub client, Channel ID: " + client.getChannelID());
							}
						} while (!client.isOpen() && attempt < 10);
						attempt = 0;
					}
				}
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
					TwitchIRCEventHooks.triggerOnErrorEvent("Twitch Framework: ReconnectThread Errored", e);
				}
			} catch (Exception e) {
				TwitchIRCEventHooks.triggerOnErrorEvent("Twitch Framework: ReconnectThread Errored", e);
			}
		}

	}

	public List<TwitchIRCClient> getTwitchClients() {
		return twitchClients;
	}

	public List<TwitchWebsocketClient> getTwitchPubSubs() {
		return twitchPubSubs;
	}

	public void addTwitchIRCClient(TwitchIRCClient client) {
		this.twitchClients.add(client);
	}

	public void addTwitchPubSubClient(TwitchWebsocketClient client) {
		this.twitchPubSubs.add(client);
	}

	public int getTwitchClientIRCSleepTime() {
		return twitchClientIRCSleepTime;
	}

	public int getTwitchClientPubSubSleepTime() {
		return twitchClientPubSubSleepTime;
	}
}
