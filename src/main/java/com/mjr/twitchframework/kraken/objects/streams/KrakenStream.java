package com.mjr.twitchframework.kraken.objects.streams;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.objects.KrakenChannel;

public class KrakenStream {
	@SerializedName("_id")
	private Long id;
	@SerializedName("game")
	private String game;
	@SerializedName("broadcast_platform")
	private String broadcastPlatform;
	@SerializedName("community_id")
	private String communityId;
	@SerializedName("community_ids")
	private List<Object> communityIds;
	@SerializedName("viewers")
	private int viewers;
	@SerializedName("video_height")
	private int videoHeight;
	@SerializedName("average_fps")
	private int averageFps;
	@SerializedName("delay")
	private int delay;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("is_playlist")
	private boolean isPlaylist;
	@SerializedName("stream_type")
	private String streamType;
	@SerializedName("preview")
	private StreamPreviewImages preview;
	@SerializedName("channel")
	private KrakenChannel channel;

	public Long getId() {
		return id;
	}

	public String getGame() {
		return game;
	}

	public String getBroadcastPlatform() {
		return broadcastPlatform;
	}

	public String getCommunityId() {
		return communityId;
	}

	public List<Object> getCommunityIds() {
		return communityIds;
	}

	public int getViewers() {
		return viewers;
	}

	public int getVideoHeight() {
		return videoHeight;
	}

	public int getAverageFps() {
		return averageFps;
	}

	public int getDelay() {
		return delay;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isPlaylist() {
		return isPlaylist;
	}

	public String getStreamType() {
		return streamType;
	}

	public StreamPreviewImages getPreview() {
		return preview;
	}

	public KrakenChannel getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "KrakenStream [id=" + id + ", game=" + game + ", broadcastPlatform=" + broadcastPlatform + ", communityId=" + communityId + ", communityIds=" + communityIds + ", viewers=" + viewers + ", videoHeight=" + videoHeight + ", averageFps="
				+ averageFps + ", delay=" + delay + ", createdAt=" + createdAt + ", isPlaylist=" + isPlaylist + ", streamType=" + streamType + ", preview=" + preview + ", channel=" + channel + "]";
	}
}
