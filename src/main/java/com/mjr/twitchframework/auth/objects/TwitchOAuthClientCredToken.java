package com.mjr.twitchframework.auth.objects;

import com.google.gson.annotations.SerializedName;

public class TwitchOAuthClientCredToken {
	@SerializedName("access_token")
	private String accessToken;
	@SerializedName("refresh_token")
	private String refreshToken;
	@SerializedName("expires_in")
	private int expiresIn = 0;
	@SerializedName("token_type")
	private String tokenType;

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public boolean checkAccessTokenLength() {
		if (this.accessToken.length() == 30)
			return true;
		return false;
	}

	public boolean checkRefreshTokenLength() {
		if (this.refreshToken.length() == 50)
			return true;
		return false;
	}
}
