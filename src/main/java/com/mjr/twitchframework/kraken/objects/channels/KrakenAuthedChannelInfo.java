package com.mjr.twitchframework.kraken.objects.channels;

import com.google.gson.annotations.SerializedName;

public class KrakenAuthedChannelInfo {
	@SerializedName("mature")
	private Boolean mature;

	@SerializedName("status")
	private String status;

	@SerializedName("broadcaster_language")
	private String broadcasterLanguage;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("game")
	private String game;

	@SerializedName("language")
	private String language;

	@SerializedName("_id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("partner")
	private Boolean partner;

	@SerializedName("logo")
	private String logo;

	@SerializedName("video_banner")
	private String videoBanner;

	@SerializedName("profile_banner")
	private Object profileBanner;

	@SerializedName("profile_banner_background_color")
	private Object profileBannerBackgroundColor;

	@SerializedName("url")
	private String url;

	@SerializedName("views")
	private Integer views;

	@SerializedName("followers")
	private Integer followers;

	@SerializedName("broadcaster_type")
	private String broadcasterType;

	@SerializedName("stream_key")
	private String streamKey;

	@SerializedName("email")
	private String email;

	public Boolean getMature() {
		return mature;
	}

	public String getStatus() {
		return status;
	}

	public String getBroadcasterLanguage() {
		return broadcasterLanguage;
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

	public String getId() {
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

	public Boolean getPartner() {
		return partner;
	}

	public String getLogo() {
		return logo;
	}

	public String getVideoBanner() {
		return videoBanner;
	}

	public Object getProfileBanner() {
		return profileBanner;
	}

	public Object getProfileBannerBackgroundColor() {
		return profileBannerBackgroundColor;
	}

	public String getUrl() {
		return url;
	}

	public Integer getViews() {
		return views;
	}

	public Integer getFollowers() {
		return followers;
	}

	public String getBroadcasterType() {
		return broadcasterType;
	}

	public String getStreamKey() {
		return streamKey;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "KrakenAuthedChannelInfo{" +
				"mature=" + mature +
				", status='" + status + '\'' +
				", broadcasterLanguage='" + broadcasterLanguage + '\'' +
				", displayName='" + displayName + '\'' +
				", game='" + game + '\'' +
				", language='" + language + '\'' +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", createdAt='" + createdAt + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				", partner=" + partner +
				", logo='" + logo + '\'' +
				", videoBanner='" + videoBanner + '\'' +
				", profileBanner=" + profileBanner +
				", profileBannerBackgroundColor=" + profileBannerBackgroundColor +
				", url='" + url + '\'' +
				", views=" + views +
				", followers=" + followers +
				", broadcasterType='" + broadcasterType + '\'' +
				", streamKey='" + streamKey + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
