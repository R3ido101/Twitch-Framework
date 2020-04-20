package com.mjr.twitchframework.kraken.objects.channels;

import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.kraken.objects.users.KrakenSimpleUserInfo;

public class KrakenSubscription {
	@SerializedName("_id")
	private String id;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("sub_plan")
	private String subPlan;

	@SerializedName("sub_plan_name")
	private String subPlanName;

	@SerializedName("user")
	private KrakenSimpleUserInfo user;

	public String getId() {
		return id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getSubPlan() {
		return subPlan;
	}

	public String getSubPlanName() {
		return subPlanName;
	}

	public KrakenSimpleUserInfo getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "KrakenSubscription{" +
				"id='" + id + '\'' +
				", createdAt='" + createdAt + '\'' +
				", subPlan='" + subPlan + '\'' +
				", subPlanName='" + subPlanName + '\'' +
				", user=" + user +
				'}';
	}
}
