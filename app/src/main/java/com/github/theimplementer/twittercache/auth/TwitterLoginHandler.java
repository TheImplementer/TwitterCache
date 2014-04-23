package com.github.theimplementer.twittercache.auth;

import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterLoginHandler {

    private static final String TWITTER_CONSUMER_KEY = "bJ6MYWxDscwN0AC4vs6IwpoF0";
    private static final String TWITTER_CONSUMER_SECRET = "bAtwBczHSganT4ux6oCpxcMYIQpQ1gzvabVrdsWrDe0lA2mjU6";
    private static final String TWITTER_CALLBACK_URL = "oauth://tcache";

    private TwitterPreferences twitterPreferences;

    public TwitterLoginHandler(TwitterPreferences twitterPreferences) {
        this.twitterPreferences = twitterPreferences;
    }

    public void login() throws TwitterException {
        if (twitterPreferences.isUserLoggedIn()) {
            return;
        }

        final Configuration configuration = new ConfigurationBuilder().
                setOAuthConsumerKey(TWITTER_CONSUMER_KEY).
                setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).
                build();

        final Twitter twitter = new TwitterFactory(configuration).getInstance();
        final RequestToken requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
        twitterPreferences.setUserLoggedIn(true);
        twitterPreferences.setAccessToken(requestToken.getToken());
        twitterPreferences.setAccessTokenSecret(requestToken.getTokenSecret());
    }

}
