package com.mjr.twitchframework.irc.events.twitch;

public class IRCSubGiftingEvent extends IRCMessageEventBase {

	/** Triggered right after gift sub(s) are made, contains info on what they have gifted. Like amount, types..
	 * @param rawLine
	 */
	public IRCSubGiftingEvent(String rawLine) {
		super(rawLine, IRCEventType.GIFTINGSUB);
	}
	
	/** Triggered right after gift sub(s) are made, contains info on what they have gifted. Like amount, types..
	 * @param rawLine
	 */
	public IRCSubGiftingEvent() {
		super(null, IRCEventType.GIFTINGSUB);
	}

	public void onEvent(IRCSubGiftingEvent ircBitsEvent) {

	}

}
