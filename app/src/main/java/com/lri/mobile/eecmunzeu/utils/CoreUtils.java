package com.lri.mobile.eecmunzeu.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.lri.mobile.eecmunzeu.R;
import com.lri.mobile.eecmunzeu.core.model.Parish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Lyonnel Dzotang on 04/02/2017.
 */

public class CoreUtils {

    private static final int ALL_PERMISSIONS_REQUEST = 300;
    private static final String PATH_TO_SERVER = "http://leroidelinformatique.com/images/eecmunzeu/database.csv";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static double getDistance(LatLng location1, LatLng location2) {
        double theta = location1.longitude - location2.longitude;
        double dist = Math.sin(deg2rad(location1.latitude)) * Math.sin(deg2rad(location2.latitude)) + Math.cos(deg2rad(location1.latitude)) * Math.cos(deg2rad(location2.latitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    public static boolean checkAllPermissions(Context context) {
        if (/*ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || */ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                /*|| ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED*/) {
            return false;

        } else {
           /* AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);
            alertBuilder.setCancelable(true);
            alertBuilder.setName(getResources().getString(R.string.permission_denied));
            alertBuilder.setMessage(getResources().getString(R.string.permission_denied_explanation_login));
            alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alert = alertBuilder.create();
            alert.show();*/

            return true;

        }
    }

    public static void alertAndRequestPermission(final Activity context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(context.getResources().getString(R.string.permission_necessary));
        alertBuilder.setMessage(Html.fromHtml(context.getResources().getString(R.string.permission_explanation)));
        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(context, new String[]{
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, ALL_PERMISSIONS_REQUEST);
            }
        });

        final AlertDialog alert = alertBuilder.create();

//        alert.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface arg0) {
//                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));
//            }
//        });
        alert.show();
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));

//        if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                || ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.CAMERA)
//                || ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
//                || ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                || ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//        } else {
//            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
//                    Manifest.permission.READ_CONTACTS,
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//            }, ALL_PERMISSIONS_REQUEST);
//        }
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static Parish getMinLatLng(LatLng currentLatLong, List<Parish> list) {
        double distance = Double.MAX_VALUE;
        Parish correspondingParish = null;
        for (Parish p : list) {

            double dist = getDistance(currentLatLong, new LatLng(p.getLatitude(), p.getLongitude()));

            if (distance > dist) {

                distance = dist;
                correspondingParish = p;
            }
        }
        return correspondingParish;
    }

    private static List<String[]> downloadDataBaseFile(){
        URL mUrl = null;
        List<String[]> csvLine = new ArrayList<>();
        String[] content = null;
        try {
            mUrl = new URL(PATH_TO_SERVER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert mUrl != null;
            URLConnection connection = mUrl.openConnection();
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null){
                content = line.split(";");
                csvLine.add(content);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Core", Log.getStackTraceString(e));
        }
        return csvLine;
    }

    public static List<String[]> readCsv(Context context) {
        List<String[]> csvLine = new ArrayList<>();
        String[] content = null;
        try {
//                InputStream inputStream = context.getAssets().open("database.csv");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("database.csv"), "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                content = line.split(";");
                csvLine.add(content);
//                Log.d("data", Arrays.toString(content));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvLine;
    }

    public static List<Parish> processData(Context context) {
        List<Parish> parishes = new ArrayList<>();
        List<String[]> incomingData = downloadDataBaseFile();
        if (incomingData == null) {
            incomingData = readCsv(context);
        }

        if (incomingData != null) {
            incomingData.remove(0);
            for (String[] data : incomingData) {

                Parish parish = new Parish();

                if (!data[0].trim().isEmpty())
                    parish.setRegion(data[0]);

                if (!data[1].trim().isEmpty())
                    parish.setDistrict(data[1]);

                if (!data[2].trim().isEmpty()) {
                    parish.setDisplayName(data[2]);
                }
                if (!data[3].trim().isEmpty()) {
                    parish.setAnnex(data[3]);
                }
                if (!data[4].trim().isEmpty() && !data[5].trim().isEmpty()) {
                    parish.setPastorName(data[4] + " " + data[5]);
                }
                if (!data[6].trim().isEmpty()) {
                    parish.setPastorPhoneNumber(data[6]);
                }
                if (!data[7].trim().isEmpty()) {
                    parish.setPastorPhoneNumber(parish.getPastorPhoneNumber()+" / " +data[7]);
                }
                if (!data[8].trim().isEmpty() && !data[9].trim().isEmpty()) {
                    parish.setLatitude(Double.valueOf(data[8]));
                    parish.setLongitude(Double.valueOf(data[9]));
                }
                if (!data[10].trim().isEmpty()) {
                    parish.getPictureUrls().add(data[10]);
                }
                if (!data[11].trim().isEmpty()) {
                    parish.setWebsite(data[11]);
                }

                parishes.add(parish);
                Log.d("data", parish.toString());
            }
            return parishes;
        }
        return null;
    }


}
