package com.mjr.twitchframework.helix.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HelixStreamData {
	@SerializedName("id")
	private String id;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("game_id")
	private String gameId;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	@SerializedName("viewer_count")
	private Integer viewerCount;

	@SerializedName("started_at")
	private String startedAt;

	@SerializedName("language")
	private String language;

	@SerializedName("thumbnail_url")
	private String thumbnailUrl;

	@SerializedName("tag_ids")
	private List<String> tagIds = null;

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getGameId() {
		return gameId;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public Integer getViewerCount() {
		return viewerCount;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public String getLanguage() {
		return language;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public List<String> getTagIds() {
		return tagIds;
	}
}
