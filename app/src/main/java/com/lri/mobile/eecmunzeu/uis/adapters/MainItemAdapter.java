package com.lri.mobile.eecmunzeu.uis.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;
import com.lri.mobile.eecmunzeu.uis.DetailsActivity;
import com.lri.mobile.eecmunzeu.uis.viewholders.MainItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyonnel Dzotang on 25/01/2017.
 */

public class MainItemAdapter extends RecyclerView.Adapter<MainItemViewHolder> {

    List<Parish> mDataSet;
    Context mContext;

    public MainItemAdapter(Context context, List<Parish> arrayList) {
        super();
        mDataSet = arrayList;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        final Parish parish = mDataSet.get(position);
        holder.title.setText(parish.getDisplayName());
        holder.subtitle.setText(parish.getDistrict());
        holder.pastorName.setText(parish.getPastorName());
        holder.people.setText("" + parish.getNumberOfDevoted());
        Picasso.with(mContext).load(parish.getPictureUrls().get(0)).into(holder.picture);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("parish", parish);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MainItemViewHolder vh = new MainItemViewHolder(v);
        return vh;
    }


}
