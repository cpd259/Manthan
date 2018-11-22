package com.example.chaitanyadeshpande.sor.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.network.APIClientCallback;
import com.example.chaitanyadeshpande.sor.network.APIClientUtils;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_login_details_container)
    LinearLayout llLoginDetailsContainer;
    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;

    boolean doubleBackToExitPressedOnce = false;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        SharedPreferences prefs = getSharedPreferences("UserNamePref", MODE_PRIVATE);
        String userName = prefs.getString("UserName", "");
        etUsername.setText(userName);
        etPassword.setFocusable(true);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        if (!etUsername.getText().toString().equalsIgnoreCase("")) {

            if (!etPassword.getText().toString().equalsIgnoreCase("")) {


                showLoadingView("Logging In");
                LoginRequest loginRequest=new LoginRequest();
                loginRequest.setUsername(etUsername.getText().toString());
                loginRequest.setPassword(etPassword.getText().toString());

                getLoginApi(loginRequest,new APIClientUtils(this));


            } else {
                etPassword.setError("Please Enter Password");
            }


        } else {
            etUsername.setError("Please Enter Username");
        }


    }


    public void getLoginApi(final LoginRequest loginRequest, APIClientUtils apiClientUtils) {

        try {
            apiClientUtils.getServiceResponseByPost(UrlConstant.getLogin(),
                    new APIClientCallback<LoginResponse>() {

                        @Override
                        public void onSuccess(LoginResponse loginResponse) {

                            hideLoadingView();

                            if (loginResponse.getStatus()) {

                                if(loginResponse.getData()!=null) {

                                    UserInfoUtility.getInstance().setSelectedUserDetails(loginResponse.getData());

                                    SharedPreferences.Editor editor = getSharedPreferences("UserNamePref", MODE_PRIVATE).edit();
                                    editor.putString("UserName",loginResponse.getData().getUsername());
                                    editor.apply();

                                    ReadingLevelListActivity.launch(LoginActivity.this);


                                }else {
                                    MainUtility.showToast(LoginActivity.this,"No Userdata available");
                                }

                            } else {
                                MainUtility.showToast(LoginActivity.this, loginResponse.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            hideLoadingView();
                            MainUtility.showMessage(btnLogin, "Something went wrong");
                        }

                        @NonNull
                        @Override
                        public Class<LoginResponse> getClassOfType() {
                            return LoginResponse.class;
                        }

                    }, new JSONObject(apiClientUtils.getObjectMapper().writeValueAsString(loginRequest)));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    public void showLoadingView(String message) {
        llProgressMain.setVisibility(View.VISIBLE);
        tvLoadingMessage.setText(message);
//        Logger.logError(LOG_TAG,"loading msg """);
    }

    public void hideLoadingView() {
        llProgressMain.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            ExitActivity.exitApplication(this);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }
}
