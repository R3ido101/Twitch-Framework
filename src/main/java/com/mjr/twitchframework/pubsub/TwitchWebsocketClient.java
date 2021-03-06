package com.mjr.twitchframework.pubsub;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.java_websocket.enums.Opcode;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mjr.twitchframework.BasicWebSocketClient;
import com.mjr.twitchframework.TwitchReconnectManager;

public class TwitchWebsocketClient extends BasicWebSocketClient {

	public static Gson gson = new Gson();
	private HashMap<String, List<String>> requests = new HashMap<String, List<String>>();
	private boolean showDebugMessages = false;
	private int channelID;

	public TwitchWebsocketClient(HashMap<String, List<String>> requests, boolean debug, int channelID) throws URISyntaxException {
		super(new URI("wss://pubsub-edge.twitch.tv"));
		TwitchReconnectManager.initTwitchReconnectThreadIfDoesntExist();
		this.requests = requests;
		this.connect();
		this.setShowDebugMessages(debug);
		this.channelID = channelID;
	}

	public TwitchWebsocketClient(HashMap<String, List<String>> requests, int channelID) throws URISyntaxException {
		super(new URI("wss://pubsub-edge.twitch.tv"));
		TwitchReconnectManager.initTwitchReconnectThreadIfDoesntExist();
		this.requests = requests;
		this.connect();
		this.channelID = channelID;
	}

	public void sendListenRequests() {
		for (String request : requests.keySet()) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "LISTEN");
			JsonObject data = new JsonObject();
			JsonArray topics = new JsonArray();
			for (String topic : requests.get(request))
				topics.add((String) topic);
			data.add("topics", topics);
			data.addProperty("auth_token", request);
			json.add("data", data);
			this.send(json.toString());
		}
	}

	public void sendPingMessage() {
		FramedataImpl1 frame = FramedataImpl1.get(Opcode.PING);
		frame.setFin(true);
		this.sendFrame(frame);
		JsonObject json = new JsonObject();
		json.addProperty("type", "PING");
		this.send(json.toString());
		if (this.isShowDebugMessages())
			TwitchPubSubEventHooks.triggerInfoEvent(this, "SENDING PING MESSAGE!");
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {
		if (this.isShowDebugMessages())
			TwitchPubSubEventHooks.triggerInfoEvent(this, "[onOpen] " + serverHandshake.getHttpStatus() + " | " + serverHandshake.getHttpStatusMessage());
		TwitchPubSubEventHooks.triggerInfoEvent(this, "CONNECTION OPENED!");
		TwitchPubSubEventHooks.triggerConnectEvent(this, serverHandshake);
		sendListenRequests();
		this.sendPingMessage();
	}

	@Override
	public void onMessage(String message) {
		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		switch (jsonObject.get("type").getAsString()) {
		case "RECONNECT":
			try {
				this.closeBlocking();
			} catch (InterruptedException e) {
				onError(e);
			}
			break;
		case "PONG":
			if (this.isShowDebugMessages())
				TwitchPubSubEventHooks.triggerInfoEvent(this, "[onMessage] RECEIVED PONG MESSAGE!");
			break;
		default:
			if (this.isShowDebugMessages())
				TwitchPubSubEventHooks.triggerInfoEvent(this, "[onMessage] " + message);
			TwitchPubSubEventHooks.triggerMessageEvent(this, message);
			break;
		}
	}

	@Override
	public void onClose(int codes, String message, boolean byRemoteHost) {
		if (this.isShowDebugMessages())
			TwitchPubSubEventHooks.triggerInfoEvent(this, "[onClose] " + message + " | " + codes + " | " + byRemoteHost);
		this.close();
		TwitchPubSubEventHooks.triggerInfoEvent(this, "CONNECTION CLOSED!");
		TwitchPubSubEventHooks.triggerDisconnectEvent(this, codes, message, byRemoteHost);
		TwitchReconnectManager.getTwitchReconnectThread().addTwitchPubSubClient(this);
	}

	public void reconnectClient() throws InterruptedException {
		this.reconnectBlocking();
	}

	@Override
	public void onError(Exception e) {
		TwitchPubSubEventHooks.triggerErrorEvent(this, "onError method", e);
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

	public int getChannelID() {
		return channelID;
	}

}
