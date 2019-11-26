package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

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
				TwitchIRCEventHooks.triggerOnInfoEvent("Connecting to Twitch! Client ID: " + ID);
				this.connect("irc.chat.twitch.tv", 6667, password);
				this.sendRawLine("CAP REQ :twitch.tv/commands");
				this.sendRawLine("CAP REQ :twitch.tv/membership");
				this.sendRawLine("CAP REQ :twitch.tv/tags");
				TwitchIRCEventHooks.triggerOnInfoEvent("Connected to Twitch! Client ID: " + ID);
			} catch (Exception e) {
				e.printStackTrace();
				TwitchIRCEventHooks.triggerOnErrorEvent("Failed to connect to Twitch! Check your internet connection! Client ID: " + ID, e);
				return false;
			}
			return true;
		} else {
			TwitchIRCEventHooks.triggerOnErrorEvent("Error! No Login details were set! Go to settings to enter them! \n Use the Reconnect button when done! Client ID: " + ID, null);
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
		TwitchIRCEventHooks.triggerOnMessageEvent(channel, sender, login, hostname, userID, subscriber, message);
	}

	@Override
	public void onMessageExtra(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		TwitchIRCEventHooks.triggerOnMessageExtraEvent(line, channel, sender, login, hostname, message);
		if (line.contains("bits="))
			TwitchIRCEventHooks.triggerOnBitsEvent(line, channel, sender, login, hostname, message);
	}

	@Override
	protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		TwitchIRCEventHooks.triggerOnNoticeEvent(sourceNick, sourceLogin, sourceHostname, target, notice);
	}

	@Override
	public void onPrivateMessage(String sender, String login, String hostname, String channel, String message) {
		TwitchIRCEventHooks.triggerOnPrivateMessageEvent(sender, login, hostname, channel, message);
	}

	@Override
	protected void onUnknown(String line) {
		if (line.contains("RECONNECT"))
			this.onDisconnect();
		else {
			TwitchIRCEventHooks.triggerOnUnknownEvent(line);
			if (line.contains("msg-id=sub") && !line.contains("msg-id=subgift"))
				TwitchIRCEventHooks.triggerOnSubscribeEvent(line);
			else if (line.contains("msg-id=resub"))
				TwitchIRCEventHooks.triggerOnReSubscribeEvent(line);
			else if ((line.contains("msg-id=subgift") || line.contains("msg-id=anonsubgift")) && line.contains("msg-param-recipient-display-name"))
				TwitchIRCEventHooks.triggerOnGiftSubEvent(line, line.contains("msg-id=anonsubgift"));
			else if (line.contains("msg-param-mass-gift-count"))
				TwitchIRCEventHooks.triggerOnSubGiftingEvent(line);
		}
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		TwitchIRCEventHooks.triggerOnJoinEvent(channel, sender, login, hostname);
	}

	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		TwitchIRCEventHooks.triggerOnPartEvent(channel, sender, login, hostname);
	}

	@Override
	protected void onDisconnect() {
		TwitchIRCEventHooks.triggerOnDisconnectEvent(this);
		try {
			do {
				TwitchIRCEventHooks.triggerOnInfoEvent("Trying to reconnect client, Client ID: " + ID);
				this.disconnect();
				this.reconnect();
				TwitchIRCEventHooks.triggerOnInfoEvent("Reconnected client, Client ID: " + ID);
				connectToChannels();
				TwitchIRCEventHooks.triggerOnInfoEvent("Joining back channels for client, Client ID: " + ID);
			} while (this.isConnected() == false);
		} catch (IOException | IrcException e) {
			e.printStackTrace();
			TwitchIRCEventHooks.triggerOnErrorEvent("Error processing onDisconnectEvent for an client, Client ID: " + ID, e);
		}
	}
	
	protected void onConnect() {
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
