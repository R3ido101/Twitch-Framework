package com.mjr.twitchframework.irc;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.IRCEventType;
import com.mjr.twitchframework.irc.events.IRCDisconnectEvent;
import com.mjr.twitchframework.irc.events.IRCErrorEvent;
import com.mjr.twitchframework.irc.events.IRCInfoEvent;
import com.mjr.twitchframework.irc.events.IRCJoinEvent;
import com.mjr.twitchframework.irc.events.IRCMessageEvent;
import com.mjr.twitchframework.irc.events.IRCMessageExtraEvent;
import com.mjr.twitchframework.irc.events.IRCNoticeEvent;
import com.mjr.twitchframework.irc.events.IRCPartEvent;
import com.mjr.twitchframework.irc.events.IRCPrivateMessageEvent;
import com.mjr.twitchframework.irc.events.IRCUnknownEvent;

public class TwitchEventHooks {
	public static void triggerOnMessageEvent(final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.MESSAGE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCMessageEvent) event).onEvent(new IRCMessageEvent(channel, sender, login, hostname, userID, subscriber, message));
		}
	}

	public static void triggerOnMessageExtraEvent(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.MESSAGEEXTRA.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCMessageExtraEvent) event).onEvent(new IRCMessageExtraEvent(line, channel, sender, login, hostname, message));
		}
	}

	public static void triggerOnNoticeEvent(final String sourceNick, final String sourceLogin, final String sourceHostname, final String target, final String notice) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.NOTICE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCNoticeEvent) event).onEvent(new IRCNoticeEvent(sourceNick, sourceLogin, sourceHostname, target, notice));
		}
	}

	public static void triggerOnJoinEvent(final String channel, final String sender, final String login, final String hostname) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.JOIN.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCJoinEvent) event).onEvent(new IRCJoinEvent(channel, sender, login, hostname));
		}
	}

	public static void triggerOnPartEvent(final String channel, final String sender, final String login, final String hostname) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.PART.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCPartEvent) event).onEvent(new IRCPartEvent(channel, sender, login, hostname));
		}
	}

	public static void triggerOnPrivateMessageEvent(final String sender, final String login, final String hostname, final String channel, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.PRIVATEMESSAGE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCPrivateMessageEvent) event).onEvent(new IRCPrivateMessageEvent(sender, login, hostname, channel, message));
		}
	}

	public static void triggerOnUnknownEvent(final String rawLine) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.UNKNOWN.getName().equalsIgnoreCase(event.typeIRC.getName())) {
				String channel = rawLine.substring(rawLine.indexOf("tmi.twitch.tv") + 13);
				channel = channel.substring(channel.indexOf("#") + 1);
				if (channel.contains(":"))
					channel = channel.substring(0, channel.indexOf(" :"));
				((IRCUnknownEvent) event).onEvent(rawLine, channel);
			}
		}
	}

	public static void triggerOnDisconnectEvent(TwitchIRCClient client) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.DISCONNECT.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCDisconnectEvent) event).onEvent(new IRCDisconnectEvent(client));
		}
	}

	public static void triggerOnErrorEvent(String errorMessage, Throwable error) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.ERRORMSG.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCErrorEvent) event).onEvent(new IRCErrorEvent(errorMessage, error));
		}
	}

	public static void triggerOnInfoEvent(String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.INFOMSG.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCInfoEvent) event).onEvent(new IRCInfoEvent(message));
		}
	}
}
