package com.mjr.twitchframework.kraken.objects.users;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.objects.KrakenChannel;

public class UserFollowedChannel {
	@SerializedName("created_at")
	private Date createdAt;
	private boolean notifications;
	private KrakenChannel channel;

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isNotifications() {
		return notifications;
	}

	public KrakenChannel getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "UserFollowedChannel [createdAt=" + createdAt + ", notifications=" + notifications + ", channel=" + channel + "]";
	}
}
