package com.mjr.twitchframework.kraken;

public class KrakenEndpoints {
	// Twitch v5
	public static String getStreamsAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/streams/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String getChannelsAPI(int channelID, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "?client_id=" + clientID + "&oauth_token=" + accessToken + "&api_version=5";
	}

	public static String getChannelsAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String getChannelsFollowsAPI(int channelID, int limit, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/follows?client_id=" + clientID + "&limit=" + limit + "&api_version=5";
	}

	public static String getChannelsFollowsAPI(int channelID, int limit, int offset, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/follows?client_id=" + clientID + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String getUsersFollowsAPI(int channelID, int limit, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + channelID + "/follows/channels?client_id=" + clientID + "&limit=" + limit + "&api_version=5";
	}

	public static String getUsersFollowsAPI(int channelID, int limit, int offset, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + channelID + "/follows/channels?client_id=" + clientID + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String getChannelsSubscriptionsAPI(int channelID, int limit, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/subscriptions?client_id=" + clientID + "&oauth_token=" + accessToken + "&limit=" + limit + "&api_version=5";
	}

	public static String getChannelsSubscriptionsAPI(int channelID, int limit, int offset, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/subscriptions?client_id=" + clientID + "&oauth_token=" + accessToken + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String checkUserSubbedChannel(int channelID, int userID, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/subscriptions/" + userID + "?client_id=" + clientID + "&oauth_token=" + accessToken + "&api_version=5";
	}

	public static String getChatAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/chat/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String checkUserFollowsChannel(int channelID, int userID, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + userID + "/follows/channels" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String getUserAPI(int channelID, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/user?client_id=" + clientID + "&oauth_token=" + accessToken + "&api_version=5";
	}

	public static String getUsersAPI(int userID, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + userID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String getUserIDFromChannelNameAPI(String clientID, String... channelNames) {
		return "https://api.twitch.tv/kraken/users?login=" + String.join(",", channelNames) + "&client_id=" + clientID + "&api_version=5";
	}

}
