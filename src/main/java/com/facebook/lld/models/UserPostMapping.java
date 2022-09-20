package com.facebook.lld.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPostMapping {
	private static UserPostMapping userPostMappingInstance;
	private Map<Long, List<Post>> store;

	private UserPostMapping() {
		store = new HashMap<Long, List<Post>>();
	}

	public static UserPostMapping getUserIdToPostMap() {
		if (userPostMappingInstance == null) {
			synchronized (UserPostMapping.class) {
				if (userPostMappingInstance == null) {
					userPostMappingInstance = new UserPostMapping();
				}
			}
		}
		return userPostMappingInstance;
	}
	
	public List<Post> getUserPosts(Long userId){
		return store.get(userId) ;
	}
	
	public void setUserPostList(Long userId, List<Post> posts) {
		store.put(userId, posts) ;
	}

}
