package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mjr.twitchframework.Event;

public class TwitchIRCManager {

	private static List<TwitchIRCClient> clients = new ArrayList<TwitchIRCClient>();
	private static List<Event> listeners = new ArrayList<Event>();

	public static List<TwitchIRCClient> getClients() {
		return clients;
	}

	public static TwitchIRCClient getClientByIndex(int index) {
		return clients.get(index);
	}

	public static void sendMessageByChannelName(String channelName, String message) {
		for (TwitchIRCClient client : getClients()) {
			if (client.getChannelsList().contains(channelName))
				client.sendMessage("#" + channelName, message);
		}
	}

	public static void setClients(List<TwitchIRCClient> clients) {
		TwitchIRCManager.clients = clients;
	}

	public static void addClient(TwitchIRCClient client) {
		TwitchIRCManager.clients.add(client);
	}

	public static void removeClient(TwitchIRCClient client) {
		TwitchIRCManager.clients.remove(client);
	}

	public static void disconnectClient(TwitchIRCClient client) {
		client.disconnect();
		TwitchIRCManager.clients.remove(client);
	}

	public static void registerEventHandler(Event event) {
		TwitchIRCManager.listeners.add(event);
	}

	public static List<Event> getEventListeners() {
		return listeners;
	}

	public static void setupClients(List<String> channels, String username, String password, int limit, boolean verbose) throws IOException {
		int numOfConnections = 0;
		numOfConnections = (int) Math.ceil((channels.size() / limit));

		if (channels.size() < 50)
			numOfConnections = 1;

		for (int i = 0; i < numOfConnections; i++) {
			TwitchIRCClient newClient = new TwitchIRCClient();
			newClient.setVerbose(verbose);
			newClient.connectToTwitch(username, password);
			TwitchIRCManager.addClient(newClient);
		}
		for (int i = 0; i < numOfConnections; i++) {
			int channelNum = 0;
			for (int j = 0; j < channels.size(); j++) {
				channelNum++;
				getClients().get(i).addChannelWithConnect(channels.get(j));
				if (channelNum == limit)
					break;
			}
		}
	}

	public static void addChannel(String channelName, int limit) {
		for(TwitchIRCClient client : clients) {
			if(client.getChannelsList().size() < limit) {
				client.addChannelWithConnect(channelName);
				return;
			}
		}
	}
	
	public static void removeChannel(String channelName, int limit) {
		for(TwitchIRCClient client : clients) {
			if(client.getChannelsList().size() < limit) {
				client.removeChannelWithDisconnect(channelName);
				return;
			}
		}
	}
}
