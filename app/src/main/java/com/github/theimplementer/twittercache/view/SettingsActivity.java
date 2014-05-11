package com.github.theimplementer.twittercache.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.github.theimplementer.twittercache.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final FragmentManager fragmentManager = getFragmentManager();
        final Fragment settingsFragment = fragmentManager.findFragmentById(R.id.settings_container);
        if (settingsFragment == null) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.settings_container, SettingsFragment.newInstance());
            transaction.commit();
        }
    }
}
