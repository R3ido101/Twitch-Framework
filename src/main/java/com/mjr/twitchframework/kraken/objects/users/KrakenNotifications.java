package com.mjr.twitchframework.kraken.objects.users;

import com.google.gson.annotations.SerializedName;

public class KrakenNotifications {
	@SerializedName("email")
	private Boolean email;

	@SerializedName("push")
	private Boolean push;

	public Boolean getEmail() {
		return email;
	}

	public Boolean getPush() {
		return push;
	}

	@Override
	public String toString() {
		return "Notifications{" +
				"email=" + email +
				", push=" + push +
				'}';
	}
}
