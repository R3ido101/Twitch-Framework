package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.*;

import org.jibble.pircbot.PircBot;

import com.mjr.twitchframework.TwitchReconnectManager;

public class TwitchIRCClient extends PircBot {

	private int ID;

	private List<String> channels = new ArrayList<String>();

	public TwitchIRCClient(int iD) {
		this.ID = iD;
	}

	public void requestCapabilities(){
		this.sendRawLine("CAP REQ :twitch.tv/commands");
		this.sendRawLine("CAP REQ :twitch.tv/membership");
		this.sendRawLine("CAP REQ :twitch.tv/tags");
	}

	public boolean connectToTwitch(String username, String password) throws IOException {
		TwitchReconnectManager.initTwitchReconnectThreadIfDoesntExist();
		if (!username.equals("") && !password.equals("") && !(username == null) && !(password == null)) {
			this.setName(username);
			try {
				TwitchIRCEventHooks.triggerOnInfoEvent("Connecting to Twitch! Client ID: " + ID);
				this.connect("irc.chat.twitch.tv", 6667, password);
				requestCapabilities();
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
	protected void onMessageWithTags(String channel, String sender, String login, String hostname, String userID, String message, Map<String, String> tags) {
		TwitchIRCEventHooks.triggerOnMessageEvent(channel, sender, login, hostname, userID, message, tags);
	}

	@Override
	public void onMessageRaw(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
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

	private Map<String, String> parseTags(String raw) {
		HashMap<String, String> map = new HashMap<>();
		if (!raw.isEmpty()) {
			for (String tag : raw.split(";")) {
				String[] val = tag.split("=");
				final String key = val[0];
				String value = (val.length > 1) ? val[1] : null;
				map.put(key, value);
			}
		}
		return Collections.unmodifiableMap(map);
	}

	@Override
	protected void onUnknown(String line) {
		if (line.contains("RECONNECT"))
			this.disconnect();
		else {
			Map<String, String> tags = parseTags(line);
			TwitchIRCEventHooks.triggerOnUnknownEvent(line);
			if (tags.containsKey("msg-id") && tags.get("msg-id").equalsIgnoreCase("sub") && !tags.get("msg-id").equalsIgnoreCase("subgift") && !tags.containsKey("msg-param-mass-gift-count"))
				TwitchIRCEventHooks.triggerOnSubscribeEvent(line);
			else if (tags.containsKey("msg-id") && tags.get("msg-id").equalsIgnoreCase("resub"))
				TwitchIRCEventHooks.triggerOnReSubscribeEvent(line);
			else if (tags.containsKey("msg-id") && (tags.get("msg-id").equalsIgnoreCase("subgift") || tags.get("msg-id").equalsIgnoreCase("anonsubgift")))
				TwitchIRCEventHooks.triggerOnGiftSubEvent(line, tags.get("msg-id").equalsIgnoreCase("anonsubgift"));
			else if (tags.containsKey("msg-param-mass-gift-count"))
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
		this.disconnect();
		TwitchIRCEventHooks.triggerOnDisconnectEvent(this);
		try {
			TwitchIRCEventHooks.triggerOnInfoEvent("Disconnect client, Client ID: " + ID);
			TwitchReconnectManager.getTwitchReconnectThread().addTwitchIRCClient(this);
		} catch (Exception e) {
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
