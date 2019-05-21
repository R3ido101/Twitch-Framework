package com.mjr.twitchframework.irc;

public abstract class TwitchBotBase {

	public abstract void onMessage(final String channel, final String sender, final String login, final String hostname, final String userID, final boolean subscriber, final String message);

	public abstract void onMessageExtra(final String line, final String channel, final String sender, final String login, final String hostname, final String message);

	protected abstract void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice);

	public abstract void onPrivateMessage(String sender, String login, String hostname, String channel, String message);

	protected abstract void onUnknown(String line);

	protected abstract void onJoin(String channel, String sender, String login, String hostname);

	protected abstract void onPart(String channel, String sender, String login, String hostname);
}
