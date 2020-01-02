package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubInfoEvent extends Event {

	public final String message;

	public PubSubInfoEvent(String message, TwitchWebsocketClient client) {
		super(PubSubEventType.INFO, client);
		this.message = message;
	}

	public PubSubInfoEvent() {
		super(PubSubEventType.INFO);
		this.message = null;
	}

	public void onEvent(PubSubInfoEvent event) {

	}
}
