package com.github.theimplementer.twittercache.auth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import static android.content.Intent.ACTION_VIEW;
import static android.net.Uri.parse;
import static com.github.theimplementer.twittercache.auth.TwitterInstance.getInstance;

public class LoginTask extends AsyncTask<Void, Void, RequestToken> {

    private final ConnectivityManager connectivityManager;
    private final Context context;

    public LoginTask(ConnectivityManager connectivityManager,
                     Context context) {
        this.connectivityManager = connectivityManager;
        this.context = context;
    }

    @Override
    protected RequestToken doInBackground(Void... voids) {
        try {
            return getRequestToken();
        } catch (TwitterException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void onPostExecute(RequestToken requestToken) {
        final Uri authenticationUrl = parse(requestToken.getAuthenticationURL());
        final Intent intent = new Intent(ACTION_VIEW, authenticationUrl);
        context.startActivity(intent);
    }

    private RequestToken getRequestToken() throws TwitterException {
        if (!isConnected()) throw new OfflineDeviceException();

        return getInstance().getRequestToken();
    }

    private boolean isConnected() {
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
