package com.example.mara.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mara.habittracker.data.RunningContract;
import com.example.mara.habittracker.data.RunningDbHelper;

/**
 * Displays list of runs that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private RunningDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new RunningDbHelper(this);

        insertRun();

        String[] projection = {
                RunningContract.RunningEntry._ID,
                RunningContract.RunningEntry.COLUMN_RUN_DATE,
                RunningContract.RunningEntry.COLUMN_RUN_KILOMETRES,
                RunningContract.RunningEntry.COLUMN_RUN_PACE,
                RunningContract.RunningEntry.COLUMN_RUN_DURATION,
                RunningContract.RunningEntry.COLUMN_RUN_FEELING,
        };

        Cursor cursor = readRuns(projection);
        logCursorData(cursor);
        cursor.close();
    }

    /**
     * Helper method to insert hardcoded running data into the database.
     */
    private void insertRun() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and first run attributes are the values
        ContentValues contentValues = new ContentValues();
        contentValues.put(RunningContract.RunningEntry.COLUMN_RUN_DATE, "2017-07-09");
        contentValues.put(RunningContract.RunningEntry.COLUMN_RUN_KILOMETRES, "8km");
        contentValues.put(RunningContract.RunningEntry.COLUMN_RUN_PACE, "6:36");
        contentValues.put(RunningContract.RunningEntry.COLUMN_RUN_DURATION, "52:48");
        contentValues.put(RunningContract.RunningEntry.COLUMN_RUN_FEELING, "Great");

        long newRowId = db.insert(RunningContract.RunningEntry.TABLE_NAME, null, contentValues);
        Log.v("MainActivity", "New row ID is " + newRowId);
    }

    //Read data from table and return cursor
    private Cursor readRuns(String[] projection) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db.query(RunningContract.RunningEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    //Write cursor data in log message
    private void logCursorData(Cursor cursor) {
        String dataInCursorString = RunningContract.RunningEntry._ID + " - " +
                RunningContract.RunningEntry.COLUMN_RUN_DATE + " - " +
                RunningContract.RunningEntry.COLUMN_RUN_KILOMETRES + " - " +
                RunningContract.RunningEntry.COLUMN_RUN_PACE + " - " +
                RunningContract.RunningEntry.COLUMN_RUN_DURATION + " - " +
                RunningContract.RunningEntry.COLUMN_RUN_FEELING + "\n";
        //Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry._ID);
        int dateColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry.COLUMN_RUN_DATE);
        int kilometersColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry.COLUMN_RUN_KILOMETRES);
        int paceColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry.COLUMN_RUN_PACE);
        int durationColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry.COLUMN_RUN_DURATION);
        int feelingColumnIndex = cursor.getColumnIndex(RunningContract.RunningEntry.COLUMN_RUN_FEELING);
        //Read all data in cursor
        while (cursor.moveToNext()) {

            int currentID = cursor.getInt(idColumnIndex);
            String currentDate = cursor.getString(dateColumnIndex);
            int currentKilometers = cursor.getInt(kilometersColumnIndex);
            int currentPace = cursor.getInt(paceColumnIndex);
            int currentDuration = cursor.getInt(durationColumnIndex);
            String currentFeeling = cursor.getString(feelingColumnIndex);
            //add data from row in table to string
            dataInCursorString += "\n" + currentID + " - " +
                    currentDate + " - " +
                    currentKilometers + " - " +
                    currentPace + " - " +
                    currentDuration + " - " +
                    currentFeeling;
        }
        Log.v("MainActivity", "Cursor data is: " + dataInCursorString);


    }


}


