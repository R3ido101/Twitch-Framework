package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;

public class PubSubErrorEvent extends Event {

	public final Exception e;

	public PubSubErrorEvent(Exception e) {
		super(PubSubEventType.ERROR);
		this.e = e;
	}

	public PubSubErrorEvent() {
		super(PubSubEventType.ERROR);
		this.e = null;
	}

	public void onEvent(PubSubErrorEvent event) {

	}
}
