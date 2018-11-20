package com.example.chaitanyadeshpande.sor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.R;
import com.example.chaitanyadeshpande.sor.activities.ReadingLevelListActivity;
import com.example.chaitanyadeshpande.sor.activities.SectionListActivity;
import com.example.chaitanyadeshpande.sor.request_response.ReadingLevel;
import com.example.chaitanyadeshpande.sor.utilities.SelectedReadingLevelUtility;


import java.util.List;


public class ReadingLevelAdapter extends RecyclerView.Adapter<ReadingLevelAdapter.MyViewHolder>  {

    private List<ReadingLevel> readingLevelList;
    Context mContext;
    ReadingLevelListActivity readingLevelListActivity;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public String LOG_TAG = ReadingLevelAdapter.class.getSimpleName();
        public TextView readingLevelName, description;


        public MyViewHolder(View view) {
            super(view);
            readingLevelName = view.findViewById(R.id.tv_reading_level_name);
            description = view.findViewById(R.id.tv_description);
        }
    }

    public ReadingLevelAdapter(List<ReadingLevel> readingLevelList, Context context, ReadingLevelListActivity readingLevelListActivity) {
        this.readingLevelList = readingLevelList;
        this.mContext = context;
        this.readingLevelListActivity = readingLevelListActivity;
    }

    @Override
    public ReadingLevelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reading_level_row_list, parent, false);

        return new ReadingLevelAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReadingLevelAdapter.MyViewHolder holder, int position) {
        final ReadingLevel readingLevel = this.readingLevelList.get(position);
        holder.readingLevelName.setText(readingLevel.getTitle());
        holder.description.setText(readingLevel.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectedReadingLevelUtility.getInstance().setSelectedReadingLevel(readingLevel);
                SectionListActivity.launch(mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        return readingLevelList.size();
    }
}

