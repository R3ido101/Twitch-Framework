package com.mjr.twitchframework.kraken;

import com.google.gson.annotations.SerializedName;

public class KrakenLinks {
	@SerializedName("self")
	private String self;

	public String getSelf() {
		return self;
	}

	@Override
	public String toString() {
		return "KrakenLinks{" +
				"self='" + self + '\'' +
				'}';
	}
}
