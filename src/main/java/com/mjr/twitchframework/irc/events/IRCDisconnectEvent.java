package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.irc.TwitchIRCClient;

public class IRCDisconnectEvent extends Event {

	public IRCDisconnectEvent() {
		super(IRCEventType.DISCONNECT);
	}
	
	public void onEvent(TwitchIRCClient client) {
		
	}
}
