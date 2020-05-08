package com.mjr.twitchframework.irc.events;

import java.util.Map;

import com.mjr.twitchframework.Event;

public class IRCMessageEvent extends Event {

	public final String channel;
	public final String sender;
	public final String login;
	public final String hostname;
	public final String userID;
	public final String message;
	public final Map<String, String> tags;

	public IRCMessageEvent(String channel, String sender, String login, String hostname, String userID, String message, Map<String, String> tags) {
		super(IRCEventType.MESSAGE);
		this.channel = channel;
		this.sender = sender;
		this.login = login;
		this.hostname = hostname;
		this.userID = userID;
		this.message = message;
		this.tags = tags;
	}

	public IRCMessageEvent() {
		super(IRCEventType.MESSAGE);
		this.channel = null;
		this.sender = null;
		this.login = null;
		this.hostname = null;
		this.userID = null;
		this.message = null;
		this.tags = null;
	}

	public void onEvent(IRCMessageEvent event) {

	}
}
