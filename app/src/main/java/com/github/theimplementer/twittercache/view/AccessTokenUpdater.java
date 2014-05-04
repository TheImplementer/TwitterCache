package com.github.theimplementer.twittercache.view;

import twitter4j.auth.AccessToken;

public interface AccessTokenUpdater {
    void updateAccessToken(AccessToken accessToken);
}
