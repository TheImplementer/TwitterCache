package com.github.theimplementer.twittercache.view;

import java.io.Serializable;

import twitter4j.Status;

public interface TweetItemClickListener extends Serializable {
    void displayDetailsFor(Status status);
}
