package com.mjr.twitchframework.irc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mjr.twitchframework.Event;

public class TwitchIRCManager {

	public static int numberOfClients = 0;

	private static List<TwitchIRCClient> clients = new ArrayList<TwitchIRCClient>();
	private static List<Event> listeners = new ArrayList<Event>();

	public static List<TwitchIRCClient> getClients() {
		return clients;
	}

	public static TwitchIRCClient getClientByIndex(int index) {
		return clients.get(index);
	}

	public static TwitchIRCClient getClientByChannelName(String channelName) {
		for (TwitchIRCClient client : getClients()) {
			if (client.getChannelsList().contains(channelName))
				return client;
		}
		return null;
	}

	public static void sendMessageByChannelName(String channelName, String message) {
		getClientByChannelName(channelName).sendMessage("#" + channelName, message);
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
		try {
			int channelsSize = channels.size();
			int numOfConnections = 0;
			numOfConnections = (int) Math.ceil((channels.size() / limit + 1));

			if (channels.size() <= limit)
				numOfConnections = 1;

			for (int i = 0; i < numOfConnections; i++) {
				setupClient(username, password, verbose);
				int channelNum = 0;
				for (int j = 0; j < channels.size(); j++) {
					channelNum++;
					getClients().get(i).addChannelWithConnect(channels.get(j));
					channels.remove(j);
					if (channelNum == limit) {
						break;
					}
				}
			}
			TwitchIRCEventHooks.triggerOnInfoEvent(getClients().size() + " client(s) have been created! For " + channelsSize + " channel(s)! With a limit of " + limit + " per connection!");
		} catch (Exception e) {
			TwitchIRCEventHooks.triggerOnErrorEvent(null, e);
		}
	}

	public static TwitchIRCClient setupClient(String username, String password, boolean verbose) throws IOException {
		try {
			TwitchIRCClient newClient = new TwitchIRCClient(numberOfClients++);
			newClient.setVerbose(verbose);
			newClient.connectToTwitch(username, password);
			TwitchIRCManager.addClient(newClient);
			return newClient;
		} catch (Exception e) {
			TwitchIRCEventHooks.triggerOnErrorEvent(null, e);
		}
		return null;
	}

	public static void addChannel(String channelName, int limit, String username, String password, boolean verbose) throws IOException {
		try {
			for (TwitchIRCClient client : clients) {
				if (client.getChannelsList().size() < limit) {
					client.addChannelWithConnect(channelName);
					TwitchIRCEventHooks.triggerOnInfoEvent("Added channel " + channelName + " to a client");
					return;
				}
			}
			TwitchIRCClient client = setupClient(username, password, verbose);
			client.addChannelWithConnect(channelName);
			TwitchIRCEventHooks.triggerOnInfoEvent("Added channel " + channelName + " to a client");
		} catch (Exception e) {
			TwitchIRCEventHooks.triggerOnErrorEvent(null, e);
		}
	}

	public static void removeChannel(String channelName) {
		try {
			List<TwitchIRCClient> clientsOld = new ArrayList<TwitchIRCClient>();
			clientsOld.addAll(clients);
			for (TwitchIRCClient client : clientsOld) {
				if (client.getChannelsList().contains(channelName)) {
					client.removeChannelWithDisconnect(channelName);
					TwitchIRCEventHooks.triggerOnInfoEvent("Removed channel " + channelName + " from a client");
					if (client.getChannelsList().size() == 0) {
						disconnectClient(client);
						TwitchIRCEventHooks.triggerOnInfoEvent("Closed connection due to having no channels for the connection!");
					}
				}
			}
		} catch (Exception e) {
			TwitchIRCEventHooks.triggerOnErrorEvent(null, e);
		}
	}
}
