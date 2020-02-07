package com.mjr.twitchframework.kraken.objects.streams;

import com.google.gson.annotations.SerializedName;

public class StreamPreviewImages {
	@SerializedName("small")
	private String small;
	@SerializedName("medium")
	private String medium;
	@SerializedName("large")
	private String large;
	@SerializedName("template")
	private String template;

	public String getSmall() {
		return small;
	}

	public String getMedium() {
		return medium;
	}

	public String getLarge() {
		return large;
	}

	public String getTemplate() {
		return template;
	}

	@Override
	public String toString() {
		return "StreamPreviewImages [small=" + small + ", medium=" + medium + ", large=" + large + ", template=" + template + "]";
	}
}
