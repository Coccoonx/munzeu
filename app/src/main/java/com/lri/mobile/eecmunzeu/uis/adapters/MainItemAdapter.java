package com.lri.mobile.eecmunzeu.uis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.uis.viewholders.MainItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lyonnel Dzotang on 25/01/2017.
 */

public class MainItemAdapter extends RecyclerView.Adapter<MainItemViewHolder> {

    ArrayList<Integer> mDataSet ;
    Context mContext;

    public MainItemAdapter(Context context, ArrayList arrayList) {
        super();
        mDataSet = arrayList;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {

        holder.people.setText(mDataSet.get(position).toString());
        Picasso.with(mContext).load(R.drawable.eecplateau).into(holder.picture);

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
