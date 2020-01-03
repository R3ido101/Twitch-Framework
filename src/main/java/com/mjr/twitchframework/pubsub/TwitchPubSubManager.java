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

	public static void setClients(List<TwitchWebsocketClient> clients) {
		TwitchPubSubManager.clients = clients;
	}

	public static void addClient(TwitchWebsocketClient client) {
		TwitchPubSubManager.clients.add(client);
	}
	
	public static void reconnectClient(final TwitchWebsocketClient client) {
		Thread temp = new Thread() {
			@Override
			public void run() {
				try {
					client.reconnectClient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		temp.start();
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
		if(tickThread != null)
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
