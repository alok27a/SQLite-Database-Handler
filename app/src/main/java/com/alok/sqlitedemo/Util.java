package com.alok.sqlitedemo;

public class Util {
    //    Database related items
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact_db";
    public static final String TABLE_NAME = "contacts";

    //    Contacts table column name
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phone_number";

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyName() {
        return KEY_NAME;
    }

    public static String getKeyPhoneNumber() {
        return KEY_PHONE_NUMBER;
    }

}