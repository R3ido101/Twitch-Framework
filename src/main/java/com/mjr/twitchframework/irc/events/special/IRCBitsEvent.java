package com.mjr.twitchframework.irc.events.special;

public class IRCBitsEvent extends IRCSpecialEventBase {

	/**
	 * Number of bits gifted
	 */
	public int amount;
	/**
	 * Display name of bits sender
	 */
	public String sender;

	public IRCBitsEvent(String rawLine) {
		super(rawLine, IRCEventType.BITS);
		this.amount = Integer.parseInt(this.tags.get("bits"));
		this.sender = this.tags.get("display-name");
	}

	public IRCBitsEvent() {
		super(null, IRCEventType.BITS);
		this.amount = -1;
		this.sender = null;
	}

	public void onEvent(IRCBitsEvent ircBitsEvent) {

	}

}
