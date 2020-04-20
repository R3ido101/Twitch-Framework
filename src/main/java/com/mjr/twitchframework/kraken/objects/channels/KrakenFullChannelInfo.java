package com.mjr.twitchframework.kraken.objects.channels;

import com.google.gson.annotations.SerializedName;

public class KrakenFullChannelInfo {
	@SerializedName("_id")
	private Integer id;

	@SerializedName("background")
	private Object background;

	@SerializedName("banner")
	private Object banner;

	@SerializedName("broadcaster_language")
	private String broadcasterLanguage;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("delay")
	private Object delay;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("followers")
	private Integer followers;

	@SerializedName("game")
	private String game;

	@SerializedName("language")
	private String language;

	@SerializedName("logo")
	private String logo;

	@SerializedName("mature")
	private Boolean mature;

	@SerializedName("name")
	private String name;

	@SerializedName("partner")
	private Boolean partner;

	@SerializedName("profile_banner")
	private String profileBanner;

	@SerializedName("profile_banner_background_color")
	private Object profileBannerBackgroundColor;

	@SerializedName("status")
	private String status;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("url")
	private String url;

	@SerializedName("video_banner")
	private String videoBanner;

	@SerializedName("views")
	private Integer views;

	public Integer getId() {
		return id;
	}

	public Object getBackground() {
		return background;
	}

	public Object getBanner() {
		return banner;
	}

	public String getBroadcasterLanguage() {
		return broadcasterLanguage;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public Object getDelay() {
		return delay;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Integer getFollowers() {
		return followers;
	}

	public String getGame() {
		return game;
	}

	public String getLanguage() {
		return language;
	}

	public String getLogo() {
		return logo;
	}

	public Boolean getMature() {
		return mature;
	}

	public String getName() {
		return name;
	}

	public Boolean getPartner() {
		return partner;
	}

	public String getProfileBanner() {
		return profileBanner;
	}

	public Object getProfileBannerBackgroundColor() {
		return profileBannerBackgroundColor;
	}

	public String getStatus() {
		return status;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public String getUrl() {
		return url;
	}

	public String getVideoBanner() {
		return videoBanner;
	}

	public Integer getViews() {
		return views;
	}

	@Override
	public String toString() {
		return "KrakenFullChannelInfo{" +
				"id=" + id +
				", background=" + background +
				", banner=" + banner +
				", broadcasterLanguage='" + broadcasterLanguage + '\'' +
				", createdAt='" + createdAt + '\'' +
				", delay=" + delay +
				", displayName='" + displayName + '\'' +
				", followers=" + followers +
				", game='" + game + '\'' +
				", language='" + language + '\'' +
				", logo='" + logo + '\'' +
				", mature=" + mature +
				", name='" + name + '\'' +
				", partner=" + partner +
				", profileBanner='" + profileBanner + '\'' +
				", profileBannerBackgroundColor=" + profileBannerBackgroundColor +
				", status='" + status + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				", url='" + url + '\'' +
				", videoBanner='" + videoBanner + '\'' +
				", views=" + views +
				'}';
	}
}
