package com.facebook.lld.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private Long userId;
	private String userName;
	private FolloweeFollowerMapping followerMapping;
	private UserPostMapping postMapping;

	public User(Integer userId) {
		this.userId = userId.longValue() ;
	}

	public Post createPost(Integer userId, Integer postId, Long postCounter) {
		Post newPost = new Post(postId.longValue());
		newPost.setTimestamp(System.currentTimeMillis());
		newPost.setContent("This is post number ->" + postCounter);
		postCounter++;
		return newPost;
	}

}
