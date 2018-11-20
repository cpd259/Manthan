package com.example.chaitanyadeshpande.sor.utilities;

public class TokenUtility {
    private static final String LOG_TAG = "TokenUtility";
    private static TokenUtility instance;

    String token;

    private TokenUtility() {
    }

    public static TokenUtility getInstance() {
        if (instance == null) {
            instance = new TokenUtility();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

