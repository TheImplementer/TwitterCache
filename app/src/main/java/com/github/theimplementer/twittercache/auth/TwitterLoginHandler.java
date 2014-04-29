package com.github.theimplementer.twittercache.auth;

import twitter4j.TwitterException;

public interface TwitterLoginHandler {
    void login() throws TwitterException;
}
