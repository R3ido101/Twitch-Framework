package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCUnknownEvent extends Event {

	public IRCUnknownEvent() {
		super(IRCEventType.UNKNOWN);
	}
	
	public void onEvent(final String rawLine, final String channel) {
		
	}
}
