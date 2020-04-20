package com.mjr.twitchframework.kraken.objects.users;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.objects.channels.KrakenFullChannelInfo;

public class KrakenUsersFollowedChannel {
	@SerializedName("created_at")
	private Date createdAt;
	private boolean notifications;
	private KrakenFullChannelInfo channel;

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isNotifications() {
		return notifications;
	}

	public KrakenFullChannelInfo getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "UserFollowedChannel [createdAt=" + createdAt + ", notifications=" + notifications + ", channel=" + channel + "]";
	}
}
