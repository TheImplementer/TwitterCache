package com.github.theimplementer.twittercache.view;

import java.util.List;

public interface Updatable<T> {
    void add(List<T> item);
}
