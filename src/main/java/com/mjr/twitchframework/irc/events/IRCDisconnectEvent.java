package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.irc.TwitchIRCClient;

public class IRCDisconnectEvent extends Event {
	
	public final TwitchIRCClient client;

	public IRCDisconnectEvent(TwitchIRCClient client) {
		super(IRCEventType.DISCONNECT);
		this.client = client;
	}

	public IRCDisconnectEvent() {
		super(IRCEventType.DISCONNECT);
		this.client = null;
	}
	
	public void onEvent(IRCDisconnectEvent event) {
		
	}
}
