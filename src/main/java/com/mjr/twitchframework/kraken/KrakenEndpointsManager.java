package com.mjr.twitchframework.kraken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import com.mjr.twitchframework.exceptions.MissingOrInvalidAuthException;
import com.mjr.twitchframework.exceptions.RemoteServerException;
import com.mjr.twitchframework.exceptions.StreamNotFoundOrIsOffline;
import com.mjr.twitchframework.exceptions.TooManyRequestsException;
import com.mjr.twitchframework.kraken.chat.KrakenFullChatEmoticons;
import com.mjr.twitchframework.kraken.objects.channels.KrakenAuthedChannelInfo;
import com.mjr.twitchframework.kraken.objects.channels.KrakenChannelsFollowedUser;
import com.mjr.twitchframework.kraken.objects.channels.KrakenExtentedChannelInfo;
import com.mjr.twitchframework.kraken.objects.channels.KrakenUserFollowedByUser;
import com.mjr.twitchframework.kraken.objects.streams.KrakenStream;
import com.mjr.twitchframework.kraken.objects.users.KrakenFullUserInfo;
import com.mjr.twitchframework.kraken.objects.users.KrakenSimpleUserInfo;
import com.mjr.twitchframework.kraken.objects.users.KrakenSimpleUsersInfo;
import com.mjr.twitchframework.kraken.objects.users.KrakenUsersFollowedChannel;
import com.mjr.twitchframework.util.HTTPConnect;

public class KrakenEndpointsManager {

	public static JsonParser gsonParser = new JsonParser();
	public static Gson gson = new Gson();

	public static List<KrakenChannelsFollowedUser> getChannelFollowers(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		List<KrakenChannelsFollowedUser> users = new ArrayList<KrakenChannelsFollowedUser>();
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getChannelsFollowsAPI(channelID, 100, clientID));
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
			JsonElement json = gsonParser.parse(HTTPConnect.getResult(connection));
			JsonObject obj = json.getAsJsonObject();
			int total = obj.get("_total").getAsInt();
			int current = 1;

