package com.facebook.lld;

import com.facebook.lld.service.FacebookRunner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
        FacebookRunner facebook = new FacebookRunner();

        facebook.createPost(1, 1000);
        facebook.createPost(1, 1002);
        facebook.createPost(1, 1003);
        facebook.createPost(1, 1004);
        facebook.createPost(1, 1005);
        facebook.createPost(1, 1006);
        facebook.createPost(7, 1007);
        facebook.createPost(8, 1008);
        facebook.createPost(9, 1009);
        facebook.createPost(10, 1010);
        facebook.createPost(11, 1011);
        facebook.createPost(12, 1012);
        facebook.createPost(13, 1013);
        facebook.getNewsFeed(1);
        facebook.getNewsFeedPaginated(1, 2);

//        facebook.deletePost(12, 1012);
//        facebook.getNewsFeed(1);
//        facebook.getNewsFeedPaginated(1, 2);
//        facebook.getNewsFeedPaginated(1, 5);
	}
}
