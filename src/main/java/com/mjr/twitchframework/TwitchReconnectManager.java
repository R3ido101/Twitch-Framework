package com.mjr.twitchframework;

public class TwitchReconnectManager {
	private static TwitchReconnectThread twitchReconnectThread;

	public static TwitchReconnectThread getTwitchReconnectThread() {
		return twitchReconnectThread;
	}
	
	public static void initTwitchReconnectThreadIfDoesntExist() {
		if(twitchReconnectThread == null) {
			twitchReconnectThread = new TwitchReconnectThread(2, 5);
			twitchReconnectThread.start();
		}
	}
}
