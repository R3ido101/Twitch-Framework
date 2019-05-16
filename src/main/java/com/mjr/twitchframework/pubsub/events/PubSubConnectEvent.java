package com.mjr.twitchframework.pubsub.events;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.Event;

public class PubSubConnectEvent extends Event {

	public PubSubConnectEvent() {
		super(PubSubEventType.CONNECT);
	}
	
	public void onEvent(ServerHandshake serverHandshake) {
		
	}
}
