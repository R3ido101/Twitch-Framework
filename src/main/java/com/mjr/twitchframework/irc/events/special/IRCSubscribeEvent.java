package com.mjr.twitchframework.irc.events.special;

public class IRCSubscribeEvent extends IRCSpecialEventBase {

	/**
	 * Display name of subscribing user
	 */
	public String sender;
	/**
	 * Is an Twitch Prime Subscription
	 */
	public boolean isPrime;

	/**
	 * Tier of Subscription
	 */
	public SubscriptionType type;

	public IRCSubscribeEvent(String rawLine) {
		super(rawLine, IRCEventType.SUBSCRIBE);
		this.sender = this.tags.get("display-name");
		this.isPrime = this.tags.get("msg-param-sub-plan").equalsIgnoreCase("prime");
		if(isPrime)
			type = null;
		else
			type = SubscriptionType.getTypeByValue(Integer.parseInt(tags.get("msg-param-sub-plan")));
	}

	public IRCSubscribeEvent() {
		super(null, IRCEventType.SUBSCRIBE);
		this.sender = null;
		this.isPrime = false;
		type = null;
	}

	public void onEvent(IRCSubscribeEvent ircBitsEvent) {

	}

}
