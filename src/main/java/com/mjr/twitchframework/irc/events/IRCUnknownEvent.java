package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCUnknownEvent extends Event {
	
	public final String rawLine;
	public final String channel;

	public IRCUnknownEvent(String rawLine, String channel) {
		super(IRCEventType.UNKNOWN);
		this.rawLine = rawLine;
		this.channel = channel;
	}

	public IRCUnknownEvent() {
		super(IRCEventType.UNKNOWN);
		this.rawLine = null;
		this.channel = null;
	}
	
	public void onEvent(IRCUnknownEvent event) {
		
	}
}
