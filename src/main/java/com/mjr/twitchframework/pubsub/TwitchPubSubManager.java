package com.mjr.twitchframework.pubsub;

import java.util.ArrayList;
import java.util.List;

import com.mjr.twitchframework.Event;

public class TwitchPubSubManager {

	private static List<TwitchWebsocketClient> clients = new ArrayList<TwitchWebsocketClient>();
	private static List<Event> listeners = new ArrayList<Event>();

	private static Thread tickThread;

	public static List<TwitchWebsocketClient> getClients() {
		return clients;
	}

	public static TwitchWebsocketClient getClientByChannelID(int channelID) {
		for(TwitchWebsocketClient client : clients)
			if(client.getChannelID() == channelID)
				return client;
		return null;
	}

	public static void setClients(List<TwitchWebsocketClient> clients) {
		TwitchPubSubManager.clients = clients;
	}

	public static void addClient(TwitchWebsocketClient client) {
		TwitchPubSubManager.clients.add(client);
	}

	public static void removeClient(TwitchWebsocketClient client) {
		try {
			client.close(1000, "Requested Forced Close");
			TwitchPubSubManager.clients.remove(client);
		} catch (Exception e) {
			TwitchPubSubEventHooks.triggerErrorEvent(client, "Error when disconnecting/removing client", e);
		}
	}

	public static void registerEventHandler(Event event) {
		TwitchPubSubManager.listeners.add(event);
	}

	public static List<Event> getEventListeners() {
		return listeners;
	}

	private static void tick() {
		for (TwitchWebsocketClient client : clients) {
			if (client.isOpen())
				client.sendPingMessage();
		}
	}

	public static void startTickTimerIfNotAlready() {
		if (tickThread != null)
			return;
		try {
			tickThread = new Thread("TwitchPubSubManager Tick Ping Thread") {
				@Override
				public void run() {
					while (true) {
						tick();
						try {
							Thread.sleep(240000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			tickThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
