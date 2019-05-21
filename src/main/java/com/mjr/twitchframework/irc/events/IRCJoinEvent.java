package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCJoinEvent extends Event {

	public IRCJoinEvent() {
		super(IRCEventType.JOIN);
	}
	
	public void onEvent(final String channel, final String sender, final String login, final String hostname) {
		
	}
}
