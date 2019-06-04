package no.woact.blemar17.ultimatetictactoe.interfaces;

import no.woact.blemar17.ultimatetictactoe.model.GameManager;

public interface GameboardInterface {
    GameManager getGameManager();
    void setFinishTime(int hour, int minute, int secound);
    String getFinishTime();
    void gameOverScreen(int state);
    void endActivity();
    void restartActivity();
}

