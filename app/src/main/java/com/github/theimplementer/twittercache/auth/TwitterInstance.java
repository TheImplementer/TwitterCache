package com.github.theimplementer.twittercache.auth;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterInstance {

    public static final String TWITTER_CALLBACK_URL = "oauth://tcache.auth";
    public static final String TWITTER_CONSUMER_KEY = "bJ6MYWxDscwN0AC4vs6IwpoF0";
    public static final String TWITTER_CONSUMER_SECRET = "bAtwBczHSganT4ux6oCpxcMYIQpQ1gzvabVrdsWrDe0lA2mjU6";
    public static final String TWITTER_OAUTH_VERIFIER = "oauth_verifier";

    private static TwitterInstance instance;

    private final Twitter twitter;

    private RequestToken requestToken = null;

    private TwitterInstance() {
        final Configuration configuration = new ConfigurationBuilder().
                setOAuthConsumerKey(TWITTER_CONSUMER_KEY).
                setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).
                build();
        twitter = new TwitterFactory(configuration).getInstance();
    }

    public static TwitterInstance getInstance() {
        if (instance == null) {
            instance = new TwitterInstance();
        }
        return instance;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public RequestToken getRequestToken() {
        if (requestToken == null) {
            try {
                requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
            } catch (TwitterException ex) {
                throw new RuntimeException(ex);
            }
        }
        return requestToken;
    }
}
