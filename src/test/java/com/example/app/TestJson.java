package com.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.*;

public class TestJson {

	static String articlesUsersUrl = "https://jsonmock.hackerrank.com/api/article_users?username=";
	static String articlesUrl = "https://jsonmock.hackerrank.com/api/articles?";

	public static void main(String[] args) throws IOException {
		List<String> history = getAuthorHistory("epaga");
		for (String s : history) {
			System.out.println(s);
		}
	}

	public static List<String> getAuthorHistory(String author) throws IOException {
		List<String> history = new ArrayList<>();

		URL url = new URL(articlesUsersUrl + author);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		if (200 == connection.getResponseCode()) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder content = new StringBuilder();
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				content.append(inputLine);
			}
			bufferedReader.close();

			System.out.println(content);
			JSONObject obj = new JSONObject(content.toString());
			int per_page = obj.getInt("per_page");
			int total = obj.getInt("total");
			int total_pages = obj.getInt("total_pages");
			JSONArray aricles = obj.getJSONArray("data");
			Iterator iterator = aricles.iterator();
			String about;
			System.out.println(aricles.length());
			while (iterator.hasNext()) {
				JSONObject article = (JSONObject) iterator.next();
				about = article.getString("about");
				if (null != about && !about.isEmpty()) {
					history.add(about);
				}
			}

			history.addAll(getArticleTitles(author));
			// ==========

		}
		return history;
	}

	public static List<String> getArticleTitles(String author) throws JSONException, IOException {
		List<String> titles = new ArrayList<>();

		int page = 1;
		int totalPage = 1;
		StringBuilder response = new StringBuilder();

		while (page <= totalPage) {
			URL obj = new URL(articlesUrl + author + "&page=" + page);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			System.out.println(response);
			JSONObject jsonResponse = new JSONObject(response.toString());
			totalPage = jsonResponse.getInt("total_pages");
			JSONArray data = jsonResponse.getJSONArray("data");
			System.out.println(data);
			Iterator iterator = data.iterator();
			Object objTitle;
			while (iterator.hasNext()) {
				JSONObject article = (JSONObject) iterator.next();
				objTitle = article.get("title");
				if (null != objTitle) {
					titles.add(objTitle.toString());
					
				}else if( null !=article.get("story_title")) {
					titles.add(article.getString("story_title"));
				}
			}
			page++;
		}		
		return titles;
	}
}
