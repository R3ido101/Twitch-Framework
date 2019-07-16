package com.mjr.twitchframework.irc.events.twitch;

public class IRCReSubscribeEvent extends IRCMessageEventBase {

	public IRCReSubscribeEvent(String rawLine) {
		super(rawLine, IRCEventType.RESUBSCRIBE);
	}
	
	public IRCReSubscribeEvent() {
		super(null, IRCEventType.RESUBSCRIBE);
	}

	public void onEvent(IRCReSubscribeEvent ircBitsEvent) {

	}

}
