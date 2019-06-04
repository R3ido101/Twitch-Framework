package com.mjr.twitchframework.pubsub;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mjr.twitchframework.BasicWebSocketClient;

public class TwitchWebsocketClient extends BasicWebSocketClient {

	public static Gson gson = new Gson();
	private HashMap<String, List<String>> requests = new HashMap<String, List<String>>();
	private boolean showDebugMessages = false;

	public TwitchWebsocketClient(HashMap<String, List<String>> requests, boolean debug) throws URISyntaxException {
		super(new URI("wss://pubsub-edge.twitch.tv"));
		this.requests = requests;
		this.connect();
		this.setShowDebugMessages(debug);
	}

	public TwitchWebsocketClient(HashMap<String, List<String>> requests) throws URISyntaxException {
		super(new URI("wss://pubsub-edge.twitch.tv"));
		this.requests = requests;
		this.connect();
	}

	public void sendListenRequests() {
		for (String request : requests.keySet()) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "LISTEN");
			JsonObject data = new JsonObject();
			JsonArray topics = new JsonArray();
			for (String topic : requests.get(request))
				topics.add(topic);
			data.add("topics", topics);
			data.addProperty("auth_token", request);
			json.add("data", data);
			this.send(json.toString());
		}
	}

	public void sendPingMessage() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "PING");
		this.send(json.toString());
		if (this.isShowDebugMessages())
			System.out.println("SENDING PING MESSAGE!");
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		if (this.isShowDebugMessages())
			System.out.println(new java.util.Date() + " [onOpen] " + serverHandshake.getHttpStatus() + " | " + serverHandshake.getHttpStatusMessage());
		System.out.println("CONNECTION OPENED!");
		TwitchPubSubEventHooks.triggerConnectEvent(serverHandshake);
		sendListenRequests();
	}

	@Override
	public void onMessage(String message) {
		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		switch (jsonObject.get("type").getAsString()) {
		case "RECONNECT":
			this.reconnect();
			break;
		case "PONG":
			if (this.isShowDebugMessages())
				System.out.println(new java.util.Date() + " [onMessage] RECEIVED PONG MESSAGE!");
			break;
		default:
			if (this.isShowDebugMessages())
				System.out.println(new java.util.Date() + " [onMessage] " + message);
			TwitchPubSubEventHooks.triggerMessageEvent(message);
			break;
		}
	}

	@Override
	public void onClose(int codes, String message, boolean byRemoteHost) {
		if (this.isShowDebugMessages())
			System.out.println(new java.util.Date() + " [onClose] " + message + " | " + codes + " | " + byRemoteHost);
		this.close();
		System.out.println("CONNECTION CLOSED!");
		TwitchPubSubEventHooks.triggerDisconnectEvent(codes, message, byRemoteHost);

	}

	@Override
	public void onError(Exception e) {
		System.out.println("[onError] " + e.getMessage());
		TwitchPubSubEventHooks.triggerErrorEvent(e);
	}

	public HashMap<String, List<String>> getRequests() {
		return requests;
	}

	public void setRequests(HashMap<String, List<String>> requests) {
		this.requests = requests;
	}

	public boolean isShowDebugMessages() {
		return showDebugMessages;
	}

	public void setShowDebugMessages(boolean showDebugMessages) {
		this.showDebugMessages = showDebugMessages;
	}

}
