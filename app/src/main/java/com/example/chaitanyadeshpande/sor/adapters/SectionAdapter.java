package com.example.chaitanyadeshpande.sor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.activities.AttachmentListActivity;
import com.example.chaitanyadeshpande.sor.activities.SectionListActivity;
import com.example.chaitanyadeshpande.sor.request_response.Section;
import com.example.chaitanyadeshpande.sor.utilities.Logger;
import com.example.chaitanyadeshpande.sor.utilities.SelectedSectionUtility;

import java.util.List;

public class SectionAdapter  extends RecyclerView.Adapter<SectionAdapter.MyViewHolder>  {
    private List<Section> sectionList;
    Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public String LOG_TAG = SectionAdapter.class.getSimpleName();
        public TextView readingLevelName, description;


        public MyViewHolder(View view) {
            super(view);
            readingLevelName = view.findViewById(R.id.tv_reading_level_name);
            description = view.findViewById(R.id.tv_description);
        }
    }

    public SectionAdapter(List<Section> sectionList, Context context) {
        this.sectionList = sectionList;
        this.mContext = context;

        Logger.logError("List",""+sectionList.size());
    }

    @Override
    public SectionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reading_level_row_list, parent, false);

        return new SectionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SectionAdapter.MyViewHolder holder, int position) {
        final Section section = this.sectionList.get(position);
        holder.readingLevelName.setText(section.getSectionTitle());
        holder.description.setText(section.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectedSectionUtility.getInstance().setSelectedSection(section);
                AttachmentListActivity.launch(mContext);
//                QualityInspectorDetailsActivity.launch(mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }}
