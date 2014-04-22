package com.github.theimplementer.twittercache;

public interface TwitterPreferences {

    void setUserLoggedIn(boolean loggedIn);
    boolean isUserLoggedIn();
    void setAccessToken(String accessToken);
    String getAccessToken();
    void setAccessTokenSecret(String accessTokenSecret);
    String getAccessTokenString();
}
