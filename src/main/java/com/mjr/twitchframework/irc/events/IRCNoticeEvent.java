package com.mjr.twitchframework.irc.events;

import com.mjr.twitchframework.Event;

public class IRCNoticeEvent extends Event {
	
	public final String sourceNick; public final String sourceLogin; public final String sourceHostname; public final String target; public final String notice;

	public IRCNoticeEvent(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		super(IRCEventType.NOTICE);
		this.sourceNick = sourceNick;
		this.sourceLogin = sourceLogin;
		this.sourceHostname = sourceHostname;
		this.target = target;
		this.notice = notice;
	}

	public IRCNoticeEvent() {
		super(IRCEventType.NOTICE);
		this.sourceNick = null;
		this.sourceLogin = null;
		this.sourceHostname = null;
		this.target = null;
		this.notice = null;
	}
	
	public void onEvent(IRCNoticeEvent event) {
		
	}
}
