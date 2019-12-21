package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubMessageEvent extends Event {

	public final String message;

	public PubSubMessageEvent(String message, TwitchWebsocketClient client) {
		super(PubSubEventType.MESSAGE, client);
		this.message = message;
	}

	public PubSubMessageEvent() {
		super(PubSubEventType.MESSAGE);
		this.message = null;
	}

	public void onEvent(PubSubMessageEvent event) {

	}
}
