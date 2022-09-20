package com.facebook.lld;

import com.facebook.lld.service.FacebookRunner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		FacebookRunner facebook = new FacebookRunner(3L);
		
		/*2,3,4,5,6 follow 1*/
		facebook.follow(2,1);
		facebook.follow(3,1);
		facebook.follow(4,1);
		facebook.follow(5,1);
		facebook.follow(6,1);
				
		/*1 follows back 2,3,4*/
		facebook.follow(1,2);
		facebook.follow(1,3);
		facebook.follow(1,4);
		
		/*
		facebook.createPost(1, 1000);
		facebook.createPost(1, 1002);
		facebook.createPost(1, 1003);
		facebook.createPost(1, 1004);
		facebook.createPost(1, 1005);
		facebook.createPost(1, 1006);
		facebook.createPost(1, 1007);
		facebook.createPost(1, 1008);
		
		facebook.getNewsFeed(1);
		facebook.getNewsFeedPaginated(1, 2);
		
		*/
		
		System.out.println("****************************");
		
		facebook.unfollow(2,1);
		facebook.unfollow(3,1);
		facebook.unfollow(4,1);
		facebook.unfollow(5,1);
		facebook.unfollow(6,1);

		facebook.getFollowedList(1L);
		facebook.getFollowerList(1L);

	}
}
