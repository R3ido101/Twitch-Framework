package com.mjr.twitchframework.helix.objects;

import com.google.gson.annotations.SerializedName;

public class GameData {
	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("box_art_url")
	private String boxArtUrl;

	public GameData(String id, String name, String boxArtUrl) {
		this.id = id;
		this.name = name;
		this.boxArtUrl = boxArtUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBoxArtUrl() {
		return boxArtUrl;
	}
}
