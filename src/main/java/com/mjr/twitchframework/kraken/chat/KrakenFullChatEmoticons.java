package com.mjr.twitchframework.kraken.chat;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.KrakenLinks;

public class KrakenFullChatEmoticons {
	@SerializedName("_links")
	private KrakenLinks links;

	@SerializedName("emoticons")
	private List<KrakenChatEmoticon> emoticons = null;

	public KrakenLinks getLinks() {
		return links;
	}

	public List<KrakenChatEmoticon> getEmoticons() {
		return emoticons;
	}

	public List<String> getEmoticonsRegexs() {
		List<String> emotes = new ArrayList<String>();
		for (KrakenChatEmoticon emote : this.getEmoticons()) {
			emotes.add(emote.getRegex());
		}
		return emotes;
	}

	@Override
	public String toString() {
		return "KrakenFullChatEmoticons{" +
				"links=" + links +
				", emoticons=" + emoticons +
				'}';
	}
}
