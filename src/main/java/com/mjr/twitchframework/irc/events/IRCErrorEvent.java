package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCErrorEvent extends Event {
	public IRCErrorEvent() {
		super(IRCEventType.ERRORMSG);
	}

	public void onEvent(String errorMessage, Throwable error) {

	}
}
