package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCInfoEvent extends Event {
	public String message;

	public IRCInfoEvent(String message) {
		super(IRCEventType.INFOMSG);
		this.message = message;
	}

	public IRCInfoEvent() {
		super(IRCEventType.INFOMSG);
	}

	public void onEvent(IRCInfoEvent event) {

	}
}
