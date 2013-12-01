package com.example.taotaokanAndroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-13
 * Time: AM11:01
 * To change this template use File | Settings | File Templates.
 */
public class WaresItems implements Parcelable {
    public String imageString;
    public String titleString;
    public String contentString;
    public String urlString;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(imageString);
        dest.writeString(titleString);
        dest.writeString(contentString);
        dest.writeString(urlString);
    }
    public static final Parcelable.Creator<WaresItems> CREATOR = new Parcelable.Creator<WaresItems>() {

        @Override
        public WaresItems createFromParcel(Parcel source) {
            WaresItems p = new WaresItems();
            p.imageString=source.readString();
            p.titleString =source.readString();
            p.contentString = source.readString();
            p.urlString = source.readString();
            return p;
        }

        @Override
        public WaresItems[] newArray(int size) {
            // TODO Auto-generated method stub
            return null;
        }
    };
}
