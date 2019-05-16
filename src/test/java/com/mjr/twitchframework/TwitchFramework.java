package com.mjr.twitchframework;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.java_websocket.handshake.ServerHandshake;

import com.mjr.twitchframework.pubsub.TwitchPubSubManager;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;
import com.mjr.twitchframework.pubsub.events.PubSubConnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubMessageEvent;

public class TwitchFramework {

	public static TwitchPubSubManager manager = new TwitchPubSubManager();
	
	public static void main(final String[] args) throws InterruptedException {

		HashMap<String, List<String>> requests = new HashMap<String, List<String>>();
		requests.put(args[0], new ArrayList<String>(Arrays.asList("channel-bits-events-v1.32907202", "channel-subscribe-events-v1.32907202")));
		try {
			manager.addClient(new TwitchWebsocketClient(requests, true));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		manager.addListener(new PubSubMessageEvent() {
			@Override
			public void onEvent(String message) {
				System.out.println("Event Triggered: " + message);
			}
		});
		
		manager.addListener(new PubSubConnectEvent() {
			@Override
			public void onEvent(ServerHandshake serverHandshake) {
				System.out.println("Event Triggered: " + serverHandshake.getHttpStatus());
			}
		});
		
		while(true) {
			manager.tick();
			if(manager.getClients().size() != 0)
				Thread.sleep(120000);
		}
	}
}
