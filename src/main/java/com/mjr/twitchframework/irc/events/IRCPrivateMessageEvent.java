package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCPrivateMessageEvent extends Event {

	public final String sender;
	public final String login;
	public final String hostname;
	public final String channel;
	public final String message;

	public IRCPrivateMessageEvent(String sender, String login, String hostname, String channel, String message) {
		super(IRCEventType.PRIVATEMESSAGE);
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
		this.channel = channel;
		this.message = message;
	}

	public IRCPrivateMessageEvent() {
		super(IRCEventType.PRIVATEMESSAGE);
		this.sender = null;
		this.login = null;
		this.hostname = null;
		this.channel = null;
		this.message = null;
	}

	public void onEvent(IRCPrivateMessageEvent event) {

	}
}
