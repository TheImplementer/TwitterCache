package com.github.theimplementer.twittercache.preferences;

public interface TwitterPreferences {

    void setUserLoggedIn(String token, String tokenSecret);
    void setUserLoggedOut();
    boolean isUserLoggedIn();
    void setAccessToken(String accessToken);
    String getAccessToken();
    void setAccessTokenSecret(String accessTokenSecret);
    String getAccessTokenSecret();

}
