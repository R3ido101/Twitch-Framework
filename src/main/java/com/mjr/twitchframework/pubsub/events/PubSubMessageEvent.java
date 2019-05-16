package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;

public class PubSubMessageEvent extends Event {

	public PubSubMessageEvent() {
		super(PubSubEventType.MESSAGE);
	}
	
	public void onEvent(String message) {
		
	}
}
