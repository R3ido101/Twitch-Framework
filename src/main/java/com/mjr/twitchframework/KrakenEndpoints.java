package com.mjr.twitchframework;

public class KrakenEndpoints {
	// Twitch v5
	public static String twitchGetStreamsAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/streams/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String twitchGetChannelsAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String twitchGetChannelsFollowsAPI(int channelID, int limit, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/follows?client_id=" + clientID + "&limit=" + limit + "&api_version=5";
	}

	public static String twitchGetChannelsFollowsAPI(int channelID, int limit, int offset, String clientID) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/follows?client_id=" + clientID + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String twitchGetUsersFollowsAPI(int channelID, int limit, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + channelID + "/follows/channels?client_id=" + clientID + "&limit=" + limit + "&api_version=5";
	}

	public static String twitchGetUsersFollowsAPI(int channelID, int limit, int offset, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + channelID + "/follows/channels?client_id=" + clientID + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String twitchGetChannelsSubscriptionsAPI(int channelID, int limit, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/subscriptions?client_id=" + clientID + "&oauth_token=" + accessToken + "&limit=" + limit + "&api_version=5";
	}

	public static String twitchGetChannelsSubscriptionsAPI(int channelID, int limit, int offset, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/channels/" + channelID + "/subscriptions?client_id=" + clientID + "&oauth_token=" + accessToken + "&limit=" + limit + "&offset=" + offset + "&api_version=5";
	}

	public static String twitchGetUserAPI(int channelID, String clientID, String accessToken) {
		return "https://api.twitch.tv/kraken/user?client_id=" + clientID + "&oauth_token=" + accessToken + "&api_version=5";
	}

	public static String twitchGetChatAPI(int channelID, String clientID) {
		return "https://api.twitch.tv/kraken/chat/" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String twitchCheckFollow(int channelID, String user, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + user + "/follows/channels" + channelID + "?client_id=" + clientID + "&api_version=5";
	}

	public static String twitchGetUsersAPI(int userID, String clientID) {
		return "https://api.twitch.tv/kraken/users/" + userID + "?client_id=" + clientID + "&api_version=5";
	}
}
