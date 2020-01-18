package com.mjr.twitchframework.auth.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mjr.twitchframework.auth.TwitchOAuthManager.TwitchScopes;

public class TwitchAuthedUserInfo {
	public String client_id;
	public String login;
	public String user_id;
	public int expires_in = 0;
	private JsonArray scopes;

	public List<TwitchScopes> getScopes() {
		List<TwitchScopes> list = new ArrayList<TwitchScopes>();
		for (JsonElement scope : this.scopes)
			list.add(TwitchScopes.getByName(scope.getAsString()));
		return list;
	}

	@Override
	public String toString() {
		return "client_id: " + this.client_id + " | login: " + this.login + " | user_id: " + this.user_id + " | expires_in: " + this.expires_in + " | scopes: " + this.scopes.toString();
	}
}
