package com.mjr.twitchframework.pubsub;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.PubSubEventType;
import com.mjr.twitchframework.pubsub.events.PubSubConnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubDisconnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubErrorEvent;
import com.mjr.twitchframework.pubsub.events.PubSubMessageEvent;

public class TwitchPubSubEventHooks {

	public static void triggerMessageEvent(TwitchWebsocketClient client, String message) {
		for (Event event : TwitchPubSubManager.getEventListeners().keySet()) {
			if (PubSubEventType.MESSAGE.getName().equalsIgnoreCase(event.type.getName()) && client.equals(TwitchPubSubManager.getEventListeners().get(event)))
				((PubSubMessageEvent) event).onEvent(new PubSubMessageEvent(message));
		}
	}

	public static void triggerErrorEvent(TwitchWebsocketClient client, Exception error) {
		for (Event event : TwitchPubSubManager.getEventListeners().keySet()) {
			if (PubSubEventType.ERROR.getName().equalsIgnoreCase(event.type.getName()) && client.equals(TwitchPubSubManager.getEventListeners().get(event)))
				((PubSubErrorEvent) event).onEvent(new PubSubErrorEvent(error));
		}
	}

	public static void triggerConnectEvent(TwitchWebsocketClient client, ServerHandshake data) {
		for (Event event : TwitchPubSubManager.getEventListeners().keySet()) {
			if (PubSubEventType.CONNECT.getName().equalsIgnoreCase(event.type.getName()) && client.equals(TwitchPubSubManager.getEventListeners().get(event)))
				((PubSubConnectEvent) event).onEvent(new PubSubConnectEvent(data));
		}
	}

	public static void triggerDisconnectEvent(TwitchWebsocketClient client, int codes, String message, boolean byRemoteHost) {
		for (Event event : TwitchPubSubManager.getEventListeners().keySet()) {
			if (PubSubEventType.DISCONNECT.getName().equalsIgnoreCase(event.type.getName()) && client.equals(TwitchPubSubManager.getEventListeners().get(event)))
				((PubSubDisconnectEvent) event).onEvent(new PubSubDisconnectEvent(codes, message, byRemoteHost));
		}
	}

}
