package no.woact.blemar17.ultimatetictactoe;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameSetupInterface;


public class GameSetupActivity extends AppCompatActivity implements GameSetupInterface {

    private FragmentManager fragmentManager;
    private Fragment gameModeFragment;
    private Fragment createPlayerFragment1;
    private Fragment createPlayerFragment2;
    private Button btnPlayGame;

    private int mGameMode = 0;
    private String player1Name;
    private String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        fragmentManager = getSupportFragmentManager();

        initWidget();
        initFragments();
        initTransactions();
        initListener();

        btnPlayGameActivation();
    }

    private void initListener() {
        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MYTEST", player1Name);
                Intent intent = new Intent(GameSetupActivity.this, GameboardActivity.class);
                intent.putExtra("KeyGameMode", mGameMode);
                intent.putExtra("KeyPlayer1", player1Name);
                if (mGameMode == 2) {
                    intent.putExtra("KeyPlayer2", player2Name);
                    Log.d("MYTEST", player2Name);
                }
                finish();
                startActivity(intent);
            }
        });
    }

    private void initWidget() {
        btnPlayGame = (Button)findViewById(R.id.btnPlayGame);
    }

    private void initTransactions() {
        if (gameModeFragment == null) {
            gameModeFragment = new GameModeFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_gameMode, gameModeFragment).commit();
        }
    }

    private void initFragments() {
        gameModeFragment = fragmentManager.findFragmentById(R.id.fragment_container_gameMode);
    }

    private void btnPlayGameActivation() {
        if (mGameMode == 0) {
            btnPlayGame.setClickable(false);
        } else {
            btnPlayGame.setClickable(true);
        }
    }

    private void initAIMode() {
        createPlayerFragment1 = createPlayerFragment(R.id.fragment_container_createPlayer1, createPlayerFragment1, "Player1");

        if (createPlayerFragment2 != null) {
            fragmentManager.beginTransaction().remove(createPlayerFragment2).commit();
            createPlayerFragment2 = null;
        }
        btnPlayGameActivation();
    }

    private void initVSMode() {
        createPlayerFragment1 = createPlayerFragment(R.id.fragment_container_createPlayer1, createPlayerFragment1, "Player1");
        createPlayerFragment2 = createPlayerFragment(R.id.fragment_container_createPlayer2, createPlayerFragment2, "Player2");
        btnPlayGameActivation();
    }

    private Fragment createPlayerFragment(int fragmentContainerId, Fragment createPlayerFragment, String name) {
        if (createPlayerFragment == null) {
            createPlayerFragment = new CreatePlayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("StringKey", name);
            createPlayerFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(fragmentContainerId, createPlayerFragment).commit();
        }
        return createPlayerFragment;
    }

    @Override
    public void setString(String test, CreatePlayerFragment createPlayerFragment) {
        if (createPlayerFragment == createPlayerFragment1) {
            player1Name = test;
        } else if (createPlayerFragment == createPlayerFragment2) {
            player2Name = test;
        }
    }

    @Override
    public void setGameMode(int gameMode) {
        mGameMode = gameMode;
        if (mGameMode == 1) {
            initAIMode();
        } else if (mGameMode == 2) {
            initVSMode();
        }
    }
}
