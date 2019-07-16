package com.mjr.twitchframework.irc.events.special;

public class IRCReSubscribeEvent extends IRCSpecialEventBase {

	public IRCReSubscribeEvent(String rawLine) {
		super(rawLine, IRCEventType.RESUBSCRIBE);
	}

	public IRCReSubscribeEvent() {
		super(null, IRCEventType.RESUBSCRIBE);
	}

	public void onEvent(IRCReSubscribeEvent ircBitsEvent) {

	}

}
