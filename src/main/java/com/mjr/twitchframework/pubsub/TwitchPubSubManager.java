package com.mjr.twitchframework.pubsub;

import java.util.ArrayList;
import java.util.List;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.PubSubEventType;
import com.mjr.twitchframework.pubsub.events.PubSubConnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubDisconnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubErrorEvent;
import com.mjr.twitchframework.pubsub.events.PubSubMessageEvent;

public class TwitchPubSubManager {

	private static List<TwitchWebsocketClient> clients = new ArrayList<TwitchWebsocketClient>();
	private static List<Event> listeners = new ArrayList<Event>();

	public static List<TwitchWebsocketClient> getClients() {
		return clients;
	}

	public static void setClients(List<TwitchWebsocketClient> clients) {
		TwitchPubSubManager.clients = clients;
	}

	public static void addClient(TwitchWebsocketClient client) {
		TwitchPubSubManager.clients.add(client);
	}

	public static void addListener(Event event) {
		listeners.add(event);
	}

	public static void triggerMessageEvent(String message) {
		for (Event event : listeners) {
			if (PubSubEventType.MESSAGE.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubMessageEvent) event).onEvent(new PubSubMessageEvent(message));
		}
	}

	public static void triggerErrorEvent(Exception error) {
		for (Event event : listeners) {
			if (PubSubEventType.ERROR.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubErrorEvent) event).onEvent(new PubSubErrorEvent(error));
		}
	}

	public static void triggerConnectEvent(ServerHandshake data) {
		for (Event event : listeners) {
			if (PubSubEventType.CONNECT.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubConnectEvent) event).onEvent(new PubSubConnectEvent(data));
		}
	}

	public static void triggerDisconnectEvent(int codes, String message, boolean byRemoteHost) {
		for (Event event : listeners) {
			if (PubSubEventType.DISCONNECT.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubDisconnectEvent) event).onEvent(new PubSubDisconnectEvent(codes, message, byRemoteHost));
		}
	}

	public static void tick() {
		for (TwitchWebsocketClient client : clients) {
			if (client.isOpen())
				client.sendPingMessage();
		}
	}
}
