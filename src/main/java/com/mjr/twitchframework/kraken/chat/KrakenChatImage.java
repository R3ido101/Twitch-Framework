package com.mjr.twitchframework.kraken.chat;

import com.google.gson.annotations.SerializedName;

public class KrakenChatImage {
	@SerializedName("width")
	private Integer width;

	@SerializedName("height")
	private Integer height;

	@SerializedName("url")
	private String url;

	@SerializedName("emoticon_set")
	private Integer emoticonSet;

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public String getUrl() {
		return url;
	}

	public Integer getEmoticonSet() {
		return emoticonSet;
	}

	@Override
	public String toString() {
		return "KrakenChatImage{" +
				"width=" + width +
				", height=" + height +
				", url='" + url + '\'' +
				", emoticonSet=" + emoticonSet +
				'}';
	}
}
