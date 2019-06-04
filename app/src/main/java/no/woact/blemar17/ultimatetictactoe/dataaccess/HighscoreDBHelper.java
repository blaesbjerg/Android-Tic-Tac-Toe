package no.woact.blemar17.ultimatetictactoe.dataaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighscoreDBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "Highscore.db";
    public static final int DB_VERSION = 2;

    public HighscoreDBHelper(Context context) {
        super(context, DB_FILE_NAME, null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HighscoreDBSchema.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Upgrading the database will delete all data stored.
        sqLiteDatabase.execSQL(HighscoreDBSchema.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}
