package com.mjr.twitchframework.kraken.objects.channels;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.objects.users.KrakenSimpleUserInfo;

public class KrakenChannelsFollowedUser {
	@SerializedName("created_at")
	private Date createdAt;
	private boolean notifications;
	private KrakenSimpleUserInfo user;

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isNotifications() {
		return notifications;
	}

	public KrakenSimpleUserInfo getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "ChannelFollowedUser [createdAt=" + createdAt + ", notifications=" + notifications + ", user=" + user.toString() + "]";
	}
}
