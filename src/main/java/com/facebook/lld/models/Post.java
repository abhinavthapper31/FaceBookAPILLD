package com.facebook.lld.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

	private Long id;
	private Long timestamp;
	private String content;

	public Post(Long postId) {
		this.id = postId;
	}

}
