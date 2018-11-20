package com.example.chaitanyadeshpande.sor.utilities;


import com.example.chaitanyadeshpande.sor.request_response.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserInfoUtility {


    private static final String LOG_TAG = "UserInfoUtility";
    private static UserInfoUtility instance;



    UserDetails selectedUserDetails;



    private UserInfoUtility() {
    }

    public static UserInfoUtility getInstance() {
        if (instance == null) {
            instance = new UserInfoUtility();
        }
        return instance;
    }

    public UserDetails getSelectedUserDetails() {
        return selectedUserDetails;
    }

    public void setSelectedUserDetails(UserDetails selectedUserDetails) {
        this.selectedUserDetails = selectedUserDetails;
    }
}



