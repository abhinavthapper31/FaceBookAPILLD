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
	private Long PAGE_SIZE;
	private UserPostMapping userIdPostMap;
	private Map<Long, User> userMap;

	public FacebookRunner(Long feedPageSize) {
		userIdPostMap = UserPostMapping.getUserIdToPostMap();
		postCounter = 1L;
		userMap = new HashMap<Long, User>();
		PAGE_SIZE = feedPageSize;
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
		if (userPosts == null) {
			userPosts = new ArrayList<>();
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
		User followee = userMap.get(followeeId.longValue());
		User follower = userMap.get(userId.longValue());

		if (followee == null) {
			followee =createUser(followeeId.longValue());
		}
		if (follower == null) {
			follower = createUser(userId.longValue());
		}

		followee.getFollowers().add(userId);
		follower.getFollowed().add(followeeId);

		System.out.println("User Id " + userId + " followed Id " + followeeId);

	}

	public void unfollow(Integer userId, Integer followeeId) {
		User followee = userMap.get(followeeId.longValue());
		User follower = userMap.get(userId.longValue());

		if (followee == null) {
			System.out.print("Followee does not exist");
			return;
		}
		if (follower == null) {
			System.out.print("Follower does not exist");
			return;
		}
		followee.getFollowers().remove(new Integer(userId));
		follower.getFollowed().remove(new Integer(followeeId));
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
			System.out.println("Post " + (i + 1) + " " + paginatedFeed.get(i).getId());
	}

	public User createUser(Long userId) {
		User user = new User(userId.intValue());
		userMap.put(userId, user);
		return user ;
	}

	public void getFollowerList(Long userId) {
		User user = userMap.get(userId.longValue());
		if (user == null) {
			System.out.println("User does not exist");
			return;
		}

		if (user.getFollowers().isEmpty()) {
			System.out.println("User does not have any followers yet !");
			return;
		}
		
		System.out.print("User " + userId + " has followers : ");
		for(int followerId : user.getFollowers()) {
			System.out.print(followerId + " ");
		}
		System.out.println();
	}

	public void getFollowedList(Long userId) {

		User user = userMap.get(userId.longValue());
		if (user == null) {
			System.out.println("User does not exist");
			return;
		}

		if (user.getFollowed().isEmpty()) {
			System.out.println("User " + userId + " hasnt followed anyone yet !");
			return;
		}

		System.out.println("User " + userId + " has followed : ");
		for (int followedId : user.getFollowed()) {
			System.out.print(followedId + " ");
		}
		System.out.println();
	}

}
