package com.mjr.twitchframework.pubsub.events;

import com.mjr.twitchframework.Event;

public class PubSubErrorEvent extends Event {

	public PubSubErrorEvent() {
		super(PubSubEventType.ERROR);
	}
	
	public void onEvent(Exception e) {
		
	}
}
