package no.woact.blemar17.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import no.woact.blemar17.ultimatetictactoe.dataaccess.DataSource;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnNewGame;
    private Button btnHighscore;

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initWidgets();
        initListeners();
    }

    private void initListeners() {
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, GameSetupActivity.class);
                startActivity(intent);
            }
        });

        btnHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets() {
        btnNewGame = findViewById(R.id.btnNewGame);
        btnHighscore = findViewById(R.id.btnHighScore);
    }

}
