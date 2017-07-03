package com.lri.mobile.eecmunzeu.uis;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;
import com.lri.mobile.eecmunzeu.uis.adapters.DetailsItemAdapter;
import com.lri.mobile.eecmunzeu.uis.adapters.ListItemAdapter;
import com.lri.mobile.eecmunzeu.uis.adapters.MainItemAdapter;
import com.lri.mobile.eecmunzeu.utils.CoreUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public List<Parish> mParishes;
    ListItemAdapter listItemAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Parish parish = bundle.getParcelable("region");
            getSupportActionBar().setTitle(parish.getRegion());


            if (MainActivity.mParishes != null) {
                mParishes = MainActivity.mParishes;
                List list1 = new ArrayList();
                for (Parish parish1 : mParishes) {
                    if (parish.getRegion().equals(parish1.getRegion())) {
                        list1.add(parish1);
                    }
                }
                progressBar.setVisibility(View.GONE);
                listItemAdapter = new ListItemAdapter(ListActivity.this, list1);
                recyclerView.setAdapter(listItemAdapter);
            }

        }


    }

//    private class DownloadFilesTask extends AsyncTask<Void, Void, List<Parish>> {
//
//        @Override
//        protected void onPostExecute(List<Parish> parishes) {
//            super.onPostExecute(parishes);
//            List<Parish> myRegion = new ArrayList<>();
//
//            if (parishes != null) {
//                mParishes = parishes;
//
//                String key = "";
//                for (Parish parish : mParishes) {
//                    if (!parish.getRegion().equalsIgnoreCase(key)) {
//                        key = parish.getRegion();
//                        myRegion.add(parish);
//                    }
//                }
//
//                for (Parish region : myRegion) {
//                    for (Parish parish : mParishes) {
//                        if (region.getRegion().equals(parish.getRegion()))
//                            region.setNumberOfDevoted(region.getNumberOfDevoted() + 1);
//                    }
//                }
////                Log.d("Region", Arrays.toString(myRegion.toArray()));
//                progressBar.setVisibility(View.GONE);
//                listItemAdapter = new MainItemAdapter(ListActivity.this, myRegion);
//                recyclerView.setAdapter(listItemAdapter);
//            }
//        }
//
//        @Override
//        protected List<Parish> doInBackground(Void... voids) {
//            return CoreUtils.processData(MainActivity.this);
//        }
//    }


}
