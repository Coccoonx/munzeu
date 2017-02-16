package com.lri.mobile.eecmunzeu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.maps.model.LatLng;
import com.lri.mobile.eecmunzeu.core.model.Parish;

import java.util.Date;
import java.util.List;

/**
 * Created by Lyonnel Dzotang on 04/02/2017.
 */

public class CoreUtils {

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
}
