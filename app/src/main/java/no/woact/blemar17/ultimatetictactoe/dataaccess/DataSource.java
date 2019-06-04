package no.woact.blemar17.ultimatetictactoe.dataaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import no.woact.blemar17.ultimatetictactoe.model.Player;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        mContext = context;
        mDbHelper = new HighscoreDBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void insertToHighscore(Player player) {
        mDatabase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HighscoreDBSchema.COLUMN_NAME, player.getName());
        values.put(HighscoreDBSchema.COLUMN_WINS, player.getWins());
        mDatabase.insert(HighscoreDBSchema.TABLE_NAME, null, values);
    }

    public void updateHighscore(Player player) {
        mDatabase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HighscoreDBSchema.COLUMN_NAME, player.getName());
        values.put(HighscoreDBSchema.COLUMN_WINS, player.getWins());

        String selection = HighscoreDBSchema.COLUMN_NAME + " = ?";
        String[] selectionArg = { player.getName() };

        mDatabase.update(
                HighscoreDBSchema.TABLE_NAME,
                values,
                selection,
                selectionArg);
    }

    public Player getPlayerFromHighscore(Player player) {
        boolean check = checkIfTableEmpty();
        if (check) {
            mDatabase = mDbHelper.getReadableDatabase();

            String query = "SELECT * FROM " + HighscoreDBSchema.TABLE_NAME + " WHERE " + HighscoreDBSchema.COLUMN_NAME + " LIKE " + player.getName();

            Cursor cursor = mDatabase.rawQuery(query, null);

            if (cursor.getColumnCount() > -1) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreDBSchema.COLUMN_NAME));
                int wins = cursor.getInt(cursor.getColumnIndexOrThrow(HighscoreDBSchema.COLUMN_WINS));
                boolean aiBool;
                if (name == "TTTBot") {
                    aiBool = true;
                } else {
                    aiBool = false;
                }
                return new Player(name, wins, aiBool);
            }
            cursor.close();
        }
        return null;
    }

    public List<Player> getHighscore() {
        boolean check = checkIfTableEmpty();
        if (check) {
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            String[] projection = {
                    BaseColumns._ID,
                    HighscoreDBSchema.COLUMN_NAME,
                    HighscoreDBSchema.COLUMN_WINS
            };

            String orderBy = HighscoreDBSchema.COLUMN_WINS + " DESC";

            Cursor cursor = mDatabase.query(
                    HighscoreDBSchema.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    orderBy);

            List<Player> highscoreList = new ArrayList<>();
            while(cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreDBSchema.COLUMN_NAME));
                int wins = cursor.getInt(cursor.getColumnIndexOrThrow(HighscoreDBSchema.COLUMN_WINS));
                highscoreList.add(new Player(name, wins));
            }
            cursor.close();

            return highscoreList;
        }
        return null;
    }

    public boolean checkIfTableEmpty() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String count = "SELECT count(*) FROM " + HighscoreDBSchema.TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0)
            return true;
        else
            return false;
    }
}
