package com.lri.mobile.eecmunzeu.uis.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lri.mobile.eecmunzeu.R;

/**
 * Created by Lyonnel Dzotang on 30/01/2017.
 */


public class DetailsItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView content;
    public ImageView icon;

    public DetailsItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_label);
        content = (TextView) itemView.findViewById(R.id.item_content);
        icon = (ImageView) itemView.findViewById(R.id.item_icon);


    }
}
