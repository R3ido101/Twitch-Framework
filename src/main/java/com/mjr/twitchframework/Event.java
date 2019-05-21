package com.mjr.twitchframework;

public class Event {

	public enum PubSubEventType {
		MESSAGE("Message"), ERROR("Error"), CONNECT("Connect"), DISCONNECT("Disconnect");

		public final String name;

		PubSubEventType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public PubSubEventType type;

	public Event(PubSubEventType type) {
		super();
		this.type = type;
	}

	public enum IRCEventType {
		MESSAGE("Message"), MESSAGEEXTRA("Message"), NOTICE("Notice"), PRIVATEMESSAGE("PrivateMessage"), UNKNOWN("Unknown"), JOIN("Join"), PART("Part"), ERROR("Error"), CONNECT("Connect"), DISCONNECT("Disconnect");

		public final String name;

		IRCEventType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public IRCEventType typeIRC;

	public Event(IRCEventType type) {
		super();
		this.typeIRC = type;
	}
}
