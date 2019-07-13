package com.mjr.twitchframework.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mjr.twitchframework.Event;

public class TwitchPubSubManager {

	private static List<TwitchWebsocketClient> clients = new ArrayList<TwitchWebsocketClient>();
	private static HashMap<Event, TwitchWebsocketClient> listeners = new HashMap<Event, TwitchWebsocketClient>();

	public static List<TwitchWebsocketClient> getClients() {
		return clients;
	}

	public static void setClients(List<TwitchWebsocketClient> clients) {
		TwitchPubSubManager.clients = clients;
	}

	public static void addClient(TwitchWebsocketClient client) {
		TwitchPubSubManager.clients.add(client);
	}

	public static void registerEventHandler(TwitchWebsocketClient client, Event event) {
		TwitchPubSubManager.listeners.put(event, client);
	}

	public static HashMap<Event, TwitchWebsocketClient> getEventListeners() {
		return listeners;
	}

	public static void tick() {
		for (TwitchWebsocketClient client : clients) {
			if (client.isOpen())
				client.sendPingMessage();
		}
	}
}
