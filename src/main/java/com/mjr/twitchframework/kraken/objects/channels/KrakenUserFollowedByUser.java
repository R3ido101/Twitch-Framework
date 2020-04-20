package com.mjr.twitchframework.kraken.objects.channels;

import com.google.gson.annotations.SerializedName;

public class KrakenUserFollowedByUser {
	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("notifications")
	private Boolean notifications;

	@SerializedName("channel")
	private KrakenSimpleChannelInfo channel;

	public String getCreatedAt() {
		return createdAt;
	}

	public Boolean getNotifications() {
		return notifications;
	}

	public KrakenSimpleChannelInfo getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "UserFollowedByUser{" +
				"createdAt='" + createdAt + '\'' +
				", notifications=" + notifications +
				", channel=" + channel +
				'}';
	}
}
