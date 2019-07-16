package com.mjr.twitchframework.irc.events.twitch;

public class IRCSubscribeEvent extends IRCMessageEventBase {

	/**
	 * Display name of subscribing user
	 */
	public String sender;
	/**
	 * Is an Twitch Prime Subscription
	 */
	public boolean isPrime;

	public IRCSubscribeEvent(String rawLine) {
		super(rawLine, IRCEventType.SUBSCRIBE);
		this.sender = this.tags.get("display-name");
		this.isPrime = this.tags.get("msg-param-sub-plan").equalsIgnoreCase("prime");
	}

	public IRCSubscribeEvent() {
		super(null, IRCEventType.SUBSCRIBE);
		this.sender = null;
		this.isPrime = false;
	}

	public void onEvent(IRCSubscribeEvent ircBitsEvent) {

	}

}
