package com.mjr.twitchframework.auth.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.mjr.twitchframework.auth.TwitchOAuthManager.TwitchScopes;

public class TwitchOAuthBearerToken {
	@SerializedName("access_token")
	private String accessToken;
	@SerializedName("refresh_token")
	private String refreshToken;
	@SerializedName("expires_in")
	private int expiresIn = 0;
	@SerializedName("scope")
	private JsonArray scopes;
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

	public List<TwitchScopes> getScopes() {
		List<TwitchScopes> list = new ArrayList<TwitchScopes>();
		for (JsonElement scope : this.scopes)
			list.add(TwitchScopes.getByName(scope.getAsString()));
		return list;
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
