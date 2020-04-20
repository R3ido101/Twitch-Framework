package com.mjr.twitchframework.kraken.chat;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KrakenChatEmoticon {
	@SerializedName("id")
	private Integer id;

	@SerializedName("regex")
	private String regex;

	@SerializedName("images")
	private List<KrakenChatImage> images = null;

	public Integer getId() {
		return id;
	}

	public String getRegex() {
		return regex;
	}

	public List<KrakenChatImage> getImages() {
		return images;
	}

	@Override
	public String toString() {
		return "KrakenChatEmoticon{" +
				"id=" + id +
				", regex='" + regex + '\'' +
				", images=" + images +
				'}';
	}
}
