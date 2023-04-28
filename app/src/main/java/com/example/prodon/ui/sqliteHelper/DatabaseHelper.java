package com.example.prodon.ui.sqliteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.prodon.MainActivity;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "proDonn.db";
    private static final int DATABASE_VERSION = 2;

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
    private static final String TABLE_PAYMENTS = "Payments";
    private static final String COLUMN_PAYMENT_ID = "payment_id";
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_PAYMENT_DATE = "payment_date";


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
    private static final String SQL_CREATE_PAYMENTS =
            "CREATE TABLE " + TABLE_PAYMENTS + " (" +
                    COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PLAYER_ID + " INTEGER," +
                    COLUMN_YEAR + " INTEGER," +
                    COLUMN_MONTH + " INTEGER," +
                    COLUMN_AMOUNT + " REAL," +
                    COLUMN_PAYMENT_DATE + " TEXT," +
                    "FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + TABLE_PLAYERS + "(" + COLUMN_ID + ")," +
                    "FOREIGN KEY (" + COLUMN_COACH_ID + ") REFERENCES " + TABLE_COACHES + "(" + COLUMN_ID + ")" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COACHES);
        db.execSQL(SQL_CREATE_GROUPS);
        db.execSQL(SQL_CREATE_PLAYERS);
        db.execSQL(SQL_CREATE_PAYMENTS);
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
        cv.put(COLUMN_PARENT_NAME,player.getParentName());
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
    public ArrayList<PlayerModel> searchPlayer(String playerName, String playerLname, Context context) {
        ArrayList<PlayerModel> players= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { COLUMN_ID,COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PARENT_NAME, COLUMN_DATE_OF_BIRTH, COLUMN_PARENT_PHONE_NUMBER, COLUMN_PLAYER_PHONE_NUMBER, COLUMN_DATE_JOINED, COLUMN_STATUS, COLUMN_STATUS_SINCE, COLUMN_GROUP_ID };

        String selection = COLUMN_FIRST_NAME + " LIKE ? AND " + COLUMN_LAST_NAME + " LIKE ?";
        String[] selectionArgs = { "%" + playerName + "%", "%" + playerLname + "%" };

        Cursor cursor = db.query(
                TABLE_PLAYERS,     // The table to query
                projection,        // The columns to return
                selection,         // The columns for the WHERE clause
                selectionArgs,     // The values for the WHERE clause
                null,              // Don't group the rows
                null,              // Don't filter by row groups
                null               // The sort order
        );
        PlayerModel playerModel;
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String fName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
                String lName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
                String parentName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_NAME));
                String parentPhone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_PHONE_NUMBER));
                String playerPhone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_PHONE_NUMBER));
                String dateJoined = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_JOINED));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH));
                playerModel = new PlayerModel(id, fName, lName, parentName, parentPhone, playerPhone, dateJoined, year);
                players.add(playerModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return players;
    }

    public void addNewPayment(int playerId, int year, int month, double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_ID, playerId);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_MONTH, month);
        values.put(COLUMN_AMOUNT, amount);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());
        values.put(COLUMN_PAYMENT_DATE, currentDate);
        db.insert(TABLE_PAYMENTS, null, values);
        db.close();
    }



    public PlayerModel getPlayerById(int playerId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_FIRST_NAME,
                COLUMN_PARENT_NAME,
                COLUMN_LAST_NAME,
                COLUMN_DATE_OF_BIRTH,
                COLUMN_PARENT_PHONE_NUMBER,
                COLUMN_PLAYER_PHONE_NUMBER,
                COLUMN_DATE_JOINED,
                COLUMN_STATUS,
                COLUMN_STATUS_SINCE,
                COLUMN_GROUP_ID
        };

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(playerId) };

        Cursor cursor = db.query(
                TABLE_PLAYERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        PlayerModel player = null;
        if (cursor.moveToFirst()) {
            player = new PlayerModel(playerId,
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PARENT_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_JOINED)),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH)).substring(0, 4)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_SINCE)),
                    getGroupNameById(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GROUP_ID))))
            );

        }

        cursor.close();
        db.close();

        return player;
    }

    public String getGroupNameById(int groupId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_GROUP_NAME
        };

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(groupId) };

        Cursor cursor = db.query(
                TABLE_GROUPS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String groupName = null;
        if (cursor.moveToFirst()) {
            groupName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GROUP_NAME));
        }

        cursor.close();
        db.close();

        return groupName;
    }

    }



