
package com.mjr.twitchframework.helix.objects;

import com.google.gson.annotations.SerializedName;

public class HelixPagination {

	@SerializedName("cursor")
	private String cursor;

	public String getCursor() {
		return cursor;
	}
}
