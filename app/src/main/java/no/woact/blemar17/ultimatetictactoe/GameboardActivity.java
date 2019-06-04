package no.woact.blemar17.ultimatetictactoe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameboardInterface;
import no.woact.blemar17.ultimatetictactoe.model.GameManager;

public class GameboardActivity extends AppCompatActivity implements GameboardInterface {

    GameManager gameManager;

    private FragmentManager fragmentManager;
    private Fragment gameboardFragment;
    private Fragment playerViewFragment;
    private Fragment playerWonViewFragment;
    private Fragment playerDrawViewFragment;

    String mFinishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        fragmentManager = getSupportFragmentManager();

        initGame();
        initFragments();
        initTransactions();
    }

    private void initGame() {
        int gameMode = getIntent().getIntExtra("KeyGameMode", 0);

        String playerOneName = getIntent().getStringExtra("KeyPlayer1");
        if (gameMode == 1) {
            gameManager = new GameManager(playerOneName, GameboardActivity.this);
        } else if (gameMode == 2) {
            String playerTwoName = getIntent().getStringExtra("KeyPlayer2");
            gameManager = new GameManager(playerOneName, playerTwoName, GameboardActivity.this);
        }

        gameManager.init();
    }

    private void initTransactions() {
        if (gameboardFragment == null) {
            gameboardFragment = new BoardFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_gameboard, gameboardFragment).commit();
        }

        if (playerViewFragment == null) {
            playerViewFragment = new PlayerViewFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_playerView, playerViewFragment).commit();
        }
    }

    private void initFragments() {
        gameboardFragment  = fragmentManager.findFragmentById(R.id.fragment_container_gameboard);
        playerViewFragment = fragmentManager.findFragmentById(R.id.fragment_container_playerView);

    }

    @Override
    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public void setFinishTime(int minutes, int seconds, int milliseconds) {
        StringBuilder sb = new StringBuilder();
        if (minutes < 10) {
            sb.append("0");
        }
        sb.append(minutes);
        sb.append(":");
        if (seconds < 10) {
            sb.append("0");
        }
        sb.append(seconds);
        sb.append(":");
        if (milliseconds < 10) {
            sb.append("0");
        }
        sb.append(milliseconds);
        mFinishTime = sb.toString();
    }

    @Override
    public String getFinishTime() {
        return mFinishTime;
    }

    @Override
    public void gameOverScreen(int state) {
        fragmentManager.beginTransaction().remove(playerViewFragment).commit();
        fragmentManager.popBackStack();

        if (state == 1) {
            playerWonViewFragment = new PlayerWonViewFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_playerView, playerWonViewFragment).commit();
        } else if (state == 2) {
            playerDrawViewFragment = new PlayerDrawViewFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_playerView, playerDrawViewFragment).commit();
        }
    }

    @Override
    public void endActivity() {
        finish();
    }

    @Override
    public void restartActivity() {
        finish();
        startActivity(getIntent());
    }


}
