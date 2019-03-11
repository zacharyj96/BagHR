package com.example.baghr;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static DatabaseHelper sInstance;

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "BagHrDatabase";
    private static final String TABLE_ACCOUNT = "Account";
    private static final String TABLE_INVENTORY = "Inventory";
    private static final String TABLE_HR = "HR";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS Account (email TEXT PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, user_type TEXT NOT NULL)";

        // insert code for inventory creation here

        // insert code for hr creation here


        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);

        // insert code for inventory drop here

        // insert code for hr drop here


        onCreate(db);
    }

    public boolean addAccount(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", u.first_name);
        contentValues.put("last_name", u.last_name);
        contentValues.put("email", u.email);
        contentValues.put("password", u.password);
        contentValues.put("user_type", u.type);

        Log.d(TAG, "addAccount: Adding " + u.first_name + " " + u.last_name + " " + u.email + " " + u.password + " " + u.type + " to " + TABLE_ACCOUNT);

        long result = db.insert(TABLE_ACCOUNT, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
