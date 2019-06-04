package no.woact.blemar17.ultimatetictactoe.dataaccess;

import android.provider.BaseColumns;

public class HighscoreDBSchema implements BaseColumns {
    public static final String TABLE_NAME = "highscore";
    public static final String COLUMN_NAME = "playerName";
    public static final String COLUMN_WINS = "wins";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_WINS + " INTEGER " + ");";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}

