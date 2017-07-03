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
    private ArrayList<String> pictureUrls = new ArrayList<>();
    private ArrayList<String> pastorImages = new ArrayList<>();
    private String displayName;
    private String pastorName;
    private String pastorPhoneNumber;
    private String website;
    private String region;
    private String district;
    private String annex;
    private double latitude;
    private double longitude;
    private int numberOfDevoted;

    public Parish() {
    }

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
        dest.writeString(this.region);
        dest.writeString(this.district);
        dest.writeString(this.annex);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeInt(this.numberOfDevoted);
    }

    protected Parish(Parcel in) {
        this.id = in.readLong();
        this.pictureUrls = in.createStringArrayList();
        this.pastorImages = in.createStringArrayList();
        this.displayName = in.readString();
        this.pastorName = in.readString();
        this.pastorPhoneNumber = in.readString();
        this.website = in.readString();
        this.region = in.readString();
        this.district = in.readString();
        this.annex = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.numberOfDevoted = in.readInt();
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

    @Override
    public String toString() {
        return "Parish{" +
                "id=" + id +
                ", pictureUrls=" + pictureUrls +
                ", pastorImages=" + pastorImages +
                ", displayName='" + displayName + '\'' +
                ", pastorName='" + pastorName + '\'' +
                ", pastorPhoneNumber='" + pastorPhoneNumber + '\'' +
                ", website='" + website + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", annex='" + annex + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", numberOfDevoted=" + numberOfDevoted +
                '}';
    }
}
