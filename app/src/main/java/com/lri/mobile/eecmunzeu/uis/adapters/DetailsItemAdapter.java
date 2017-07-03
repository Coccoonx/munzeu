package com.lri.mobile.eecmunzeu.uis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;
import com.lri.mobile.eecmunzeu.uis.viewholders.DetailsItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lombok.Data;

/**
 * Created by Lyonnel Dzotang on 25/01/2017.
 */

public class DetailsItemAdapter extends RecyclerView.Adapter<DetailsItemViewHolder> {

    ArrayList<InfoWrapper> mDataSet;
    Context mContext;

    public DetailsItemAdapter(Context context, Parish baseData) {
        super();
        mDataSet = expandData(baseData);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(DetailsItemViewHolder holder, int position) {
        InfoWrapper wrapper = mDataSet.get(position);
        holder.content.setText(wrapper.getContent());
        holder.title.setText(wrapper.getTitle());
        holder.icon.setImageResource(wrapper.getIcon());
//        Picasso.with(mContext).load(R.drawable.eecplateau).into(holder.picture);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public DetailsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        DetailsItemViewHolder vh = new DetailsItemViewHolder(v);
        return vh;
    }

    private ArrayList<InfoWrapper> expandData(Parish parish) {
        ArrayList<InfoWrapper> infos = new ArrayList<>();
        infos.add(new InfoWrapper(parish.getDistrict(), "District", R.drawable.ic_district));
        if (parish.getAnnex()!=null && !parish.getAnnex().isEmpty())
            infos.add(new InfoWrapper(parish.getAnnex(), "Annexe", R.drawable.ic_home));
//        infos.add(new InfoWrapper("" + parish.getNumberOfDevoted(), "Fidèles", R.drawable.ic_people));
        infos.add(new InfoWrapper(parish.getPastorName(), "Pasteur", R.drawable.ic_pastor));
        infos.add(new InfoWrapper(parish.getPastorPhoneNumber(), "No du Pasteur", R.drawable.ic_phone));
        infos.add(new InfoWrapper(parish.getWebsite(), "Site Web", R.drawable.ic_website));
        return infos;

    }

    @Data
    class InfoWrapper {
        String content;
        String title;
        int icon;

        public InfoWrapper(String content, String title, int icon) {
            this.content = content;
            this.title = title;
            this.icon = icon;
        }
    }


}
