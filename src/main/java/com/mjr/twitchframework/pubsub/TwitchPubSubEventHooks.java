package com.mjr.twitchframework.pubsub;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.PubSubEventType;
import com.mjr.twitchframework.pubsub.events.*;

public class TwitchPubSubEventHooks {

	public static void triggerMessageEvent(TwitchWebsocketClient client, String message) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.MESSAGE.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubMessageEvent) event).onEvent(new PubSubMessageEvent(message, client));
		}
	}

	public static void triggerErrorEvent(TwitchWebsocketClient client, String errorMessage, Throwable error) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.ERROR.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubErrorEvent) event).onEvent(new PubSubErrorEvent(errorMessage, error, client));
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

	public static void triggerInfoEvent(TwitchWebsocketClient client, String message) {
		for (Event event : TwitchPubSubManager.getEventListeners()) {
			if (PubSubEventType.INFO.getName().equalsIgnoreCase(event.type.getName()))
				((PubSubInfoEvent) event).onEvent(new PubSubInfoEvent(message, client));
		}
	}

}
