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

    public String itemClickURLString = "";
    public String itemDescription = "";
    public String itemFreePostage = "";

    public String itemImageURLString = "";
    public String itemName = "";
    public String itemPrice = "";

    public String itemPromotionPrice = "";
    public String itemType= "";
    public String tradingVolumeInThirtyDays = "";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(itemClickURLString);
        dest.writeString(itemDescription);
        dest.writeString(itemFreePostage);

        dest.writeString(itemImageURLString);
        dest.writeString(itemName);
        dest.writeString(itemPrice);

        dest.writeString(itemPromotionPrice);
        dest.writeString(itemType);
        dest.writeString(tradingVolumeInThirtyDays);
    }
    public static final Parcelable.Creator<WaresItems> CREATOR = new Parcelable.Creator<WaresItems>() {

        @Override
        public WaresItems createFromParcel(Parcel source) {
            WaresItems p = new WaresItems();
            p.itemClickURLString = source.readString();
            p.itemDescription= source.readString();
            p.itemFreePostage = source.readString();

            p.itemImageURLString = source.readString();
            p.itemName = source.readString();
            p.itemPrice = source.readString();

            p.itemPromotionPrice = source.readString();
            p.itemType = source.readString();
            p.tradingVolumeInThirtyDays = source.readString();


            return p;
        }

        @Override
        public WaresItems[] newArray(int size) {
            // TODO Auto-generated method stub
            return null;
        }
    };
}
