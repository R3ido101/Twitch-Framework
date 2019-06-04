package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCErrorEvent extends Event {

	public final String errorMessage;
	public final Throwable error;

	public IRCErrorEvent(String errorMessage, Throwable error) {
		super(IRCEventType.ERRORMSG);
		this.errorMessage = errorMessage;
		this.error = error;
	}

	public IRCErrorEvent() {
		super(IRCEventType.ERRORMSG);
		this.errorMessage = null;
		this.error = null;
	}

	public void onEvent(IRCErrorEvent event) {

	}
}
