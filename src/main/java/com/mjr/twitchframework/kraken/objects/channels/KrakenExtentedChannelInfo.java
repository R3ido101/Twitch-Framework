package com.mjr.twitchframework.kraken.objects.channels;

import com.google.gson.annotations.SerializedName;

public class KrakenExtentedChannelInfo {
	@SerializedName("mature")
	private boolean mature;
	@SerializedName("status")
	private String status;
	@SerializedName("broadcaster_language")
	private String broadcasterLanguage;
	@SerializedName("broadcaster_software")
	private String broadcasterSoftware;
	@SerializedName("display_name")
	private String displayName;
	@SerializedName("game")
	private String game;
	@SerializedName("language")
	private String language;
	@SerializedName("_id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("updated_at")
	private String updatedAt;
	@SerializedName("partner")
	private boolean partner;
	@SerializedName("logo")
	private String logo;
	@SerializedName("video_banner")
	private String videoBanner;
	@SerializedName("profile_banner")
	private String profileBanner;
	@SerializedName("profile_banner_background_color")
	private String profileBannerBackgroundColor;
	@SerializedName("url")
	private String url;
	@SerializedName("views")
	private int views;
	@SerializedName("followers")
	private int followers;
	@SerializedName("broadcaster_type")
	private String broadcasterType;
	@SerializedName("description")
	private String description;
	@SerializedName("private_video")
	private boolean privateVideo;
	@SerializedName("privacy_options_enabled")
	private boolean privacyOptionsEnabled;

	public boolean isMature() {
		return mature;
	}

	public String getStatus() {
		return status;
	}

	public String getBroadcasterLanguage() {
		return broadcasterLanguage;
	}

	public String getBroadcasterSoftware() {
		return broadcasterSoftware;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getGame() {
		return game;
	}

	public String getLanguage() {
		return language;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public boolean isPartner() {
		return partner;
	}

	public String getLogo() {
		return logo;
	}

	public String getVideoBanner() {
		return videoBanner;
	}

	public String getProfileBanner() {
		return profileBanner;
	}

	public String getProfileBannerBackgroundColor() {
		return profileBannerBackgroundColor;
	}

	public String getUrl() {
		return url;
	}

	public int getViews() {
		return views;
	}

	public int getFollowers() {
		return followers;
	}

	public String getBroadcasterType() {
		return broadcasterType;
	}

	public String getDescription() {
		return description;
	}

	public boolean iPrivateVideo() {
		return privateVideo;
	}

	public boolean isPrivacyOptionsEnabled() {
		return privacyOptionsEnabled;
	}

	@Override
	public String toString() {
		return "Channel [mature=" + mature + ", status=" + status + ", broadcasterLanguage=" + broadcasterLanguage + ", broadcasterSoftware=" + broadcasterSoftware + ", displayName=" + displayName + ", game=" + game + ", language="
				+ language + ", id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", partner=" + partner + ", logo=" + logo + ", videoBanner=" + videoBanner + ", profileBanner=" + profileBanner
				+ ", profileBannerBackgroundColor=" + profileBannerBackgroundColor + ", url=" + url + ", views=" + views + ", followers=" + followers + ", broadcasterType=" + broadcasterType + ", description=" + description + ", privateVideo="
				+ privateVideo + ", privacyOptionsEnabled=" + privacyOptionsEnabled + "]";
	}
}
