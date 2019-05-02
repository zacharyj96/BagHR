package com.example.baghr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static DatabaseHelper sInstance;

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "BagHrDatabase";
    private static final String TABLE_ACCOUNT = "Account";
    private static final String TABLE_INVENTORY = "Inventory";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }



    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAccount = "CREATE TABLE IF NOT EXISTS Account (email TEXT PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL, hours_worked REAL, user_type TEXT NOT NULL)";

        // insert code for inventory creation here
        String createInventory = "CREATE TABLE IF NOT EXISTS Inventory (item_number INTEGER PRIMARY KEY, aisle TEXT NOT NULL, row_number INTEGER NOT NULL, shelf TEXT NOT NULL, description TEXT NOT NULL, is_stored INTEGER, price REAL)";


        db.execSQL(createAccount);
        db.execSQL(createInventory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);

        // insert code for inventory drop here

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);



        onCreate(db);
    }

    public boolean addAccount(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", u.first_name);
        contentValues.put("last_name", u.last_name);
        contentValues.put("email", u.email);
        contentValues.put("password", u.password);
        contentValues.put("hours_worked", u.hours_worked);
        contentValues.put("user_type", u.type);

        Log.d(TAG, "addAccount: Adding " + u.first_name + " " + u.last_name + " " + u.email + " " + u.password + " " + u.hours_worked + " " + u.type + " to " + TABLE_ACCOUNT);

        long result = db.insert(TABLE_ACCOUNT, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean addItem(Item i) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("aisle", i.aisle);
        contentValues.put("row_number", i.row_number);
        contentValues.put("shelf", i.shelf);
        contentValues.put("item_number", i.item_number);
        contentValues.put("description", i.description);
        contentValues.put("is_stored", i.is_stored);
        contentValues.put("price", i.price);

        Log.d(TAG, "addItem: Adding " + i.aisle + " " + i.row_number + " " + i.shelf + " " + i.item_number + " " + i.description + " " + i.is_stored + " " + i.price + " to " + TABLE_INVENTORY);

        long result = db.insert(TABLE_INVENTORY, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateIsStored(String aisle, int row, String shelf) {
        String itemUpdateQuery = String.format("UPDATE Inventory SET is_stored = 0 WHERE aisle = '%s' AND row_number = %d AND shelf = '%s'", aisle, row, shelf);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(itemUpdateQuery);
    }

    public void updateHoursWorked(String email, double hoursWorked) {
        String userUpdateQuery = String.format("UPDATE Account SET hours_worked = %f WHERE email = '%s'", hoursWorked, email);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(userUpdateQuery);
    }

    public void updateUserByEmail(String email, String firstName, String lastName) {
        String userUpdateQuery = String.format("UPDATE Account SET first_name = '%s', last_name = '%s' WHERE email = '%s'", firstName, lastName, email);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(userUpdateQuery);
    }

    public int getNumItems() {
        int count = -1;
        String itemSelectQuery = String.format("SELECT * FROM Inventory");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(itemSelectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                count = cursor.getCount();
            } else {
                count = 0;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error retrieving item count from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    public ArrayList<Item> getItems(int stored) {
        ArrayList<Item> items = new ArrayList<>();
        String itemSelectQuery = String.format("SELECT * FROM Inventory WHERE is_stored = %d", stored);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(itemSelectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Item i = new Item();
                    i.aisle = cursor.getString(cursor.getColumnIndex("aisle"));
                    i.row_number = cursor.getInt(cursor.getColumnIndex("row_number"));
                    i.shelf = cursor.getString(cursor.getColumnIndex("shelf"));
                    i.item_number = cursor.getInt(cursor.getColumnIndex("item_number"));
                    i.description = cursor.getString(cursor.getColumnIndex("description"));
                    i.is_stored = cursor.getInt(cursor.getColumnIndex("is_stored"));
                    i.price = cursor.getInt(cursor.getColumnIndex("price"));
                    items.add(i);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error retrieving item profile from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return items;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String userSelectQuery = String.format("SELECT * FROM Account");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(userSelectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User u = new User();
                    u.first_name = cursor.getString(cursor.getColumnIndex("first_name"));
                    u.last_name = cursor.getString(cursor.getColumnIndex("last_name"));
                    u.email = cursor.getString(cursor.getColumnIndex("email"));
                    u.password = cursor.getString(cursor.getColumnIndex("password"));
                    u.hours_worked = cursor.getDouble(cursor.getColumnIndex("hours_worked"));
                    u.type = cursor.getString(cursor.getColumnIndex("user_type"));
                    users.add(u);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error retrieving user profile from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return users;
    }

    public ArrayList<Item> getItemByLocation(String aisle, int row, String shelf) {
        ArrayList<Item> items = new ArrayList<>();
        String itemSelectQuery = String.format("SELECT * FROM Inventory WHERE aisle = '%s' AND row_number = %d AND shelf = '%s' AND is_stored = 1", aisle, row, shelf);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(itemSelectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Item i = new Item();
                    i.aisle = cursor.getString(cursor.getColumnIndex("aisle"));
                    i.row_number = cursor.getInt(cursor.getColumnIndex("row_number"));
                    i.shelf = cursor.getString(cursor.getColumnIndex("shelf"));
                    i.item_number = cursor.getInt(cursor.getColumnIndex("item_number"));
                    i.description = cursor.getString(cursor.getColumnIndex("description"));
                    i.is_stored = cursor.getInt(cursor.getColumnIndex("is_stored"));
                    i.price = cursor.getInt(cursor.getColumnIndex("price"));
                    items.add(i);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error retrieving item profile from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return items;
    }

    public ArrayList<User> getUserByEmail(String email) {
        ArrayList<User> users = new ArrayList<>();
        String acctSelectQuery = String.format("SELECT * FROM ACCOUNT WHERE email = '%s'", email);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(acctSelectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User u = new User();
                    u.first_name = cursor.getString(cursor.getColumnIndex("first_name"));
                    u.last_name = cursor.getString(cursor.getColumnIndex("last_name"));
                    u.email = cursor.getString(cursor.getColumnIndex("email"));
                    u.password = cursor.getString(cursor.getColumnIndex("password"));
                    u.hours_worked = cursor.getDouble(cursor.getColumnIndex("hours_worked"));
                    u.type = cursor.getString(cursor.getColumnIndex("user_type"));
                    users.add(u);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error retrieving user profile from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return users;
    }
}
