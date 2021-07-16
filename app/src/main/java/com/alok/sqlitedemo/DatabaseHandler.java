package com.alok.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NAME = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + "INTEGER PRIMARY KEY, " + Util.KEY_NAME + " TEXT," +
                Util.KEY_PHONE_NUMBER + " TEXT " + ")";
        // create table_name(key,name,phone_number)
        db.execSQL(CREATE_TABLE_NAME);//Creating a table.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_droptable);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});
        onCreate(db);
    }

    //    CRUD OPERATION
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, Util.getKeyName());
        values.put(Util.KEY_PHONE_NUMBER, Util.getKeyPhoneNumber());

//        Insert in row
        db.insert(Util.getTableName(), null, values);
        db.close();
    }

    //to get a row in the table
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_NAME},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );
        if (cursor != null)
            cursor.moveToFirst();


        Contact contact = new Contact();
        contact.setSno(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));
        return contact;
    }

    // to get all the contacts in table
    public List<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

//        To get all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

//        Loop through data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setSno(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                list.add(contact);
            } while (cursor.moveToNext());
        }
        return list;
    }

}