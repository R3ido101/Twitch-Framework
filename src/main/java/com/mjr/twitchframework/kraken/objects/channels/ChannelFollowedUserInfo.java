package com.mjr.twitchframework.kraken.objects.channels;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class ChannelFollowedUserInfo {
	@SerializedName("display_name")
	private String displayName;
	@SerializedName("_id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("type")
	private String type;
	@SerializedName("bio")
	private String bio;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("logo")
	private String logo;

	public String getDisplayName() {
		return displayName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getBio() {
		return bio;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public String getLogo() {
		return logo;
	}

	@Override
	public String toString() {
		return "ChannelFollowedUsersChannel [displayName=" + displayName + ", id=" + id + ", name=" + name + ", type=" + type + ", bio=" + bio + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", logo=" + logo + "]";
	}
}