			if (total > 1700)
				total = 1700;
			do {
				if (current != 1) {
					connection = HTTPConnect.getRequest(KrakenEndpoints.getChannelsFollowsAPI(channelID, 100, current - 1, clientID));
					json = gsonParser.parse(HTTPConnect.getResult(connection));
					obj = json.getAsJsonObject();
				}
				JsonArray array = obj.get("follows").getAsJsonArray();
				for (JsonElement follow : array) {
					users.add(gson.fromJson(follow, KrakenChannelsFollowedUser.class));
					current++;
				}
			} while (current < total);
		}
		return users;
	}

	public static KrakenExtentedChannelInfo getChannelInfo(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getChannelsAPI(channelID, clientID));
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
			return gson.fromJson(HTTPConnect.getResult(connection), KrakenExtentedChannelInfo.class);
		}
		return null;
	}

	public static KrakenAuthedChannelInfo getAuthedChannelInfo(int channelID, String accessToken, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getChannelsAPI(channelID, clientID, accessToken));
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
			return gson.fromJson(HTTPConnect.getResult(connection), KrakenAuthedChannelInfo.class);
		}
		return null;
	}


	/*
	Chat Section
	 */

	public static KrakenFullChatEmoticons getAllChatEmoticons(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException, StreamNotFoundOrIsOffline {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getStreamsAPI(channelID, clientID));
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
			JsonObject result = gsonParser.parse(HTTPConnect.getResult(connection)).getAsJsonObject();
			JsonElement stream = result.get("stream");
			if (stream.isJsonObject())
				return gson.fromJson(stream.getAsJsonObject(), KrakenFullChatEmoticons.class);
			else
				throw new StreamNotFoundOrIsOffline();
		}
		return null;
	}

	/*
	Streams Section
	 */

	public static KrakenStream getStream(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException, StreamNotFoundOrIsOffline {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getStreamsAPI(channelID, clientID));
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
			JsonObject result = gsonParser.parse(HTTPConnect.getResult(connection)).getAsJsonObject();
			JsonElement stream = result.get("stream");
			if (stream.isJsonObject())
				return gson.fromJson(stream.getAsJsonObject(), KrakenStream.class);
			else
				throw new StreamNotFoundOrIsOffline();
		}
		return null;
	}


	/*
	Users Section
	 */

	public static List<KrakenUsersFollowedChannel> getUserFollowers(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		List<KrakenUsersFollowedChannel> users = new ArrayList<KrakenUsersFollowedChannel>();
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getUsersFollowsAPI(channelID, 100, clientID));
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
			JsonElement json = gsonParser.parse(HTTPConnect.getResult(connection));
			JsonObject obj = json.getAsJsonObject();
			int total = obj.get("_total").getAsInt();
			int current = 1;

			if (total > 1700)
				total = 1700;
			do {
				if (current != 1) {
					connection = HTTPConnect.getRequest(KrakenEndpoints.getUsersFollowsAPI(channelID, 100, current - 1, clientID));
					json = gsonParser.parse(HTTPConnect.getResult(connection));
					obj = json.getAsJsonObject();
				}
				JsonArray array = obj.get("follows").getAsJsonArray();
				for (JsonElement follow : array) {
					users.add(gson.fromJson(follow, KrakenUsersFollowedChannel.class));
					current++;
				}
			} while (current < total);
		}
		return users;
	}

	public static boolean doesUserFollowChannel(int channelID, int userID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.checkUserFollowsChannel(channelID, userID, clientID));
		int responseCode = connection.getResponseCode();
		if (responseCode == 429)
			throw new TooManyRequestsException();
		if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
			throw new MissingOrInvalidAuthException();
		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND)
			return false;
		if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY || responseCode == HttpURLConnection.HTTP_UNAVAILABLE)
			throw new RemoteServerException();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			KrakenUserFollowedByUser user = gson.fromJson(HTTPConnect.getResult(connection), KrakenUserFollowedByUser.class);
			if (user.getCreatedAt() != null)
				return true;
		}
		return false;
	}

	public static boolean doesUserFollowChannel(int channelID, String userName, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		return doesUserFollowChannel(channelID, getUserIDFromChannelName(clientID, userName), clientID);
	}

	public static KrakenFullUserInfo getUserFullInfo(int userID, String accessToken, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getUserAPI(userID, clientID, accessToken));
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
			return gson.fromJson(HTTPConnect.getResult(connection), KrakenFullUserInfo.class);
		}
		return null;
	}

	public static KrakenFullUserInfo getUserFullInfo(String userName, String accessToken, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		return getUserFullInfo(getUserIDFromChannelName(clientID, userName), clientID, accessToken);
	}

	public static KrakenSimpleUserInfo getUserSimpleInfo(int userID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getUsersAPI(userID, clientID));
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
			return gson.fromJson(HTTPConnect.getResult(connection), KrakenSimpleUserInfo.class);
		}
		return null;
	}

	public static KrakenSimpleUserInfo getUserSimpleInfo(String userName, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		return getUserSimpleInfo(getUserIDFromChannelName(clientID, userName), clientID);
	}

	public static int getUserIDFromChannelName(String clientID, String channelName) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getUserIDFromChannelNameAPI(clientID, channelName));
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
			return Integer.parseInt(gson.fromJson(HTTPConnect.getResult(connection), KrakenSimpleUserInfo.class).getId());
		}
		return -1;
	}

	public static HashMap<String, Integer> getUserIDFromChannelName(String clientID, String... channelNames) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.getUserIDFromChannelNameAPI(clientID, channelNames));
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
			KrakenSimpleUsersInfo users = gson.fromJson(HTTPConnect.getResult(connection), KrakenSimpleUsersInfo.class);
			for (KrakenSimpleUserInfo user : users.getUsers())
				output.put(user.getDisplayName(), Integer.parseInt(user.getId()));
		}
		return output;
	}
}
