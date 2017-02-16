package com.lri.mobile.eecmunzeu.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashSet;

import lombok.Data;

@Data
public class Parish implements Parcelable {

    private long id;
    private ArrayList<String> pictureUrls;
    private ArrayList<String> pastorImages;
    private String displayName;
    private String pastorName;
    private String pastorPhoneNumber;
    private String website;
    private String district;
    private double latitude;
    private double longitude;
    private double numberOfDevoted;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeStringList(this.pictureUrls);
        dest.writeStringList(this.pastorImages);
        dest.writeString(this.displayName);
        dest.writeString(this.pastorName);
        dest.writeString(this.pastorPhoneNumber);
        dest.writeString(this.website);
        dest.writeString(this.district);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.numberOfDevoted);
    }

    protected Parish(Parcel in) {
        this.id = in.readLong();
        this.pictureUrls = in.createStringArrayList();
        this.pastorImages = in.createStringArrayList();
        this.displayName = in.readString();
        this.pastorName = in.readString();
        this.pastorPhoneNumber = in.readString();
        this.website = in.readString();
        this.district = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.numberOfDevoted = in.readDouble();
    }

    public static final Parcelable.Creator<Parish> CREATOR = new Parcelable.Creator<Parish>() {
        @Override
        public Parish createFromParcel(Parcel source) {
            return new Parish(source);
        }

        @Override
        public Parish[] newArray(int size) {
            return new Parish[size];
        }
    };
}
