package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCPartEvent extends Event {

	public IRCPartEvent() {
		super(IRCEventType.PART);
	}
	
	public void onEvent(final String channel, final String sender, final String login, final String hostname) {
		
	}
}
