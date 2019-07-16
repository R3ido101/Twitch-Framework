
package com.mjr.twitchframework.irc.events.twitch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mjr.twitchframework.Event;

public class IRCMessageEventBase extends Event {
	private static Pattern pattern = Pattern.compile("^(?:@(?<tags>.+?) )?(?<clientName>.+?)(?: (?<command>[A-Z0-9]+) )(?:#(?<channel>.*?) ?)?(?<payload>[:\\-\\+](?<message>.+))?$");

	public String message;
	public String channelName;
	public String command;
	public Map<String, String> tags;
	
	public IRCMessageEventBase(String rawLine, IRCEventType eventType) {
		super(eventType);
		if(rawLine != null)
			this.parseRawMessage(rawLine);
	}

	private void parseRawMessage(String raw) {
		Matcher match = pattern.matcher(raw);
		if (match.matches()) {
			this.message = match.group("message");
			this.channelName = match.group("channel");
			this.command = match.group("command");
			this.tags = parseTags(match.group("tags"));
		}
	}

	private Map<String, String> parseTags(String raw) {
		HashMap<String, String> map = new HashMap<>();
		if (!raw.isEmpty()) {
			for (String tag : raw.split(";")) {
				String[] val = tag.split("=");
				final String key = val[0];
				String value = (val.length > 1) ? val[1] : null;
				map.put(key, value);
			}
		}
		return Collections.unmodifiableMap(map);
	}
}
