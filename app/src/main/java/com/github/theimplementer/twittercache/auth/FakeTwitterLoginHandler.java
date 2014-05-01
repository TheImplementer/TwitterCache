package com.github.theimplementer.twittercache.auth;

import android.os.AsyncTask;

public class FakeTwitterLoginHandler extends AsyncTask<Void, Void, LoginResult> {

    @Override
    protected LoginResult doInBackground(Void... voids) {
        return LoginResult.SUCCESS;
    }
}
