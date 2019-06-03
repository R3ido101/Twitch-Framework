package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCInfoEvent extends Event {
	public IRCInfoEvent() {
		super(IRCEventType.INFOMSG);
	}

	public void onEvent(String message) {

	}
}
