package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCMessageExtraEvent extends Event {

	public IRCMessageExtraEvent() {
		super(IRCEventType.MESSAGEEXTRA);
	}
	
	public void onEvent(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		
	}
}
