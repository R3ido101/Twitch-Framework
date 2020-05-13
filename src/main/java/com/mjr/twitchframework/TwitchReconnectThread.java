package com.mjr.twitchframework;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mjr.twitchframework.irc.TwitchIRCClient;
import com.mjr.twitchframework.irc.TwitchIRCEventHooks;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class TwitchReconnectThread extends Thread {
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
		while (true) {
			try {
				if (twitchClients.size() != 0) {
					Iterator<TwitchIRCClient> iterator = twitchClients.iterator();
					while (iterator.hasNext()) {
						TwitchIRCClient client = iterator.next();
						TwitchIRCEventHooks.triggerOnInfoEvent("Reconnecting IRC client, Client ID: " + client.getID());
						client.reconnect();
						Thread.sleep(twitchClientIRCSleepTime * 1000);
						if (client.isClientConnected()) {
							twitchClients.remove(client);
							TwitchIRCEventHooks.triggerOnInfoEvent("Reconnected IRC client, Client ID: " + client.getID());
							client.requestCapabilities();
							client.connectToChannels();
							TwitchIRCEventHooks.triggerOnInfoEvent("Joining back channels for IRC client, Client ID: " + client.getID());
						}
					}
				}
				if (twitchPubSubs.size() != 0) {
					Iterator<TwitchWebsocketClient> iterator = twitchPubSubs.iterator();
					while (iterator.hasNext()) {
						TwitchWebsocketClient client = iterator.next();
						client.reconnectClient();
						TwitchIRCEventHooks.triggerOnInfoEvent("Reconnecting PubSub client, Channel ID: " + client.getChannelID());
						Thread.sleep(twitchClientPubSubSleepTime * 1000);
						if (client.isOpen()) {
							twitchPubSubs.remove(client);
							TwitchIRCEventHooks.triggerOnInfoEvent("Reconnected PubSub client, Channel ID: " + client.getChannelID());
						}
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
