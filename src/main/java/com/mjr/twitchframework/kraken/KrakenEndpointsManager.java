package com.mjr.twitchframework.kraken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.mjr.twitchframework.KrakenEndpoints;
import com.mjr.twitchframework.exceptions.MissingOrInvalidAuthException;
import com.mjr.twitchframework.exceptions.RemoteServerException;
import com.mjr.twitchframework.exceptions.StreamNotFoundOrIsOffline;
import com.mjr.twitchframework.exceptions.TooManyRequestsException;
import com.mjr.twitchframework.kraken.objects.KrakenChannel;
import com.mjr.twitchframework.kraken.objects.channels.ChannelFollowedUser;
import com.mjr.twitchframework.kraken.objects.streams.KrakenStream;
import com.mjr.twitchframework.kraken.objects.users.UserFollowedChannel;
import com.mjr.twitchframework.util.HTTPConnect;

public class KrakenEndpointsManager {

	public static JsonParser gsonParser = new JsonParser();
	public static Gson gson = new Gson();

	public static List<ChannelFollowedUser> getChannelFollowers(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		List<ChannelFollowedUser> users = new ArrayList<ChannelFollowedUser>();
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetChannelsFollowsAPI(channelID, 100, clientID));
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
					connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetChannelsFollowsAPI(channelID, 100, current - 1, clientID));
					json = gsonParser.parse(HTTPConnect.getResult(connection));
					obj = json.getAsJsonObject();
				}
				JsonArray array = obj.get("follows").getAsJsonArray();
				for (JsonElement follow : array) {
					users.add(gson.fromJson(follow, ChannelFollowedUser.class));
					current++;
				}
			} while (current < total);
		}
		return users;
	}

	public static List<UserFollowedChannel> getUserFollowers(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		List<UserFollowedChannel> users = new ArrayList<UserFollowedChannel>();
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetUsersFollowsAPI(channelID, 100, clientID));
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
					connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetUsersFollowsAPI(channelID, 100, current - 1, clientID));
					json = gsonParser.parse(HTTPConnect.getResult(connection));
					obj = json.getAsJsonObject();
				}
				JsonArray array = obj.get("follows").getAsJsonArray();
				for (JsonElement follow : array) {
					users.add(gson.fromJson(follow, UserFollowedChannel.class));
					current++;
				}
			} while (current < total);
		}
		return users;
	}

	public static KrakenStream getStream(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException, StreamNotFoundOrIsOffline {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetStreamsAPI(channelID, clientID));
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

	public static KrakenChannel getChannel(int channelID, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequest(KrakenEndpoints.twitchGetChannelsAPI(channelID, clientID));
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
			return gson.fromJson(HTTPConnect.getResult(connection), KrakenChannel.class);
		}
		return null;
	}
}
