package com.mjr.twitchframework.helix;

public class HelixEndpoints {
	public static String webHook() {
		return "https://api.twitch.tv/helix/webhooks/hub";
	}

	public static String webHookSubscriptions() {
		return "https://api.twitch.tv/helix/webhooks/subscriptions";
	}
}
