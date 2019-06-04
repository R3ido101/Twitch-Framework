package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCPartEvent extends Event {

	public final String channel;
	public final String sender;
	public final String login;
	public final String hostname;

	public IRCPartEvent(String channel, String sender, String login, String hostname) {
		super(IRCEventType.PART);
		this.channel = channel;
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
	}

	public IRCPartEvent() {
		super(IRCEventType.PART);
		this.channel = null;
		this.sender = null;
		this.login = null;
		this.hostname = null;
	}

	public void onEvent(IRCPartEvent event) {

	}
}
