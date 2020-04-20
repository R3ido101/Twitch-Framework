package com.mjr.twitchframework.helix.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HelixWebHookSubscriptions {
	@SerializedName("total")
	private Integer total;

	@SerializedName("data")
	private List<HelixWebhook> data = null;

	@SerializedName("pagination")
	private HelixPagination pagination;

	public Integer getTotal() {
		return total;
	}

	public List<HelixWebhook> getData() {
		return data;
	}

	public HelixPagination getPagination() {
		return pagination;
	}
}
