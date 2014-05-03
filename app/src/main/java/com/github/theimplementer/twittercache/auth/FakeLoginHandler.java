package com.github.theimplementer.twittercache.auth;

public class FakeLoginHandler implements LoginHandler {

    private LoginObserver loginObserver;

    public FakeLoginHandler(LoginObserver loginObserver) {
        this.loginObserver = loginObserver;
    }

    @Override
    public void login() {
        loginObserver.notifySuccess();
    }
}
