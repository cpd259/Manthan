package com.example.chaitanyadeshpande.sor.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.chaitanyadeshpande.sor.BaseDrawerActivity;
import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.network.APIClientCallback;
import com.example.chaitanyadeshpande.sor.network.APIClientUtils;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
import com.example.chaitanyadeshpande.sor.request_response.ChangePasswordRequest;
import com.example.chaitanyadeshpande.sor.request_response.ChangePasswordResponse;
import com.example.chaitanyadeshpande.sor.request_response.LoginRequest;
import com.example.chaitanyadeshpande.sor.request_response.LoginResponse;
import com.example.chaitanyadeshpande.sor.utilities.MainUtility;
import com.example.chaitanyadeshpande.sor.utilities.UserInfoUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseDrawerActivity {


    @BindView(R.id.iv_inspector_logo)
    ImageView ivInspectorLogo;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.et_old_password)
    AutoCompleteTextView etOldPassword;
    @BindView(R.id.et_new_password_1)
    AutoCompleteTextView etNewPassword1;
    @BindView(R.id.et_new_password_2)
    AutoCompleteTextView etNewPassword2;
    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        disableNavigationDrawer();


        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(getResources().getColor(R.color.colorPrimary))
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(UserInfoUtility.getInstance().getSelectedUserDetails().getFirstName().substring(0,1), Color.WHITE);

        tvUser.setText(UserInfoUtility.getInstance().getSelectedUserDetails().getFirstName()+" "+UserInfoUtility.getInstance().getSelectedUserDetails().getLastName());

        toolbarHeaderTv.setText("");

        ivInspectorLogo.setImageDrawable(drawable);

    }


    public void showLoadingView(String message) {
        llProgressMain.setVisibility(View.VISIBLE);
        tvLoadingMessage.setText(message);
//        Logger.logError(LOG_TAG,"loading msg """);
    }

    public void hideLoadingView() {
        llProgressMain.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {

        if (!etOldPassword.getText().toString().equalsIgnoreCase("")) {

            if (!etNewPassword1.getText().toString().equalsIgnoreCase("")) {

                if (!etNewPassword2.getText().toString().equalsIgnoreCase("")) {

                    if (etNewPassword1.getText().toString().equals(etNewPassword2.getText().toString())) {

                        showLoadingView("Changing Password");
                        ChangePasswordRequest changePasswordRequest=new ChangePasswordRequest();
                        changePasswordRequest.setM("change-password");
                        changePasswordRequest.setUserId(UserInfoUtility.getInstance().getSelectedUserDetails().getId());
                        changePasswordRequest.setCurrentPassword(etOldPassword.getText().toString());
                        changePasswordRequest.setNewPassword(etNewPassword2.getText().toString());

                        changePassword(changePasswordRequest,new APIClientUtils(this));

                    } else {
                        etNewPassword2.setError("New password does not match");
                    }

                } else {
                    etNewPassword2.setError("Please Confirm New Password");
                }

            } else {
                etNewPassword1.setError("Please Enter New Password");
            }

        } else {
            etOldPassword.setError("Please Enter Old Password");
        }


    }


    public void changePassword(ChangePasswordRequest changePasswordRequest, APIClientUtils apiClientUtils) {

        try {
            apiClientUtils.getServiceResponseByPost(UrlConstant.getLogin(),
                    new APIClientCallback<ChangePasswordResponse>() {

                        @Override
                        public void onSuccess(ChangePasswordResponse changePasswordResponse) {

                            hideLoadingView();

                            if (changePasswordResponse.getStatus()) {

                                MainUtility.showToast(ProfileActivity.this,changePasswordResponse.getMsg());
                                ReadingLevelListActivity.launch(ProfileActivity.this);

                            } else {
                                MainUtility.showToast(ProfileActivity.this, changePasswordResponse.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            hideLoadingView();
                            MainUtility.showMessage(btnUpdate, "Something went wrong");
                        }

                        @NonNull
                        @Override
                        public Class<ChangePasswordResponse> getClassOfType() {
                            return ChangePasswordResponse.class;
                        }

                    }, new JSONObject(apiClientUtils.getObjectMapper().writeValueAsString(changePasswordRequest)));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        ReadingLevelListActivity.launch(this);
    }
}
