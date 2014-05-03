package com.github.theimplementer.twittercache.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.github.theimplementer.twittercache.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getFragmentManager();
        final Fragment container = fragmentManager.findFragmentById(R.id.tweet_list_container);
        if (container == null) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.tweet_list_container, TweetListFragment.newInstance());
            fragmentTransaction.commit();
        }
    }
}
