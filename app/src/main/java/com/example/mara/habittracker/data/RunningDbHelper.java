package com.example.mara.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mara on 9.7.2017..
 */

/**
 * Database helper for the Running Diary app. Manages database creation and version management.
 */
public class RunningDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = RunningDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "running.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link RunningDbHelper}.
     *
     * @param context of the app
     */
    public RunningDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the running table
        String SQL_CREATE_RUNNING_TABLE = "CREATE TABLE " + RunningContract.RunningEntry.TABLE_NAME + " ("
                + RunningContract.RunningEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RunningContract.RunningEntry.COLUMN_RUN_DATE + " TEXT NOT NULL, "
                + RunningContract.RunningEntry.COLUMN_RUN_KILOMETRES + " INTEGER NOT NULL, "
                + RunningContract.RunningEntry.COLUMN_RUN_PACE + " INTEGER NOT NULL, "
                + RunningContract.RunningEntry.COLUMN_RUN_DURATION + " INTEGER NOT NULL, "
                + RunningContract.RunningEntry.COLUMN_RUN_FEELING + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_RUNNING_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}



