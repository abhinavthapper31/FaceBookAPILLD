package com.facebook.lld.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.facebook.lld.models.Post;
import com.facebook.lld.models.User;
import com.facebook.lld.models.UserPostMapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookRunner {

	private static Long postCounter;
	private final Long PAGE_SIZE;
	private UserPostMapping userIdPostMap;
	private Map<Long, User> userMap;

	public FacebookRunner() {
		PAGE_SIZE = 3L;
		userIdPostMap = UserPostMapping.getUserIdToPostMap();
		postCounter = 1L;
		userMap = new HashMap<Long, User>();
	}

	public void createPost(Integer userId, Integer postId) {
		User user = userMap.get(userId);
		if (user == null) {
			user = new User(userId);
			userMap.put(userId.longValue(), user);
		}
		Post newPost = user.createPost(userId, postId, postCounter);
		postCounter++;

		List<Post> userPosts = userIdPostMap.getUserPosts(userId.longValue());
		if(userPosts == null) {
			userPosts = new ArrayList<>() ;
		}
		userPosts.add(newPost);
		userIdPostMap.setUserPostList(userId.longValue(), userPosts);
		System.out.println("User " + userId + " posted " + postId);

	}

	public void deletePost(Integer userId, Integer postId) {
		User user = userMap.get(userId.longValue());
		if (user == null) {
			System.out.println("user does not exist");
			return;
		}
		List<Post> userPosts = userIdPostMap.getUserPosts(userId.longValue());
		userPosts.removeIf(e -> e.getId().equals(postId.longValue()));
		userIdPostMap.setUserPostList(userId.longValue(), userPosts);
	}

	public void follow(Integer userId, Integer followeeId) {

	}

	public void unfollow(Integer userId, Integer followeeId) {
	}

	public void getNewsFeed(Integer userId) {
		User user = userMap.get(userId.longValue());
		if (user == null) {
			return;
		}
		List<Post> userPosts = userIdPostMap.getUserPosts(userId.longValue());
		Collections.sort(userPosts, (a, b) -> a.getTimestamp().intValue() - b.getTimestamp().intValue());
		System.out.println("Publishing Feed..");
		for (Post post : userPosts) {
			System.out.println("Post number : " + post.getId());
		}
	}

	public void getNewsFeedPaginated(Integer userId, Integer pageNumber) {
		List<Post> userPosts = userIdPostMap.getUserPosts(userId.longValue());
		Collections.sort(userPosts, (a, b) -> a.getTimestamp().intValue() - b.getTimestamp().intValue());
		
		Integer start = pageNumber * PAGE_SIZE.intValue();
        Integer end = Math.min(start + PAGE_SIZE.intValue(), userPosts.size());
        
        if (start > end)
            return;
        List<Post> paginatedFeed = userPosts.subList(start, end);
        System.out.println("Publishing paginated news feed : ");
        System.out.println("Page number " + pageNumber + " of user " + userId + " feed");
        for (int i = 0; i < paginatedFeed.size(); i++)
            System.out.println("Post " + (i + 1) + " " + paginatedFeed.get(i));
	}

}
