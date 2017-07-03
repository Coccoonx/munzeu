package com.lri.mobile.eecmunzeu.uis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;
import com.lri.mobile.eecmunzeu.uis.adapters.ListItemAdapter;
import com.lri.mobile.eecmunzeu.uis.adapters.MainItemAdapter;
import com.lri.mobile.eecmunzeu.utils.CoreUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    //    private BackEndService backEndService;
    public static List<Parish> mParishes;
    MainItemAdapter listItemAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);

        new DownloadFilesTask().execute();


    }

    @Override
    protected void onResume() {
        super.onResume();



        /*backEndService = BackEndService.retrofit.create(BackEndService.class);

//            if (tmpParish == 1L) {
        Call<List<Parish>> callParish = backEndService.getAllParishes();
        callParish.enqueue(new Callback<List<Parish>>() {
            @Override
            public void onResponse(Call<List<Parish>> call, Response<List<Parish>> response) {
                if (response.body() != null) {
                    mParishes = response.body();
                    Log.d(TAG, "parishes = " + mParishes);
                    progressBar.setVisibility(View.GONE);

                    if (mParishes != null && !mParishes.isEmpty()) {
                        listItemAdapter = new ListItemAdapter(MainActivity.this, mParishes);
                        recyclerView.setAdapter(listItemAdapter);
                        listItemAdapter.notifyDataSetChanged();
                    } else
                        //// TODO: 02/02/2017 Handle internationalization and message display
                        Toast.makeText(MainActivity.this, "Nothing to display", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, response.message() + "", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, response.message());

                }
            }

            @Override
            public void onFailure(Call<List<Parish>> call, Throwable t) {
                Log.d(TAG, Log.getStackTraceString(t));

            }
        });*/
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_browse) {
            startActivity(new Intent(MainActivity.this, BrowseActivity.class));
            finish();

        } /*else if (id == R.id.nav_send) {
//            startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            //finish();
        } else if (id == R.id.nav_manage) {

        }*/ else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out EEC app at: http://www.leroidelinformatique.com");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, List<Parish>> {

        @Override
        protected void onPostExecute(List<Parish> parishes) {
            super.onPostExecute(parishes);
            List<Parish> myRegion = new ArrayList<>();

            if (parishes != null) {
                mParishes = parishes;

                String key = "";
                for (Parish parish : mParishes) {
                    if (!parish.getRegion().equalsIgnoreCase(key)) {
                        key = parish.getRegion();
                        myRegion.add(parish);
                    }
                }

                for (Parish region : myRegion) {
                    for (Parish parish : mParishes) {
                        if (region.getRegion().equals(parish.getRegion()))
                            region.setNumberOfDevoted(region.getNumberOfDevoted() + 1);
                    }
                }
//                Log.d("Region", Arrays.toString(myRegion.toArray()));
                progressBar.setVisibility(View.GONE);
                listItemAdapter = new MainItemAdapter(MainActivity.this, myRegion);
                recyclerView.setAdapter(listItemAdapter);
            }
        }

        @Override
        protected List<Parish> doInBackground(Void... voids) {
            return CoreUtils.processData(MainActivity.this);
        }
    }


}
