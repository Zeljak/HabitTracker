package com.example.mara.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Mara on 9.7.2017..
 */

public class RunningContract {
    /// To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private RunningContract() {
    }

    /**
     * Inner class that defines constant values for the running diary database table.
     * Each entry in the table represents a single run.
     */
    public static final class RunningEntry implements BaseColumns {

        /**
         * Name of database table for the running diary
         */
        public final static String TABLE_NAME = "running";

        /**
         * Unique ID number for each run (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Date of the run.
         * <p>
         * Type: TEXT in format YYYY-MM-DD
         */
        public final static String COLUMN_RUN_DATE = "date";

        /**
         * How many kilometers did I run.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_RUN_KILOMETRES = "kilometres";

        /**
         * Avg. Pace of the rune
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_RUN_PACE = "pace";

        /**
         * Duration of the run.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_RUN_DURATION = "duration";

        /**
         * How did I feel during the run (description.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_RUN_FEELING = "feeling";


    }
}
