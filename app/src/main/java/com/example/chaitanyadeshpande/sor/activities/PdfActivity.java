package com.example.chaitanyadeshpande.sor.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.BaseDrawerActivity;
import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.network.UrlConstant;
import com.example.chaitanyadeshpande.sor.utilities.Logger;
import com.example.chaitanyadeshpande.sor.utilities.MainUtility;
import com.example.chaitanyadeshpande.sor.utilities.SelectedAttachmentUtility;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PdfActivity extends BaseDrawerActivity implements DownloadFile.Listener {

    @BindView(R.id.ll_pdf_container)
    LinearLayout llPdfContainer;
    @BindView(R.id.tv_loading_message)
    TextView tvLoadingMessage;
    @BindView(R.id.ll_progress_main)
    LinearLayout llProgressMain;
    private String LOG_TAG = "PdfActivity";

    PDFPagerAdapter pdfPagerAdapter;
    RemotePDFViewPager remotePDFViewPager;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, PdfActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);

        showLoadingView("Loading Attachment");


        String url = UrlConstant.getBaseUrl() + "uploads/" + SelectedAttachmentUtility.getInstance().getSelectedAttachment().getAttachment();
        String urlCommon = UrlConstant.getBaseUrl() + "uploads/global/" + SelectedAttachmentUtility.getInstance().getSelectedAttachment().getAttachment();

        toolbarHeaderTv.setText(SelectedAttachmentUtility.getInstance().getSelectedAttachment().getTitle());
//        toolbarHeaderTv.setText(R.string.app_name);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        if(SelectedAttachmentUtility.getInstance().getSelectedAttachment().getCommon()){
            remotePDFViewPager =
                    new RemotePDFViewPager(this, urlCommon, this);

            Logger.logError("PdfActivity","Url :- "+urlCommon);

        }else {
            remotePDFViewPager =
                    new RemotePDFViewPager(this, url, this);
            Logger.logError("PdfActivity","Url :- "+url);

        }


    }

    public void updateLayout() {
        llPdfContainer.removeAllViewsInLayout();
        llPdfContainer.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {

        hideLoadingView();
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();

    }

    @Override
    public void onFailure(Exception e) {
        hideLoadingView();
        MainUtility.showToast(this, "Attachment Loading failed");

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
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
