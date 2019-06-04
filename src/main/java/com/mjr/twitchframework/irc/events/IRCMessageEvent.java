package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCMessageEvent extends Event {

	public final String channel;
	public final String sender;
	public final String login;
	public final String hostname;
	public final String userID;
	public final boolean subscriber;
	public final String message;

	public IRCMessageEvent(String channel, String sender, String login, String hostname, String userID, boolean subscriber, String message) {
		super(IRCEventType.MESSAGE);
		this.channel = channel;
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
		this.userID = userID;
		this.subscriber = subscriber;
		this.message = message;
	}

	public IRCMessageEvent() {
		super(IRCEventType.MESSAGE);
		this.channel = null;
		this.sender = null;
		this.login = null;
		this.hostname = null;
		this.userID = null;
		this.subscriber = false;
		this.message = null;
	}

	public void onEvent(IRCMessageEvent event) {

	}
}
