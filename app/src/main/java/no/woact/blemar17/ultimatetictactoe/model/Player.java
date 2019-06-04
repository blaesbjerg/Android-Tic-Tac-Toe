package no.woact.blemar17.ultimatetictactoe.model;

public class Player {

    private boolean mAi;
    private String mName;
    private int mWins;

    public Player(String name, int wins) {
        this.mName = name;
        this.mWins = wins;
    }

    public Player(String name, boolean ai) {
        this.mName = name;
        this.mWins = 0;
        this.mAi = ai;
    }

    public Player(String name, int wins,  boolean ai) {
        this.mName = name;
        this.mWins = wins;
        this.mAi = ai;
    }

    public boolean getAi() {
        return mAi;
    }

    public String getName() {
        return mName;
    }

    public int getWins() {
        return mWins;
    }

    public void incrementWins() {
        ++mWins;
    }
}
