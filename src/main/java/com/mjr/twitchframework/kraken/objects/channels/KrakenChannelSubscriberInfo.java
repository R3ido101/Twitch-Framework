package com.mjr.twitchframework.kraken.objects.channels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class KrakenChannelSubscriberInfo {
	@SerializedName("_total")
	private Integer total;

	@SerializedName("subscriptions")
	private List<KrakenSubscription> subscriptions;

	public Integer getTotal() {
		return total;
	}

	public List<KrakenSubscription> getSubscriptions() {
		return subscriptions;
	}

	@Override
	public String toString() {
		return "KrakenChannelSubscriberInfo{" +
				"total=" + total +
				", subscriptions=" + subscriptions +
				'}';
	}
}
