package com.mjr.twitchframework.irc;

public abstract class TwitchBotBase {

	private int channelID;
	private String channelName;

	public int getChannelID() {
		return this.channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getIrcChannelName() {
		return "#" + this.getChannelName();
	}

	public abstract void onMessage(final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message);

	/**
	 * Same as onMessage but includes the rawMessage for extra parsing
	 * 
	 * @param line
	 * @param channel
	 * @param sender
	 * @param login
	 * @param hostname
	 * @param message
	 */
	public abstract void onMessageExtra(final String line, final String channel, final String sender, final String login, final String hostname, final String message);

	protected abstract void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice);

	public abstract void onPrivateMessage(String sender, String login, String hostname, String channel, String message);

	protected abstract void onUnknown(String line);

	protected abstract void onJoin(String channel, String sender, String login, String hostname);

	protected abstract void onPart(String channel, String sender, String login, String hostname);
}
