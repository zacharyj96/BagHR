package com.example.baghr;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    public int describeContents() {return 0;}

    public int item_number;
    public String aisle;
    public int row_number;
    public String shelf;
    public String description;
    public int is_stored;
    public double price;

    // used to send User to next activity (between login and main activity)
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(item_number);
        dest.writeString(aisle);
        dest.writeInt(row_number);
        dest.writeString(shelf);
        dest.writeString(description);
        dest.writeInt(is_stored);
        dest.writeDouble(price);
    }

    // used to receive User on new activity (from login to main activity)
    public Item(Parcel dest) {
        item_number = dest.readInt();
        aisle = dest.readString();
        row_number = dest.readInt();
        shelf = dest.readString();
        description = dest.readString();
        is_stored = dest.readInt();
        price = dest.readDouble();
    }

    public Item(int i, String a, int r, String s, String d, int is, double p) {
        item_number = i;
        aisle = a;
        row_number = r;
        shelf = s;
        description = d;
        is_stored = is;
        price = p;
    }

    public Item() {
        this(0, null, 0, null, null, 0, 0);
    }


    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        public Item[] newArray(int size) {
            return new Item[0];
        }
    };
}
