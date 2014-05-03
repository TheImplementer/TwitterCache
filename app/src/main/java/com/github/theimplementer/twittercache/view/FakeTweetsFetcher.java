package com.github.theimplementer.twittercache.view;

import java.util.List;

import static java.util.Arrays.asList;

public class FakeTweetsFetcher implements TweetsFetcher {

    private static final List<String> FAKE_TWEETS = asList("Tweet 1", "Tweet 2", "Tweet 3", "Tweet 4", "Tweet 5");

    private final Updatable<String> updatable;

    public FakeTweetsFetcher(Updatable<String> updatable) {
        this.updatable = updatable;
    }

    @Override
    public void fetch() {
        updatable.add(FAKE_TWEETS);
    }
}
