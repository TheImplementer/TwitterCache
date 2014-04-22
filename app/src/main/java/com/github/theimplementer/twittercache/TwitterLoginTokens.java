package com.github.theimplementer.twittercache;

public class TwitterLoginTokens {
    private String token;
    private String tokenSecret;

    public TwitterLoginTokens(String token, String tokenSecret) {
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public String getToken() {
        return token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }
}
