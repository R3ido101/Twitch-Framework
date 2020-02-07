package com.mjr.twitchframework.kraken.objects.channels;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ChannelFollowedUser {
	@SerializedName("created_at")
	private Date createdAt;
	private boolean notifications;
	private ChannelFollowedUserInfo user;

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isNotifications() {
		return notifications;
	}

	public ChannelFollowedUserInfo getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "ChannelFollowedUser [createdAt=" + createdAt + ", notifications=" + notifications + ", user=" + user.toString() + "]";
	}
}
