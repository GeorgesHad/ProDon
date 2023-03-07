package com.example.prodon.ui.sqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "football_academy.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_PLAYERS = "Players";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DATE_JOINED = "date_joined";
    private static final String COLUMN_GROUP_ID = "group_id";

    private static final String TABLE_GROUPS = "Groups";
    private static final String COLUMN_GROUP_NAME = "name";
    private static final String COLUMN_COACH_ID = "coach_id";

    private static final String TABLE_COACHES = "Coaches";
    private static final String COLUMN_COACH_FIRST_NAME = "first_name";
    private static final String COLUMN_COACH_LAST_NAME = "last_name";

    // SQL statements to create the tables
    private static final String SQL_CREATE_PLAYERS =
            "CREATE TABLE " + TABLE_PLAYERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_FIRST_NAME + " TEXT," +
                    COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_DATE_OF_BIRTH + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_PHONE_NUMBER + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_DATE_JOINED + " TEXT," +
                    COLUMN_GROUP_ID + " INTEGER," +
                    "FOREIGN KEY (" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_ID + ")" +
                    ");";

    private static final String SQL_CREATE_GROUPS =
            "CREATE TABLE " + TABLE_GROUPS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_GROUP_NAME + " TEXT," +
                    COLUMN_COACH_ID + " INTEGER," +
                    "FOREIGN KEY (" + COLUMN_COACH_ID + ") REFERENCES " + TABLE_COACHES + "(" + COLUMN_ID + ")" +
                    ");";

    private static final String SQL_CREATE_COACHES =
            "CREATE TABLE " + TABLE_COACHES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_COACH_FIRST_NAME + " TEXT," +
                    COLUMN_COACH_LAST_NAME + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COACHES);
        db.execSQL(SQL_CREATE_GROUPS);
        db.execSQL(SQL_CREATE_PLAYERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not implemented in this example
    }
}



