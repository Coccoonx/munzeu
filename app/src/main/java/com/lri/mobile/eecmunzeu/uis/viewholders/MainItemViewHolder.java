package com.lri.mobile.eecmunzeu.uis.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lri.mobile.eecmunzeu.R;

/**
 * Created by Lyonnel Dzotang on 30/01/2017.
 */


public class MainItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView subtitle;
    public TextView pastorName;
    public TextView people;
    public ImageView picture;
    public CardView container;

    public MainItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_title);
        subtitle = (TextView) itemView.findViewById(R.id.item_subtitle);
        pastorName = (TextView) itemView.findViewById(R.id.item_label_pastor);
        people = (TextView) itemView.findViewById(R.id.item_label_people);
        picture = (ImageView) itemView.findViewById(R.id.item_image);
        container = (CardView) itemView.findViewById(R.id.card_view);


    }
}
