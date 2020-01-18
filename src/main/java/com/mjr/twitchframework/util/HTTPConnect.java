package com.mjr.twitchframework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class HTTPConnect {

	public static String getResult(HttpURLConnection request) throws IOException {
		String result = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			result += line;
		}
		reader.close();
		return result;
	}

	public static HttpURLConnection getRequest(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		return connection;
	}

	public static HttpURLConnection postRequest(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.connect();
		return connection;
	}

	public static HttpURLConnection getRequestWithHeader(String urlString, HashMap<String, String> headers) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		for (String key : headers.keySet()) {
			String value = headers.get(key);
			connection.setRequestProperty(key, value);
		}
		connection.connect();
		return connection;
	}

	public static HttpURLConnection postRequestWithHeader(String urlString, HashMap<String, String> headers) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		for (String key : headers.keySet()) {
			String value = headers.get(key);
			connection.setRequestProperty(key, value);
		}
		connection.connect();
		return connection;
	}

	public static HttpURLConnection postRequestWithBody(String urlString, HashMap<String, String> bodies) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		OutputStream os = connection.getOutputStream();
		Gson gson = new GsonBuilder().create();
		JsonObject json = new JsonObject();
		for (String key : bodies.keySet()) {
			json.addProperty(key, bodies.get(key));
		}
		String jsonString = gson.toJson(json);
		os.write(jsonString.getBytes("UTF-8"));

		connection.connect();
		return connection;
	}

	public static HttpURLConnection postRequestWithHeaderBody(String urlString, HashMap<String, String> headers, HashMap<String, String> bodies) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		for (String key : headers.keySet()) {
			String value = headers.get(key);
			connection.setRequestProperty(key, value);
		}
		OutputStream os = connection.getOutputStream();
		Gson gson = new GsonBuilder().create();
		JsonObject json = new JsonObject();
		for (String key : bodies.keySet()) {
			json.addProperty(key, bodies.get(key));
		}
		String jsonString = gson.toJson(json);
		os.write(jsonString.getBytes("UTF-8"));
		connection.connect();
		return connection;
	}
}
