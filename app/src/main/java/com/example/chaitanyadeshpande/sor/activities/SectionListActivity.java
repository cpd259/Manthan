package com.example.chaitanyadeshpande.sor.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.BaseDrawerActivity;
import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.adapters.SectionAdapter;
import com.example.chaitanyadeshpande.sor.network.APIClientCallback;
import com.example.chaitanyadeshpande.sor.network.APIClientUtils;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
import com.example.chaitanyadeshpande.sor.request_response.SectionListRequest;
import com.example.chaitanyadeshpande.sor.request_response.SectionListResponse;
import com.example.chaitanyadeshpande.sor.utilities.MainUtility;
import com.example.chaitanyadeshpande.sor.utilities.SelectedReadingLevelUtility;
import com.example.chaitanyadeshpande.sor.utilities.UserInfoUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionListActivity extends BaseDrawerActivity {

    @BindView(R.id.tv_no_section)
    TextView tvNoSection;
    @BindView(R.id.rv_section_list)
    RecyclerView rvSectionList;
    SectionAdapter sectionAdapter;
    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, SectionListActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_list);
        ButterKnife.bind(this);
        toolbarHeaderTv.setText(SelectedReadingLevelUtility.getInstance().getSelectedReadingLevel().getTitle());
    }


    @Override
    protected void onResume() {
        super.onResume();

        showLoadingView("Please wait");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvSectionList.setLayoutManager(mLayoutManager);
        rvSectionList.setItemAnimator(new DefaultItemAnimator());

        SectionListRequest sectionListRequest = new SectionListRequest();
        sectionListRequest.setM("ReadingLevelSections");
        sectionListRequest.setUserId(UserInfoUtility.getInstance().getSelectedUserDetails().getId());
        sectionListRequest.setLevelId(SelectedReadingLevelUtility.getInstance().getSelectedReadingLevel().getId());
        getSectionList(sectionListRequest, new APIClientUtils(this));


    }


    public void getSectionList(SectionListRequest sectionListRequest, APIClientUtils apiClientUtils) {

        try {
            apiClientUtils.getServiceResponseByPost(UrlConstant.getList(),
                    new APIClientCallback<SectionListResponse>() {

                        @Override
                        public void onSuccess(SectionListResponse sectionListResponse) {

                            hideLoadingView();

                            if (sectionListResponse.getStatus()) {

                                if (sectionListResponse.getData().size() != 0) {

                                    tvNoSection.setVisibility(View.GONE);
                                    rvSectionList.setVisibility(View.VISIBLE);
                                    sectionAdapter = new SectionAdapter(sectionListResponse.getData(), SectionListActivity.this);
                                    rvSectionList.setAdapter(sectionAdapter);
                                } else {
                                    tvNoSection.setVisibility(View.VISIBLE);
                                    rvSectionList.setVisibility(View.GONE);
                                }
                            } else {
                                MainUtility.showToast(SectionListActivity.this, "No Sections Available");
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            hideLoadingView();
                            MainUtility.showMessage(rvSectionList, "Something went wrong");
                        }

                        @NonNull
                        @Override
                        public Class<SectionListResponse> getClassOfType() {
                            return SectionListResponse.class;
                        }

                    }, new JSONObject(apiClientUtils.getObjectMapper().writeValueAsString(sectionListRequest)));
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


}
