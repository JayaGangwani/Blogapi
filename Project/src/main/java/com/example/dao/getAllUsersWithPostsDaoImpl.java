package com.example.dao;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import com.example.model.Address;
import com.example.model.Geo;
import com.example.model.Posts;
import com.example.model.User;

@Repository
public class getAllUsersWithPostsDaoImpl implements getAllUsersWithPostsDao {
	
	public List<User> getAllUsersWithPosts() throws Exception {
		JSONParser jsonparser = new JSONParser();
		try {

			FileReader postsReader = new FileReader("/Users/ASUS/Downloads/posts.json");
			FileReader fileReader = new FileReader("/Users/ASUS/Downloads/users.json");

			List<Posts> postsList = new ArrayList<Posts>();
			Object postsobj = jsonparser.parse(postsReader);
			JSONArray postList = (JSONArray) postsobj;
			for (Object object : postList) {
				postsList.add(parsePostsObject((JSONObject) object));
			}

			List<User> usersList = new ArrayList<User>();
			Object obj = jsonparser.parse(fileReader);
			JSONArray userList = (JSONArray) obj;
			for (Object object : userList) {
				usersList.add(parseUserObject((JSONObject) object));
			}

			for (User user : usersList) {
				for (Posts post : postsList) {
					if (post.getUserId() == user.getId()) {
						user.getPosts().add(post);
					}
				}
			}

			postsReader.close();
			fileReader.close();
			return usersList;

		} catch (Exception e) {
			throw null;
		}
	}

	private static Posts parsePostsObject(JSONObject postRaw) {
		Posts posts = new Posts();
		posts.setBody((String) postRaw.get("body"));
		posts.setTitle((String) postRaw.get("title"));
		posts.setId((Long) postRaw.get("id"));
		posts.setUserId((Long) postRaw.get("userId"));
		return posts;
	}

	private static User parseUserObject(JSONObject userRaw) {
		User user = new User();
		user.setId((Long) userRaw.get("id"));
		user.setEmail((String) userRaw.get("email"));
		user.setName((String) userRaw.get("name"));
		user.setPhone((String) userRaw.get("phone"));
		user.setUsername((String) userRaw.get("username"));
		user.setWebsite((String) userRaw.get("website"));
		Address address = new Address();

		JSONObject addressObject = (JSONObject) userRaw.get("address");
		address.setCity((String) addressObject.get("city"));
		address.setStreet((String) addressObject.get("street"));
		address.setSuite((String) addressObject.get("suite"));
		address.setZipcode((String) addressObject.get("zipcode"));

		JSONObject geoObject = (JSONObject) addressObject.get("geo");
		Geo geo = new Geo();
		geo.setLat((String) geoObject.get("lat"));
		geo.setLng((String) geoObject.get("lng"));

		address.setGeo(geo);
		user.setAddress(address);

		return user;

	}
}
