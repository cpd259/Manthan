package com.example.chaitanyadeshpande.sor.network;

import org.json.JSONArray;


public interface APIClientCallbackForJSONArray {

    void onSuccess(JSONArray success);

    void onFailure(Exception e);

    String getAPINameForGA();

    String getEventLabelForGA();

    String getScreenNameForGA();

}
