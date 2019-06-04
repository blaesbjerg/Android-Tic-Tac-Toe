package no.woact.blemar17.ultimatetictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import no.woact.blemar17.ultimatetictactoe.dataaccess.DataSource;
import no.woact.blemar17.ultimatetictactoe.model.Player;

public class HighscoreActivity extends AppCompatActivity {

    private TextView mTxtvName1;
    private TextView mTxtvName2;
    private TextView mTxtvName3;
    private TextView mTxtvName4;
    private TextView mTxtvName5;
    private TextView mTxtvName6;
    private TextView mTxtvName7;
    private TextView mTxtvName8;
    private TextView mTxtvName9;
    private TextView mTxtvName10;
    private TextView mTxtvWins1;
    private TextView mTxtvWins2;
    private TextView mTxtvWins3;
    private TextView mTxtvWins4;
    private TextView mTxtvWins5;
    private TextView mTxtvWins6;
    private TextView mTxtvWins7;
    private TextView mTxtvWins8;
    private TextView mTxtvWins9;
    private TextView mTxtvWins10;
    private TextView[] mTxtvNameArray;
    private TextView[] mTxtvWinsArray;

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mDataSource = new DataSource(HighscoreActivity.this);

        initWidgets();
        initArrays();
        populateWidgets();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }

    private void populateWidgets() {
        List<Player> playerListFromDB = mDataSource.getHighscore();
        if (playerListFromDB != null) {
            int max = 10;
            if (playerListFromDB.size() < 10) {
                max = playerListFromDB.size();
            }
            for(int i = 0; i < max; i++) {
                mTxtvNameArray[i].setText(playerListFromDB.get(i).getName());
                mTxtvWinsArray[i].setText(String.valueOf(playerListFromDB.get(i).getWins()));
            }
        }
        mDataSource.close();
    }

    private void initWidgets() {
        mTxtvName1 = findViewById(R.id.txtvName1);
        mTxtvName2 = findViewById(R.id.txtvName2);
        mTxtvName3 = findViewById(R.id.txtvName3);
        mTxtvName4 = findViewById(R.id.txtvName4);
        mTxtvName5 = findViewById(R.id.txtvName5);
        mTxtvName6 = findViewById(R.id.txtvName6);
        mTxtvName7 = findViewById(R.id.txtvName7);
        mTxtvName8 = findViewById(R.id.txtvName8);
        mTxtvName9 = findViewById(R.id.txtvName9);
        mTxtvName10 = findViewById(R.id.txtvName10);
        mTxtvWins1 = findViewById(R.id.txtvWins1);
        mTxtvWins2 = findViewById(R.id.txtvWins2);
        mTxtvWins3 = findViewById(R.id.txtvWins3);
        mTxtvWins4 = findViewById(R.id.txtvWins4);
        mTxtvWins5 = findViewById(R.id.txtvWins5);
        mTxtvWins6 = findViewById(R.id.txtvWins6);
        mTxtvWins7 = findViewById(R.id.txtvWins7);
        mTxtvWins8 = findViewById(R.id.txtvWins8);
        mTxtvWins9 = findViewById(R.id.txtvWins9);
        mTxtvWins10 = findViewById(R.id.txtvWins10);
    }

    private void initArrays() {
        mTxtvNameArray = new TextView[] {
                mTxtvName1,
                mTxtvName2,
                mTxtvName3,
                mTxtvName4,
                mTxtvName5,
                mTxtvName6,
                mTxtvName7,
                mTxtvName8,
                mTxtvName9,
                mTxtvName10 };

        mTxtvWinsArray = new TextView[] {
                mTxtvWins1,
                mTxtvWins2,
                mTxtvWins3,
                mTxtvWins4,
                mTxtvWins5,
                mTxtvWins6,
                mTxtvWins7,
                mTxtvWins8,
                mTxtvWins9,
                mTxtvWins10 };
    }

}
