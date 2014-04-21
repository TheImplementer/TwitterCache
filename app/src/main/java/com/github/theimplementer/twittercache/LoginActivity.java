package com.github.theimplementer.twittercache;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FragmentManager fragmentManager = getFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment == null) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            final Fragment loginFragment = LoginFragment.newInstance();
            fragmentTransaction.add(R.id.container, loginFragment);
            fragmentTransaction.commit();
        }

    }
}
