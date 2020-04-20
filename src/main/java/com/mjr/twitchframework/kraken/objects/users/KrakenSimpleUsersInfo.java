package com.mjr.twitchframework.kraken.objects.users;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KrakenSimpleUsersInfo {
	@SerializedName("_total")
	private Integer total;
	@SerializedName("users")
	private List<KrakenSimpleUserInfo> users;

	public Integer getTotal() {
		return total;
	}

	public List<KrakenSimpleUserInfo> getUsers() {
		return users;
	}
}
