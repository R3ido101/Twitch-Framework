package com.mjr.twitchframework.pubsub.events;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubConnectEvent extends Event {

	public final ServerHandshake serverHandshake;

	public PubSubConnectEvent(ServerHandshake serverHandshake, TwitchWebsocketClient client) {
		super(PubSubEventType.CONNECT, client);
		this.serverHandshake = serverHandshake;
	}

	public PubSubConnectEvent() {
		super(PubSubEventType.CONNECT);
		this.serverHandshake = null;
	}

	public void onEvent(PubSubConnectEvent event) {

	}
}
