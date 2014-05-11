package com.github.theimplementer.twittercache.view;

import twitter4j.Status;

public class RemoteTweetsFetcher implements TweetsFetcher {

    private final Updatable<Status> updatable;

    public RemoteTweetsFetcher(Updatable<Status> updatable) {
        this.updatable = updatable;
    }

    @Override
    public void fetch(int tweetsCacheSize) {
        new TweetFetchTask(updatable).execute(tweetsCacheSize);
    }
}
