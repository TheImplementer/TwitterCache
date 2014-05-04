package com.github.theimplementer.twittercache.auth;

import android.content.Context;
import android.content.Intent;

import com.github.theimplementer.twittercache.view.MainActivity;

public class FakeLoginHandler implements LoginHandler {

    private final Context context;

    public FakeLoginHandler(Context context) {
        this.context = context;
    }

    @Override
    public void login() {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
