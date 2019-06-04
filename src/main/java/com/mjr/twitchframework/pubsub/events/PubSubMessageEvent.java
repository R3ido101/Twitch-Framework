package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;

public class PubSubMessageEvent extends Event {
	
	public final String message;

	public PubSubMessageEvent(String message) {
		super(PubSubEventType.MESSAGE);
		this.message = message;
	}

	public PubSubMessageEvent() {
		super(PubSubEventType.MESSAGE);
		this.message = null;
	}
	
	public void onEvent(PubSubMessageEvent event) {
		
	}
}
