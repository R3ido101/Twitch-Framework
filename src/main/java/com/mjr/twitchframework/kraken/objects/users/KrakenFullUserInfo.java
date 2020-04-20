package com.mjr.twitchframework.kraken.objects.users;

import com.google.gson.annotations.SerializedName;

public class KrakenFullUserInfo {
	@SerializedName("_id")
	public Integer id;

	@SerializedName("bio")
	public String bio;

	@SerializedName("created_at")
	public String createdAt;

	@SerializedName("display_name")
	public String displayName;

	@SerializedName("email")
	public String email;

	@SerializedName("email_verified")
	public Boolean emailVerified;

	@SerializedName("logo")
	public String logo;

	@SerializedName("name")
	public String name;

	@SerializedName("notifications")
	public KrakenNotifications notifications;

	@SerializedName("partnered")
	public Boolean partnered;

	@SerializedName("twitter_connected")
	public Boolean twitterConnected;

	@SerializedName("type")
	public String type;

	@SerializedName("updated_at")
	public String updatedAt;

	public Integer getId() {
		return id;
	}

	public String getBio() {
		return bio;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public KrakenNotifications getNotifications() {
		return notifications;
	}

	public Boolean getPartnered() {
		return partnered;
	}

	public Boolean getTwitterConnected() {
		return twitterConnected;
	}

	public String getType() {
		return type;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return "KrakenFullUserInfo{" +
				"id=" + id +
				", bio='" + bio + '\'' +
				", createdAt='" + createdAt + '\'' +
				", displayName='" + displayName + '\'' +
				", email='" + email + '\'' +
				", emailVerified=" + emailVerified +
				", logo='" + logo + '\'' +
				", name='" + name + '\'' +
				", notifications=" + notifications +
				", partnered=" + partnered +
				", twitterConnected=" + twitterConnected +
				", type='" + type + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				'}';
	}
}
