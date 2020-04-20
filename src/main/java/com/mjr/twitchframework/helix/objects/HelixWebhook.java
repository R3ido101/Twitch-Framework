
package com.mjr.twitchframework.helix.objects;

import com.google.gson.annotations.SerializedName;

public class HelixWebhook {

	@SerializedName("topic")
	private String topic;

	@SerializedName("callback")
	private String callback;

	@SerializedName("expires_at")
	private String expiresAt;

	public String getTopic() {
		return topic;
	}

	public String getCallback() {
		return callback;
	}

	public String getExpiresAt() {
		return expiresAt;
	}
}
