// Mobile Platform Development Coursework 2018-2019
// Ian Campbell 200507045

package com.mpd.coursework.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WidgetClass implements Parcelable {

    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String category;
    private String geoLat;
    private String geoLong;

    public WidgetClass() {
        
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {

        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {

        this.pubDate = pubDate;
    }

    public String getCetagory() {
        return category;
    }

    public void setCatagory(String category) {
        this.category = category;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {

        this.geoLat = geoLat;
    }

    public String getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(String geoLong) {

        this.geoLong = geoLong;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeString(this.pubDate);
        dest.writeString(this.category);
        dest.writeString(this.geoLat);
        dest.writeString(this.geoLong);
    }

    public WidgetClass(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.pubDate = in.readString();
        this.category = in.readString();
        this.geoLat = in.readString();
        this.geoLong = in.readString();
    }

    public static final Parcelable.Creator<WidgetClass> CREATOR = new Parcelable.Creator<WidgetClass>() {
        @Override
        public WidgetClass createFromParcel(Parcel source) {
            return new WidgetClass(source);
        }

        @Override
        public WidgetClass[] newArray(int size) {
            return new WidgetClass[size];
        }
    };
}





