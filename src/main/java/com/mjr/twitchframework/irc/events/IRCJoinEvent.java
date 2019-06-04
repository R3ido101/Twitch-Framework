package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCJoinEvent extends Event {

	public final String channel;
	public final String sender;
	public final String login;
	public final String hostname;

	public IRCJoinEvent(String channel, String sender, String login, String hostname) {
		super(IRCEventType.JOIN);
		this.channel = channel;
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
	}

	public IRCJoinEvent() {
		super(IRCEventType.JOIN);
		this.channel = null;
		this.sender = null;
		this.login = null;
		this.hostname = null;
	}

	public void onEvent(IRCJoinEvent event) {

	}
}
