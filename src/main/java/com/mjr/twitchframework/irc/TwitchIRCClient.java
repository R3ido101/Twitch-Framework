package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.PircBot;

import com.mjr.twitchframework.Event.IRCEventType;

public class TwitchIRCClient extends PircBot {

	private List<String> channels = new ArrayList<String>();

	public boolean connectToTwitch(String username, String password) throws IOException {
		if (!username.equals("") && !password.equals("") && !(username == null) && !(password == null)) {
			this.setName(username);
			try {
				System.out.println("Connecting to Twitch!");
				this.connect("irc.chat.twitch.tv", 6667, password);
				this.sendRawLine("CAP REQ :twitch.tv/commands");
				this.sendRawLine("CAP REQ :twitch.tv/membership");
				this.sendRawLine("CAP REQ :twitch.tv/tags");
				System.out.println("Connected to Twitch!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to connect to Twitch! Check your internet connection!");

				return false;
			}
			return true;
		} else {
			System.out.println("Error! No Login details were set! Go to settings to enter them! \n Use the Reconnect button when done!");
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
		TwitchIRCManager.triggerOnMessageEvent(IRCEventType.MESSAGE, channel, sender, login, hostname, userID, subscriber, message);
	}

	@Override
	public void onMessageExtra(final String line, final String channel, final String sender, final String login, final String hostname, final String message) {
		System.out.println("[onMessage]" + channel + " | " + sender + " > " + message);

	}

	@Override
	protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		System.out.println("[onNotice]" + notice);
	}

	@Override
	public void onPrivateMessage(String sender, String login, String hostname, String channel, String message) {
		System.out.println("[onMessage]" + channel + " | " + sender + " > " + message);
	}

	@Override
	protected void onUnknown(String line) {
		System.out.println("[onUnknown]" + line);

	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		System.out.println("[onPart]" + channel + " | " + sender);

	}

	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		System.out.println("[onPart]" + channel + " | " + sender);

	}

	@Override
	protected void onDisconnect() {
		System.out.println("[onDisconnect]");

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
}
