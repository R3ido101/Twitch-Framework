package com.mjr.twitchframework.helix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mjr.twitchframework.exceptions.MissingOrInvalidAuthException;
import com.mjr.twitchframework.exceptions.RemoteServerException;
import com.mjr.twitchframework.exceptions.TooManyRequestsException;
import com.mjr.twitchframework.helix.objects.GameData;
import com.mjr.twitchframework.helix.objects.GameDataList;
import com.mjr.twitchframework.helix.objects.HelixWebHookSubscriptions;
import com.mjr.twitchframework.util.HTTPConnect;

public class HelixEndpointsManager {

	public static JsonParser gsonParser = new JsonParser();
	public static Gson gson = new Gson();

	/**
	 * Subscribe to a webhook
	 * @param clientID
	 * @param callBackUrl
	 * @param topic
	 * @param sercet
	 * @return boolean http request success
	 * @throws IOException
	 * @throws TooManyRequestsException
	 * @throws MissingOrInvalidAuthException
	 * @throws RemoteServerException
	 */
	public static boolean webHookSubscribe(String appAccessToken, String clientID, String callBackUrl, String topic, String sercet) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HashMap<String, String> headers = new HashMap<String, String>();
		HashMap<String, String> bodies = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + appAccessToken);
		headers.put("Client-ID", clientID);
		bodies.put("hub.callback", callBackUrl);
		bodies.put("hub.mode", "subscribe");
		bodies.put("hub.topic", topic);
		bodies.put("hub.lease_seconds", "864000"); // 10 days
		bodies.put("hub.secret", sercet);
		HttpURLConnection connection = null;
		connection = HTTPConnect.postRequestWithHeaderBody(HelixEndpoints.webHook(), headers, bodies);
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
			return true;
		}
		return false;
	}

	/**
	 * Subscribe to a webhook
	 * @param clientID
	 * @param callBackUrl
	 * @param topic
	 * @param sercet
	 * @return boolean http request success
	 * @return
	 * @throws IOException
	 * @throws TooManyRequestsException
	 * @throws MissingOrInvalidAuthException
	 * @throws RemoteServerException
	 */
	public static boolean webHookUnsubscribe(String appAccessToken, String clientID, String callBackUrl, String topic, String sercet) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HashMap<String, String> headers = new HashMap<String, String>();
		HashMap<String, String> bodies = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + appAccessToken);
		headers.put("Client-ID", clientID);
		bodies.put("hub.callback", callBackUrl);
		bodies.put("hub.mode", "unsubscribe");
		bodies.put("hub.topic", topic);
		bodies.put("hub.lease_seconds", "864000"); // 10 days
		bodies.put("hub.secret", sercet);
		HttpURLConnection connection = null;
		connection = HTTPConnect.postRequestWithHeaderBody(HelixEndpoints.webHook(), headers, bodies);
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
			return true;
		}
		return false;
	}

	/**
	 * Get a All WebHook Subscriptions
	 * @param appAccessToken
	 * @return
	 * @throws IOException
	 * @throws TooManyRequestsException
	 * @throws MissingOrInvalidAuthException
	 * @throws RemoteServerException
	 */
	public static HelixWebHookSubscriptions getSubscribedWebHooks(String appAccessToken, String clientID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + appAccessToken);
		headers.put("Client-ID", clientID);
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequestWithHeader(HelixEndpoints.webHookSubscriptions(), headers);
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
			return gson.fromJson(stream.getAsJsonObject(), HelixWebHookSubscriptions.class);
		}
		return null;
	}

	public static GameData getGameDataFromID(String appAccessToken, String clientID, int gameID) throws IOException, TooManyRequestsException, MissingOrInvalidAuthException, RemoteServerException {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + appAccessToken);
		headers.put("Client-ID", clientID);
		HttpURLConnection connection = null;
		connection = HTTPConnect.getRequestWithHeader(HelixEndpoints.getGames(gameID), headers);
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
			return gson.fromJson(result.getAsJsonObject(), GameDataList.class).getData().get(0);
		}
		return null;
	}

}
