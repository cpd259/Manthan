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
import com.example.chaitanyadeshpande.sor.adapters.AttachmentAdapter;
import com.example.chaitanyadeshpande.sor.network.APIClientCallback;
import com.example.chaitanyadeshpande.sor.network.APIClientUtils;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
import com.example.chaitanyadeshpande.sor.request_response.AttachmentListRequest;
import com.example.chaitanyadeshpande.sor.request_response.AttachmentListResponse;
import com.example.chaitanyadeshpande.sor.utilities.MainUtility;
import com.example.chaitanyadeshpande.sor.utilities.SelectedSectionUtility;
import com.example.chaitanyadeshpande.sor.utilities.UserInfoUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttachmentListActivity extends BaseDrawerActivity {

    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AttachmentListActivity.class));
    }


    @BindView(R.id.tv_no_attachment)
    TextView tvNoAttachment;
    @BindView(R.id.rv_attachment_list)
    RecyclerView rvAttachmentList;

    AttachmentAdapter attachmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachment_list);
        ButterKnife.bind(this);
        toolbarHeaderTv.setText(SelectedSectionUtility.getInstance().getSelectedSection().getSectionTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();

        showLoadingView("Please wait");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvAttachmentList.setLayoutManager(mLayoutManager);
        rvAttachmentList.setItemAnimator(new DefaultItemAnimator());

        AttachmentListRequest attachmentListRequest = new AttachmentListRequest();
        attachmentListRequest.setM("ReadingData");
        attachmentListRequest.setUserId(UserInfoUtility.getInstance().getSelectedUserDetails().getId());
        attachmentListRequest.setSectionId(SelectedSectionUtility.getInstance().getSelectedSection().getId());
        getReadingLevelList(attachmentListRequest, new APIClientUtils(this));


    }


    public void getReadingLevelList(AttachmentListRequest attachmentListRequest, APIClientUtils apiClientUtils) {

        try {
            apiClientUtils.getServiceResponseByPost(UrlConstant.getList(),
                    new APIClientCallback<AttachmentListResponse>() {

                        @Override
                        public void onSuccess(AttachmentListResponse attachmentListResponse) {

                            hideLoadingView();

                            if (attachmentListResponse.getStatus()) {

                                if (attachmentListResponse.getData().size() != 0) {

                                    tvNoAttachment.setVisibility(View.GONE);
                                    rvAttachmentList.setVisibility(View.VISIBLE);
                                    attachmentAdapter = new AttachmentAdapter(attachmentListResponse.getData(), AttachmentListActivity.this);
                                    rvAttachmentList.setAdapter(attachmentAdapter);
                                } else {
                                    tvNoAttachment.setVisibility(View.VISIBLE);
                                    rvAttachmentList.setVisibility(View.GONE);
                                }
                            } else {
                                MainUtility.showToast(AttachmentListActivity.this, "No Attachments Available");
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            hideLoadingView();
                            MainUtility.showMessage(rvAttachmentList, "Something went wrong");
                        }

                        @NonNull
                        @Override
                        public Class<AttachmentListResponse> getClassOfType() {
                            return AttachmentListResponse.class;
                        }

                    }, new JSONObject(apiClientUtils.getObjectMapper().writeValueAsString(attachmentListRequest)));
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
