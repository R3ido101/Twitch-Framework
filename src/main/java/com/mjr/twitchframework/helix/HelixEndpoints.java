package com.mjr.twitchframework.helix;

public class HelixEndpoints {
	public static String webHook() {
		return "https://api.twitch.tv/helix/webhooks/hub";
	}

	public static String webHookSubscriptions() {
		return "https://api.twitch.tv/helix/webhooks/subscriptions";
	}

	public static String getGames(int gameID) {
		return "https://api.twitch.tv/helix/games?id=" + gameID;
	}

	public static String getStreams(int userID) {
		return "https://api.twitch.tv/helix/streams?user_id=" + userID;
	}
}
