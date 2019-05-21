package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCNoticeEvent extends Event {

	public IRCNoticeEvent() {
		super(IRCEventType.NOTICE);
	}
	
	public void onEvent(final String sourceNick, final String sourceLogin, final String sourceHostname, final String target, final String notice) {
		
	}
}
