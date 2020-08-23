package org.techtown.merge;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.widget.ImageView;

import java.net.URL;

public class Person {

    private String title;
    private String address;
    private String coverImage;
    private String songURL;


    public Person(){}
    public Person(String title, String address, String coverImage, String songURL) {
        this.title = title;
        this.address = address;
        this.coverImage = coverImage;
        this.songURL = songURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }
}
