package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCMessageExtraEvent extends Event {

	public final String line;
	public final String channel;
	public final String sender;
	public final String login;
	public final String hostname;
	public final String message;

	public IRCMessageExtraEvent(String line, String channel, String sender, String login, String hostname, String message) {
		super(IRCEventType.MESSAGEEXTRA);
		this.line = line;
		this.channel = channel;
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
		this.message = message;
	}

	public IRCMessageExtraEvent() {
		super(IRCEventType.MESSAGEEXTRA);
		this.line = null;
		this.channel = null;
		this.sender = null;
		this.login = null;
		this.hostname = null;
		this.message = null;
	}

	public void onEvent(IRCMessageExtraEvent event) {

	}
}
