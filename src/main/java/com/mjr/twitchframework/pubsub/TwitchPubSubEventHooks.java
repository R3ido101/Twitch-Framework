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
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.MESSAGE.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubMessageEvent) event).onEvent(new PubSubMessageEvent(message, client));
		}
	}

	public static void triggerErrorEvent(TwitchWebsocketClient client, Exception error) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.ERROR.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubErrorEvent) event).onEvent(new PubSubErrorEvent(error, client));
		}
	}

	public static void triggerConnectEvent(TwitchWebsocketClient client, ServerHandshake data) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.CONNECT.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubConnectEvent) event).onEvent(new PubSubConnectEvent(data, client));
		}
	}

	public static void triggerDisconnectEvent(TwitchWebsocketClient client, int codes, String message, boolean byRemoteHost) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.DISCONNECT.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubDisconnectEvent) event).onEvent(new PubSubDisconnectEvent(codes, message, byRemoteHost, client));
		}
	}

}
