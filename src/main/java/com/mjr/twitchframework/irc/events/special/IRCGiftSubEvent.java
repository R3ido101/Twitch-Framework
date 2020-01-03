package com.mjr.twitchframework.irc.events.special;

public class IRCGiftSubEvent extends IRCSpecialEventBase {

	public boolean isAnonymousGift;

	public IRCGiftSubEvent(String rawLine, boolean isAnonymousGift) {
		super(rawLine, IRCEventType.GIFTSUB);
		this.isAnonymousGift = isAnonymousGift;
	}

	public IRCGiftSubEvent() {
		super(null, IRCEventType.GIFTSUB);
		this.isAnonymousGift = false;
	}

	public void onEvent(IRCGiftSubEvent ircBitsEvent) {

	}

}
