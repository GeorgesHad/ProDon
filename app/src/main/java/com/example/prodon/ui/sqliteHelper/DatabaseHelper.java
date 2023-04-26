package com.example.prodon.ui.sqliteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.prodon.MainActivity;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "proDon.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_PLAYERS = "Players";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_PARENT_NAME = "parent_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    private static final String COLUMN_PARENT_PHONE_NUMBER = "parent_phone_number";
    private static final String COLUMN_PLAYER_PHONE_NUMBER = "player_phone_number";
    private static final String COLUMN_DATE_JOINED = "date_joined";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_STATUS_SINCE = "status_since";
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
                    COLUMN_PARENT_NAME + " TEXT," +
                    COLUMN_DATE_OF_BIRTH + " TEXT," +
                    COLUMN_PARENT_PHONE_NUMBER + " TEXT," +
                    COLUMN_PLAYER_PHONE_NUMBER + " TEXT," +
                    COLUMN_DATE_JOINED + " TEXT," +
                    COLUMN_STATUS + " TEXT," +
                    COLUMN_STATUS_SINCE + " TEXT," +
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
    public boolean addPlayer(PlayerModel player) {
        SQLiteDatabase db = getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = day + "-"+ month +"-"+ year;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, player.getfName());
        cv.put(COLUMN_LAST_NAME, player.getlName());
        cv.put(COLUMN_DATE_OF_BIRTH, player.getYear());
        cv.put(COLUMN_PLAYER_PHONE_NUMBER,player.getPlayerPhone());
        cv.put(COLUMN_PARENT_PHONE_NUMBER,player.getParentPhone());
        cv.put(COLUMN_DATE_JOINED,date);
        cv.put(COLUMN_STATUS,"Active");
        cv.put(COLUMN_STATUS_SINCE,date);
        cv.put(COLUMN_GROUP_ID,0);
        long insert = db.insert(TABLE_PLAYERS,null,cv);
        if (insert == -1)
        {
            return false;
        }
        else return true;
    }
    public void searchPlayer(String playerName,Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PARENT_NAME, COLUMN_DATE_OF_BIRTH, COLUMN_PARENT_PHONE_NUMBER, COLUMN_PLAYER_PHONE_NUMBER, COLUMN_DATE_JOINED, COLUMN_STATUS, COLUMN_STATUS_SINCE, COLUMN_GROUP_ID };

        String selection = COLUMN_FIRST_NAME + " LIKE ? OR " + COLUMN_LAST_NAME + " LIKE ?";
        String[] selectionArgs = { "%" + playerName + "%", "%" + playerName + "%" };

        Cursor cursor = db.query(
                TABLE_PLAYERS,     // The table to query
                projection,        // The columns to return
                selection,         // The columns for the WHERE clause
                selectionArgs,     // The values for the WHERE clause
                null,              // Don't group the rows
                null,              // Don't filter by row groups
                null               // The sort order
        );

        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            String parentName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_NAME));
            String dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH));
            String parentPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_PHONE_NUMBER));
            String playerPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_PHONE_NUMBER));
            String dateJoined = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_JOINED));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS));
            String statusSince = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_SINCE));
            int groupId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GROUP_ID));

            // Construct a string with the player's details
            StringBuilder builder = new StringBuilder();
            builder.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
            builder.append("Parent Name: ").append(parentName).append("\n");
            builder.append("Date of Birth: ").append(dateOfBirth).append("\n");
            builder.append("Parent Phone Number: ").append(parentPhoneNumber).append("\n");
            builder.append("Player Phone Number: ").append(playerPhoneNumber).append("\n");
            builder.append("Date Joined: ").append(dateJoined).append("\n");
            builder.append("Status: ").append(status).append("\n");
            builder.append("Status Since: ").append(statusSince).append("\n");
            builder.append("Group ID: ").append(groupId);

            // Display a toast with the player's details

            CharSequence text = builder.toString();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        cursor.close();
    }

    }



