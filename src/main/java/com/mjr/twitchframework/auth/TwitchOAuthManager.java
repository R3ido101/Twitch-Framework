package com.mjr.twitchframework.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.mjr.twitchframework.auth.objects.TwitchAuthedUserInfo;
import com.mjr.twitchframework.auth.objects.TwitchOAuthClientCredToken;
import com.mjr.twitchframework.auth.objects.TwitchOAuthRefreshToken;
import com.mjr.twitchframework.exceptions.BadRequestException;
import com.mjr.twitchframework.exceptions.MissingOrInvalidAuthException;
import com.mjr.twitchframework.exceptions.RemoteServerException;
import com.mjr.twitchframework.exceptions.TooManyRequestsException;
import com.mjr.twitchframework.util.HTTPConnect;

public class TwitchOAuthManager {

	public static Gson gson = new Gson();

	public enum TwitchScopes {
		// @formatter:off
		CHAT_CHANNEL_MODERATE("channel:moderate"),
	    CHAT_EDIT("chat:edit"),
	    CHAT_READ("chat:read"),
	    CHAT_WHISPERS_READ("whispers:read"),
	    CHAT_WHISPERS_EDIT("whispers:edit"),
	    KRAKEN_CHANNEL_CHECK_SUBSCRIPTION("channel_check_subscription"),
	    KRAKEN_CHANNEL_COMMERCIAL("channel_commercial"),
	    KRAKEN_CHANNEL_EDITOR("channel_editor"),
	    KRAKEN_CHANNEL_FEED_EDIT("channel_feed_edit"),
	    KRAKEN_CHANNEL_FEED_READ("channel_feed_read"),
	    KRAKEN_CHANNEL_READ("channel_read"),
	    KRAKEN_CHANNEL_STREAM("channel_stream"),
	    KRAKEN_CHANNEL_SUBSCRIPTIONS("channel_subscriptions"),
	    KRAKEN_CHAT_LOGIN("chat_login"),
	    KRAKEN_COLLECTIONS_EDIT("collections_edit"),
	    KRAKEN_COMMUNITIES_EDIT("communities_edit"),
	    KRAKEN_COMMUNITIES_MODERATE("communities_moderate"),
	    KRAKEN_OPENID("openid"),
	    KRAKEN_USER_BLOCKS_EDIT("user_blocks_edit"),
	    KRAKEN_USER_BLOCKS_READ("user_blocks_read"),
	    KRAKEN_USER_FOLLOWS_EDIT("user_follows_edit"),
	    KRAKEN_USER_READ("user_read"),
	    KRAKEN_USER_SUBSCRIPTIONS("user_subscriptions"),
	    KRAKEN_VIEWING_ACTIVITY_READ("viewing_activity_read");
		// @formatter:on

		public String name;

		private TwitchScopes(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public static TwitchScopes getByName(String name) {
			for (TwitchScopes scope : TwitchScopes.values())
				if (scope.getName().equalsIgnoreCase(name))
					return scope;
			return null;
		}

	};

	public static TwitchAuthedUserInfo validateOrGetOAuthedUserInfo(String accessToken) throws IOException, FileNotFoundException, MissingOrInvalidAuthException, RemoteServerException, TooManyRequestsException {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "OAuth " + accessToken);
		HttpURLConnection connection = HTTPConnect.getRequestWithHeader("https://id.twitch.tv/oauth2/validate", headers);
		int responseCode = connection.getResponseCode();
		if (responseCode == 429)
			throw new TooManyRequestsException();
		if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
			throw new MissingOrInvalidAuthException();
		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND)
			throw new FileNotFoundException();
		if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY || responseCode == HttpURLConnection.HTTP_UNAVAILABLE)
			throw new RemoteServerException();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String jsonReply = HTTPConnect.getResult(connection);
			connection.disconnect();
			return gson.fromJson(jsonReply, TwitchAuthedUserInfo.class);
		}
		return null;
	}

	public static TwitchOAuthRefreshToken refreshUserAccessToken(String refreshToken, String clientID, String clientSecret)
			throws IOException, FileNotFoundException, MissingOrInvalidAuthException, RemoteServerException, BadRequestException, TooManyRequestsException {
		HttpURLConnection connection = HTTPConnect.postRequest("https://id.twitch.tv/oauth2/token?client_id=" + clientID + "&grant_type=refresh_token&refresh_token=" + refreshToken + "&client_secret=" + clientSecret);
		int responseCode = connection.getResponseCode();
		if (responseCode == 429)
			throw new TooManyRequestsException();
		if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
			throw new MissingOrInvalidAuthException();
		if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST)
			throw new BadRequestException();
		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND)
			throw new FileNotFoundException();
		if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY || responseCode == HttpURLConnection.HTTP_UNAVAILABLE)
			throw new RemoteServerException();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String jsonReply = HTTPConnect.getResult(connection);
			connection.disconnect();
			return gson.fromJson(jsonReply, TwitchOAuthRefreshToken.class);
		}
		return null;
	}

	public static TwitchOAuthClientCredToken newAppAccessToken(String clientID, String clientSecret) throws IOException, FileNotFoundException, MissingOrInvalidAuthException, RemoteServerException, BadRequestException, TooManyRequestsException {
		HttpURLConnection connection = HTTPConnect.postRequest("https://id.twitch.tv/oauth2/token?client_id=" + clientID + "&grant_type=client_credentials&client_secret=" + clientSecret);
		int responseCode = connection.getResponseCode();
		if (responseCode == 429)
			throw new TooManyRequestsException();
		if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
			throw new MissingOrInvalidAuthException();
		if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST)
			throw new BadRequestException();
		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND)
			throw new FileNotFoundException();
		if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY || responseCode == HttpURLConnection.HTTP_UNAVAILABLE)
			throw new RemoteServerException();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String jsonReply = HTTPConnect.getResult(connection);
			connection.disconnect();
			return gson.fromJson(jsonReply, TwitchOAuthClientCredToken.class);
		}
		return null;
	}
}
