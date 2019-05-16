package com.mjr.twitchframework;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public abstract class BasicWebSocketClient extends WebSocketClient {

	public BasicWebSocketClient(URI uri) {
		super(uri);
	}

	@Override
	public abstract void onOpen(ServerHandshake serverHandshake);

	@Override
	public abstract void onMessage(String message);

	@Override
	public abstract void onClose(int i, String s, boolean b);

	@Override
	public abstract void onError(Exception e);
}
