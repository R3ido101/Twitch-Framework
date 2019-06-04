package com.mjr.twitchframework;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.mjr.twitchframework.pubsub.TwitchPubSubManager;
import com.mjr.twitchframework.pubsub.TwitchWebsocketClient;
import com.mjr.twitchframework.pubsub.events.PubSubConnectEvent;
import com.mjr.twitchframework.pubsub.events.PubSubMessageEvent;

public class TwitchFramework {

	public static void main(final String[] args) throws InterruptedException {

		HashMap<String, List<String>> requests = new HashMap<String, List<String>>();
		requests.put(args[0], new ArrayList<String>(Arrays.asList("channel-bits-events-v1.32907202", "channel-subscribe-events-v1.32907202")));
		try {
			TwitchPubSubManager.addClient(new TwitchWebsocketClient(requests, true));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		TwitchPubSubManager.addListener(new PubSubMessageEvent() {
			@Override
			public void onEvent(PubSubMessageEvent event) {
				System.out.println("Event Triggered: " + event.message);
			}
		});

		TwitchPubSubManager.addListener(new PubSubConnectEvent() {
			@Override
			public void onEvent(PubSubConnectEvent event) {
				System.out.println("Event Triggered: " + event.serverHandshake.getHttpStatus());
			}
		});

		while (true) {
			TwitchPubSubManager.tick();
			if (TwitchPubSubManager.getClients().size() != 0)
				Thread.sleep(120000);
		}
	}
}
