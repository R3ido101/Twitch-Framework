package com.mjr.twitchframework.pubsub;

public class ReconnectThread extends Thread {

	private TwitchWebsocketClient client;

	public ReconnectThread(TwitchWebsocketClient client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			client.reconnectClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
