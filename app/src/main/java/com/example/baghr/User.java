package com.example.baghr;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public int describeContents() {return 0;}

    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String type;
    public double hours_worked;

    // used to send User to next activity (between login and main activity)
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(type);
        dest.writeDouble(hours_worked);
    }

    // used to receive User on new activity (from login to main activity)
    public User(Parcel dest) {
        first_name = dest.readString();
        last_name = dest.readString();
        email = dest.readString();
        password = dest.readString();
        type = dest.readString();
        hours_worked = dest.readDouble();
    }

    public User(String fn, String ln, String em, String pw, double h, String t) {
        first_name = fn;
        last_name = ln;
        email = em;
        password = pw;
        type = t;
        hours_worked = h;
    }

    public User (String fn, String ln, String em, String pw, double h) {
        this(fn, ln, em, pw, h, "Standard");
    }

    public User() {
        this(null, null, null, null, 0,null);
    }


    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        public User[] newArray(int size) {
            return new User[0];
        }
    };
}
