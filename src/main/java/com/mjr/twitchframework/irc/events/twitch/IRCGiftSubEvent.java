package com.mjr.twitchframework.irc.events.twitch;

public class IRCGiftSubEvent extends IRCMessageEventBase {

	public boolean isAnonymousGift;

	public IRCGiftSubEvent(String rawLine, boolean isAnonymousGift) {
		super(rawLine, IRCEventType.GIFTSUB);
		this.isAnonymousGift = isAnonymousGift;
	}

	public IRCGiftSubEvent() {
		super(null, IRCEventType.BITS);
		this.isAnonymousGift = false;
	}

	public void onEvent(IRCGiftSubEvent ircBitsEvent) {

	}

}
