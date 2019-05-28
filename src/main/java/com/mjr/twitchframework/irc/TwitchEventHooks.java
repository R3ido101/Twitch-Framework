package com.mjr.twitchframework.irc;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.IRCEventType;
import com.mjr.twitchframework.irc.events.IRCDisconnectEvent;
import com.mjr.twitchframework.irc.events.IRCJoinEvent;
import com.mjr.twitchframework.irc.events.IRCMessageEvent;
import com.mjr.twitchframework.irc.events.IRCMessageExtraEvent;
import com.mjr.twitchframework.irc.events.IRCNoticeEvent;
import com.mjr.twitchframework.irc.events.IRCPartEvent;
import com.mjr.twitchframework.irc.events.IRCPrivateMessageEvent;
import com.mjr.twitchframework.irc.events.IRCUnknownEvent;

public class TwitchEventHooks {
	public static void triggerOnMessageEvent(IRCEventType type, final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCMessageEvent) event).onEvent(channel, sender, login, hostname, userID, subscriber, message);
		}
	}

	public static void triggerOnMessageExtraEvent(IRCEventType type, final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCMessageExtraEvent) event).onEvent(line, channel, sender, login, hostname, message);
		}
	}

	public static void triggerOnNoticeEvent(IRCEventType type, final String sourceNick, final String sourceLogin, final String sourceHostname, final String target, final String notice) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCNoticeEvent) event).onEvent(sourceNick, sourceLogin, sourceHostname, target, notice);
		}
	}

	public static void triggerOnJoinEvent(IRCEventType type, final String channel, final String sender, final String login, final String hostname) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCJoinEvent) event).onEvent(channel, sender, login, hostname);
		}
	}

	public static void triggerOnPartEvent(IRCEventType type, final String channel, final String sender, final String login, final String hostname) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCPartEvent) event).onEvent(channel, sender, login, hostname);
		}
	}

	public static void triggerOnPrivateMessageEvent(IRCEventType type, final String sender, final String login, final String hostname, final String channel, final String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCPrivateMessageEvent) event).onEvent(sender, login, hostname, channel, message);
		}
	}

	public static void triggerOnUnknownEvent(IRCEventType type, final String rawLine) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName())) {
				String channel = rawLine.substring(rawLine.indexOf("tmi.twitch.tv") + 18);
	        	channel = channel.substring(channel.indexOf("#") + 1);
	        	channel = channel.substring(0, channel.indexOf(" :"));
				((IRCUnknownEvent) event).onEvent(rawLine, channel);
			}
		}
	}

	public static void triggerOnDisconnectEvent(IRCEventType type, TwitchIRCClient client) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (type.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCDisconnectEvent) event).onEvent(client);
		}
	}
}
