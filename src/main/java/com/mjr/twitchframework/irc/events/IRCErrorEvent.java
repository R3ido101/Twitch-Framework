package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCErrorEvent extends Event {

	public String errorMessage;
	public Throwable error;

	public IRCErrorEvent(String errorMessage, Throwable error) {
		super(IRCEventType.ERRORMSG);
		this.errorMessage = errorMessage;
		this.error = error;
	}

	public IRCErrorEvent() {
		super(IRCEventType.ERRORMSG);
	}

	public void onEvent(IRCErrorEvent event) {

	}
}
