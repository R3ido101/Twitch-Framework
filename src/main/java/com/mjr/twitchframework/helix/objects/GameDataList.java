package com.mjr.twitchframework.helix.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GameDataList {
	@SerializedName("data")
	private List<GameData> data = null;

	public GameDataList(List<GameData> data) {
		this.data = data;
	}

	public List<GameData> getData() {
		return data;
	}
}
