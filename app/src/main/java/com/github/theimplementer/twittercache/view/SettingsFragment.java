package com.github.theimplementer.twittercache.view;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.github.theimplementer.twittercache.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public static Fragment newInstance() {
        return new SettingsFragment();
    }
}
