package com.facebook.lld.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private Long userId;
	private UserPostMapping postMapping;
	private List<Integer> followed;
	private List<Integer> followers;

	public User(Integer userId) {
		this.userId = userId.longValue();
		followers = new ArrayList<>();
		followed = new ArrayList<>();
	}

	public Post createPost(Integer userId, Integer postId, Long postCounter) {
		Post newPost = new Post(postId.longValue());
		newPost.setTimestamp(System.currentTimeMillis());
		newPost.setContent("This is post number ->" + postCounter);
		postCounter++;
		return newPost;
	}

}
