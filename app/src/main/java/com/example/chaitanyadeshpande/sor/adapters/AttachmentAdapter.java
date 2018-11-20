package com.example.chaitanyadeshpande.sor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.activities.PdfActivity;
import com.example.chaitanyadeshpande.sor.request_response.Attachment;
import com.example.chaitanyadeshpande.sor.utilities.Logger;
import com.example.chaitanyadeshpande.sor.utilities.SelectedAttachmentUtility;

import java.util.List;

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.MyViewHolder>  {

    private List<Attachment> attachmentList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public String LOG_TAG = AttachmentAdapter.class.getSimpleName();
        public TextView readingLevelName, type;
        public Button btnOpen;


        public MyViewHolder(View view) {
            super(view);
            readingLevelName = view.findViewById(R.id.tv_reading_level_name);
            type = view.findViewById(R.id.tv_type);
            btnOpen =view.findViewById(R.id.btn_open);
        }
    }

    public AttachmentAdapter(List<Attachment> attachmentList, Context context) {
        this.attachmentList = attachmentList;
        this.mContext = context;
        Logger.logError("LIST",""+attachmentList.size());
    }

    @Override
    public AttachmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attachment_row_list, parent, false);

        return new AttachmentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AttachmentAdapter.MyViewHolder holder, int position) {
        final Attachment attachment = this.attachmentList.get(position);
        holder.readingLevelName.setText(attachment.getTitle());
//        holder.type.setText(attachment.getDescription());
        holder.btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectedAttachmentUtility.getInstance().setSelectedAttachment(attachment);
                SelectedAttachmentUtility.getInstance().getSelectedAttachment().setCommon(false);
                PdfActivity.launch(mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        return attachmentList.size();
    }}
