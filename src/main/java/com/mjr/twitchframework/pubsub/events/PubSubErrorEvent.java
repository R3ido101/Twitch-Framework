package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;

public class PubSubErrorEvent extends Event {

	public final Exception e;

	public PubSubErrorEvent(Exception e, TwitchWebsocketClient client) {
		super(PubSubEventType.ERROR, client);
		this.e = e;
	}

	public PubSubErrorEvent() {
		super(PubSubEventType.ERROR);
		this.e = null;
	}

	public void onEvent(PubSubErrorEvent event) {

	}
}
