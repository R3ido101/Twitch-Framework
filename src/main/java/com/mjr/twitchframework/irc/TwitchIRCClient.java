package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import com.mjr.twitchframework.Event.IRCEventType;

public class TwitchIRCClient extends PircBot {

	private int ID;

	private List<String> channels = new ArrayList<String>();

	public TwitchIRCClient(int iD) {
		this.ID = iD;
	}

	public boolean connectToTwitch(String username, String password) throws IOException {
		if (!username.equals("") && !password.equals("") && !(username == null) && !(password == null)) {
			this.setName(username);
			try {
				TwitchEventHooks.triggerOnInfoEvent(IRCEventType.INFOMSG, "Connecting to Twitch! Client ID: " + ID);
				this.connect("irc.chat.twitch.tv", 6667, password);
				this.sendRawLine("CAP REQ :twitch.tv/commands");
				this.sendRawLine("CAP REQ :twitch.tv/membership");
				this.sendRawLine("CAP REQ :twitch.tv/tags");
				TwitchEventHooks.triggerOnInfoEvent(IRCEventType.INFOMSG, "Connected to Twitch! Client ID: " + ID);
			} catch (Exception e) {
				e.printStackTrace();
				TwitchEventHooks.triggerOnErrorEvent(IRCEventType.ERRORMSG, "Failed to connect to Twitch! Check your internet connection! Client ID: " + ID, e);
				return false;
			}
			return true;
		} else {
			TwitchEventHooks.triggerOnErrorEvent(IRCEventType.ERRORMSG, "Error! No Login details were set! Go to settings to enter them! \n Use the Reconnect button when done! Client ID: " + ID, null);
		}
		return false;
	}

	public void connectToChannels() {
		for (String channel : channels) {
			this.joinChannel("#" + channel);
		}
	}

	@Override
	public void onMessage(final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message) {
		TwitchEventHooks.triggerOnMessageEvent(IRCEventType.MESSAGE, channel, sender, login, hostname, userID, subscriber, message);
	}

	@Override
	public void onMessageExtra(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		TwitchEventHooks.triggerOnMessageExtraEvent(IRCEventType.MESSAGEEXTRA, line, channel, sender, login, hostname, message);
	}

	@Override
	protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		TwitchEventHooks.triggerOnNoticeEvent(IRCEventType.NOTICE, sourceNick, sourceLogin, sourceHostname, target, notice);
	}

	@Override
	public void onPrivateMessage(String sender, String login, String hostname, String channel, String message) {
		TwitchEventHooks.triggerOnPrivateMessageEvent(IRCEventType.PRIVATEMESSAGE, sender, login, hostname, channel, message);
	}

	@Override
	protected void onUnknown(String line) {
		if (line.contains("RECONNECT"))
			this.onDisconnect();
		else
			TwitchEventHooks.triggerOnUnknownEvent(IRCEventType.UNKNOWN, line);
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		TwitchEventHooks.triggerOnJoinEvent(IRCEventType.JOIN, channel, sender, login, hostname);
	}

	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		TwitchEventHooks.triggerOnPartEvent(IRCEventType.PART, channel, sender, login, hostname);
	}

	@Override
	protected void onDisconnect() {
		TwitchEventHooks.triggerOnDisconnectEvent(IRCEventType.DISCONNECT, this);
		try {
			do {
				TwitchEventHooks.triggerOnInfoEvent(IRCEventType.INFOMSG, "Trying to reconnect client, Client ID: " + ID);
				this.disconnect();
				this.reconnect();
			} while (this.isConnected() == false);
		} catch (IOException | IrcException e) {
			e.printStackTrace();
			TwitchEventHooks.triggerOnErrorEvent(IRCEventType.ERRORMSG, "Error processing onDisconnectEvent for an client, Client ID: " + ID, e);
		}
	}

	public void sendMessage(String message) {
		this.sendMessage(message);
	}

	public List<String> getChannelsList() {
		return channels;
	}

	public void setChannelsList(List<String> channels) {
		this.channels = channels;
	}

	public void addChannel(String channel) {
		this.channels.add(channel);
	}

	public void removeChannel(String channel) {
		this.channels.remove(channel);
	}

	public void addChannelWithConnect(String channel) {
		this.channels.add(channel);
		this.joinChannel("#" + channel);
	}

	public void removeChannelWithDisconnect(String channel) {
		this.channels.remove(channel);
		this.partChannel("#" + channel);
	}

	public int getID() {
		return ID;
	}

	public boolean isClientConnected() {
		return this.isConnected();
	}
}
