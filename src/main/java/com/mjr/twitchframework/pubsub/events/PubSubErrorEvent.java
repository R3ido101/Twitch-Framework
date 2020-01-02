package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubErrorEvent extends Event {

	public final String errorMessage;
	public final Throwable error;

	public PubSubErrorEvent(String errorMessage, Throwable error, TwitchWebsocketClient client) {
		super(PubSubEventType.ERROR, client);
		this.errorMessage = errorMessage;
		this.error = error;
	}

	public PubSubErrorEvent() {
		super(PubSubEventType.ERROR);
		this.errorMessage = null;
		this.error = null;
	}

	public void onEvent(PubSubErrorEvent event) {

	}
}
