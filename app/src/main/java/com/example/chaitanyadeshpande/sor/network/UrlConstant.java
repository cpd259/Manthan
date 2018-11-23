package com.example.chaitanyadeshpande.sor.network;


public class UrlConstant {

    private static String baseUrl = "http://manthaneducation.in/";

    private static final String LOG_TAG = "UrlConstant";

    private static final String login = "api/login";
    private static final String list = "api/list";



    public static String getLogin() {
        return baseUrl + login;
    }

    public static String getList() {
        return baseUrl + list;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
