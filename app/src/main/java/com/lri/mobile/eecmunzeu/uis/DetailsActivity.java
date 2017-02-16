package com.lri.mobile.eecmunzeu.uis;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;
import com.lri.mobile.eecmunzeu.uis.adapters.DetailsItemAdapter;
import com.lri.mobile.eecmunzeu.uis.adapters.MainItemAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    Location parishLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView toolbarImage = (ImageView) findViewById(R.id.toolbar_image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ouvrir la navigation?", Snackbar.LENGTH_LONG)
                        .setAction("OUVRIR", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Handle user action
                                Intent intent = new Intent(DetailsActivity.this, BrowseActivity.class);
                                if (parishLocation!=null)
                                    intent.putExtra("location", parishLocation);
                                startActivity(intent);
                            }
                        }).show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_details);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Parish parish = bundle.getParcelable("parish");
            getSupportActionBar().setTitle(parish.getDisplayName());
            Picasso.with(this)
                    .load(parish.getPictureUrls().get(0))
                    //.resize(30,30)
                    .placeholder(R.color.colorPrimary)
                    .into(toolbarImage);

            parishLocation = new Location(parish.getDisplayName());
            parishLocation.setLatitude(parish.getLatitude());
            parishLocation.setLongitude(parish.getLongitude());

            DetailsItemAdapter mi = new DetailsItemAdapter(this, parish);
            recyclerView.setAdapter(mi);
        }


    }
}
