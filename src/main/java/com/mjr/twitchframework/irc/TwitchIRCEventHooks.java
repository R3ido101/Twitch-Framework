package com.mjr.twitchframework.irc;

import java.util.Map;

import com.mjr.twitchframework.Event;
import com.mjr.twitchframework.Event.IRCEventType;
import com.mjr.twitchframework.irc.events.*;
import com.mjr.twitchframework.irc.events.special.*;

public class TwitchIRCEventHooks {
	public static void triggerOnMessageEvent(String channel, String sender, String login, String hostname, String userID, String message, Map<String, String> tags) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.MESSAGE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCMessageEvent) event).onEvent(new IRCMessageEvent(channel, sender, login, hostname, userID, message, tags));
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
				((IRCUnknownEvent) event).onEvent(new IRCUnknownEvent(rawLine, channel));
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

	public static void triggerOnBitsEvent(String line, String channel, String sender, String login, String hostname, String message) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.BITS.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCBitsEvent) event).onEvent(new IRCBitsEvent(line));
		}
	}

	public static void triggerOnSubscribeEvent(String line) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.SUBSCRIBE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCSubscribeEvent) event).onEvent(new IRCSubscribeEvent(line));
		}
	}

	public static void triggerOnReSubscribeEvent(String line) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.RESUBSCRIBE.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCReSubscribeEvent) event).onEvent(new IRCReSubscribeEvent(line));
		}
	}

	public static void triggerOnGiftSubEvent(String line, boolean isAnonymousGift) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.GIFTSUB.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCGiftSubEvent) event).onEvent(new IRCGiftSubEvent(line, isAnonymousGift));
		}
	}

	public static void triggerOnSubGiftingEvent(String line) {
		for (Event event : TwitchIRCManager.getEventListeners()) {
			if (IRCEventType.GIFTINGSUB.getName().equalsIgnoreCase(event.typeIRC.getName()))
				((IRCSubGiftingEvent) event).onEvent(new IRCSubGiftingEvent(line));
		}
	}
}
