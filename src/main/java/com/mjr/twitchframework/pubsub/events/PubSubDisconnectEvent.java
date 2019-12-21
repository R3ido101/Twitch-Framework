package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubDisconnectEvent extends Event {

	public final int codes;
	public final String message;
	public final boolean byRemoteHost;

	public PubSubDisconnectEvent(int codes, String message, boolean byRemoteHost, TwitchWebsocketClient client) {
		super(PubSubEventType.DISCONNECT, client);
		this.codes = codes;
		this.message = message;
		this.byRemoteHost = byRemoteHost;
	}

	public PubSubDisconnectEvent() {
		super(PubSubEventType.DISCONNECT);
		this.codes = -1;
		this.message = null;
		this.byRemoteHost = false;
	}

	public void onEvent(PubSubDisconnectEvent pubSubDisconnectEvent) {

	}

}
