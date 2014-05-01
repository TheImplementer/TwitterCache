package com.github.theimplementer.twittercache.auth;

public interface LoginObserver {
    void notifySuccess();
    void notifyFailure();
}
