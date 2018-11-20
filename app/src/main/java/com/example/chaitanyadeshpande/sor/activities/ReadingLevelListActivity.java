package com.example.chaitanyadeshpande.sor.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.BaseDrawerActivity;
import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.adapters.ReadingLevelAdapter;
import com.example.chaitanyadeshpande.sor.network.APIClientCallback;
import com.example.chaitanyadeshpande.sor.network.APIClientUtils;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
import com.example.chaitanyadeshpande.sor.request_response.ReadingLevelListRequest;
import com.example.chaitanyadeshpande.sor.request_response.ReadingLevelListResponse;
import com.example.chaitanyadeshpande.sor.utilities.MainUtility;
import com.example.chaitanyadeshpande.sor.utilities.UserInfoUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadingLevelListActivity extends BaseDrawerActivity {

    @BindView(R.id.tv_no_reading_level)
    TextView tvNoReadingLevel;
    @BindView(R.id.rv_reading_level_list)
    RecyclerView rvReadingLevelList;
    ReadingLevelAdapter readingLevelAdapter;
    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ReadingLevelListActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_level_list);
        ButterKnife.bind(this);
        toolbarHeaderTv.setText(R.string.app_name);

    }

    @Override
    protected void onResume() {
        super.onResume();

        showLoadingView("Please wait");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvReadingLevelList.setLayoutManager(mLayoutManager);
        rvReadingLevelList.setItemAnimator(new DefaultItemAnimator());

        ReadingLevelListRequest readingLevelListRequest = new ReadingLevelListRequest();
        readingLevelListRequest.setM("ReadingLevels");
        readingLevelListRequest.setUserId(UserInfoUtility.getInstance().getSelectedUserDetails().getId());

        getReadingLevelList(readingLevelListRequest, new APIClientUtils(this));


    }


    public void getReadingLevelList(ReadingLevelListRequest readingLevelListRequest, APIClientUtils apiClientUtils) {

        try {
            apiClientUtils.getServiceResponseByPost(UrlConstant.getList(),
                    new APIClientCallback<ReadingLevelListResponse>() {

                        @Override
                        public void onSuccess(ReadingLevelListResponse readingLevelListResponse) {

                            hideLoadingView();

                            if (readingLevelListResponse.getStatus()) {

                                if (!(readingLevelListResponse.getData() == null || readingLevelListResponse.getData().size() == 0)) {

                                    tvNoReadingLevel.setVisibility(View.GONE);
                                    rvReadingLevelList.setVisibility(View.VISIBLE);
                                    readingLevelAdapter = new ReadingLevelAdapter(readingLevelListResponse.getData(), ReadingLevelListActivity.this, ReadingLevelListActivity.this);
                                    rvReadingLevelList.setAdapter(readingLevelAdapter);
                                } else {
                                    tvNoReadingLevel.setVisibility(View.VISIBLE);
                                    rvReadingLevelList.setVisibility(View.GONE);
                                }
                            } else {
                                MainUtility.showToast(ReadingLevelListActivity.this, "No Reading Level Available");
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            hideLoadingView();
                            MainUtility.showMessage(rvReadingLevelList, "Something went wrong");
                        }

                        @NonNull
                        @Override
                        public Class<ReadingLevelListResponse> getClassOfType() {
                            return ReadingLevelListResponse.class;
                        }

                    }, new JSONObject(apiClientUtils.getObjectMapper().writeValueAsString(readingLevelListRequest)));
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
        showLogoutDialog();
    }

    protected void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Logout");

        builder.setMessage("Do you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.launch(ReadingLevelListActivity.this);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }



}
