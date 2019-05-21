package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCPrivateMessageEvent extends Event {

	public IRCPrivateMessageEvent() {
		super(IRCEventType.PRIVATEMESSAGE);
	}
	
	public void onEvent(final String sender, final String login, final String hostname, final String channel, final String message) {
		
	}
}
