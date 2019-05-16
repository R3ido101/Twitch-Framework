package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCMessageEvent extends Event {

	public IRCMessageEvent() {
		super(IRCEventType.MESSAGE);
	}
	
	public void onEvent(final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message) {
		
	}
}
