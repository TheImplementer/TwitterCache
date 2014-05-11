package com.github.theimplementer.twittercache.view;

import java.util.LinkedList;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

import static java.lang.String.format;
import static twitter4j.TwitterObjectFactory.createStatus;

public class FakeTweetsFetcher implements TweetsFetcher {

    private static final List<Status> FAKE_TWEETS = fakeTweets();

    private final Updatable<Status> updatable;

    public FakeTweetsFetcher(Updatable<Status> updatable) {
        this.updatable = updatable;
    }

    @Override
    public void fetch(int tweetsCacheSize) {
        updatable.add(FAKE_TWEETS);
    }

    private static final List<Status> fakeTweets() {
        final List<Status> fakeTweets = new LinkedList<Status>();
        for (int count = 0; count < 5; count++) {
            try {
                final Status tweet = createStatus(format("{ text: \"Tweet %s\" }", count));
                fakeTweets.add(tweet);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        return fakeTweets;
    }
}
