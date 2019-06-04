package no.woact.blemar17.ultimatetictactoe.model;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Objects;

import no.woact.blemar17.ultimatetictactoe.dataaccess.DataSource;

public class GameManager {

    /*
    -gameMode-
    - 1 = vs.
    - 2 = ai.
    ----------
     */

    private final static int X = 1;
    private final static int O = 2;
    private final static int AI_MODE = 1;
    private final static int VS_MODE = 2;
    private final static int GAME_RUNNING = 0;
    private final static int GAME_WON = 1;
    private final static int GAME_DRAW = 2;
    private final static int GAME_AI_TURN = 3;

    private int mGameMode;
    private Player mPlayer1;
    private Player mPlayer2;
    private Player mActivePlayer;
    private Player mWinnerPlayer;
    private int[][] mBoard;
    private int mTurn;
    private int mSymbol;
    private int mGameState;
    private int[][] mWinningLineCoord;
    private Context mContext;

    GameAI mGameAI;
    DataSource mDataSource;

    public GameManager(String playerOneName, Context context) {
        this.mGameMode = AI_MODE;
        this.mPlayer1 = new Player(playerOneName, false);
        this.mPlayer2 = new Player("TTTBot", true);
        this.mContext = context;
    }

    public GameManager(String playerOneName, String playerTwoName, Context context) {
        this.mGameMode = VS_MODE;
        this.mPlayer1 = new Player(playerOneName, false);
        this.mPlayer2 = new Player(playerTwoName, false);
        this.mContext = context;
    }


    public Player getActivePlayer() {
        return mActivePlayer;
    }

    public Player getWinnerPlayer() {
        return mWinnerPlayer;
    }

    public Player getPlayer1() {
        return mPlayer1;
    }

    public Player getPlayer2() {
        return mPlayer2;
    }

    public int[][] getWinningLineCoord() {
        return mWinningLineCoord;
    }

    public char getSymbol() {
        if ( mSymbol == O) {
            return 'O';
        } else {
            return 'X';
        }
    }

    public void init() {
        //checkExistingPlayers();
        if (mGameMode == AI_MODE) {
            mGameAI = new GameAI(O, X);
        }
        mWinnerPlayer = null;
        mBoard = new int[3][3];
        mTurn = 1;
        mActivePlayer = mPlayer1;
        mSymbol = X;
        mGameState = GAME_RUNNING;
        mWinningLineCoord = new int[3][2];
    }

    public int runTurn(int xPosTile, int yPosTile) {
        updateBoard(xPosTile, yPosTile);
        checkBoard();

        if (mGameState == GAME_WON) {
            mWinnerPlayer = mActivePlayer;
            Log.d("GameMessage", "Winner: " + mWinnerPlayer.getName());
            updateHighscore();
            return GAME_WON;
        } else if (mGameState == GAME_DRAW) {
            Log.d("GameMessage", "Game is a draw");
            return GAME_DRAW;
        } else {
            Log.d("GameMessage", "No winner");
            updateActivePlayer();
            updateSymbol();
            ++mTurn;
            if (mGameMode == AI_MODE && mActivePlayer.getAi() == true) {
                return GAME_AI_TURN;
            } else {
                return GAME_RUNNING;
            }
        }
    }

    private void updateHighscore() {
        mDataSource = new DataSource(mContext);
        List<Player> playerList = mDataSource.getHighscore();
        boolean found = false;
        if (playerList != null) {
            for (int i = 0; i < playerList.size(); i++) {
                if (Objects.equals(playerList.get(i).getName(), mWinnerPlayer.getName())) {
                    found = true;
                    playerList.get(i).incrementWins();
                    mDataSource.updateHighscore(playerList.get(i));
                }
            }
        }
        if (!found) {
            mWinnerPlayer.incrementWins();
            mDataSource.insertToHighscore(mWinnerPlayer);
        }
    }

    public int[] runAI() {
        return mGameAI.move(mBoard);
    }

    private void updateSymbol() {
        if (mSymbol == X) {
            mSymbol = O;
        } else {
            mSymbol = X;
        }
    }

    private void updateBoard(int xPosTile, int yPosTile) {
        if ((mTurn % 2) != 0) {
            mBoard[xPosTile][yPosTile] = X;
        } else if ((mTurn % 2) == 0) {
            mBoard[xPosTile][yPosTile] = O;
        }
        Log.d("GameMessage", getSymbol() + " placed at " + xPosTile + " " + yPosTile);
    }

    private void checkBoard() {
        if (mTurn > 4) {
            Log.d("GameMessage", "Checking board for: " + getSymbol());
            for (int xPos = 0; xPos < 3; xPos++) {
                for (int yPos = 0; yPos < 3; yPos++) {
                    if (mBoard[xPos][yPos] == mSymbol) {
                        //Checks the row to the right, put only if we are in column 0
                        if (yPos == 0 && mBoard[xPos][yPos+1] == mSymbol && mBoard[xPos][yPos+2] == mSymbol) {
                            setWinningLineCoord(xPos, yPos, xPos, yPos+1, xPos, yPos+2);
                            mGameState = GAME_WON;
                        }
                        //Checks the column from the top and down, put only if we are in row 0
                        if (xPos == 0 && mBoard[xPos+1][yPos] == mSymbol && mBoard[xPos+2][yPos] == mSymbol) {
                            setWinningLineCoord(xPos, yPos, xPos+1, yPos, xPos+2, yPos);
                            mGameState = GAME_WON;
                        }
                        //We wish to check sloping lines, put only if we are checking row 0 at column 0 or 2.
                        if ((xPos == 0 && yPos == 0) && (mBoard[xPos+1][yPos+1] == mSymbol && mBoard[xPos+2][yPos+2] == mSymbol)) {
                            setWinningLineCoord(xPos, yPos, xPos+1, yPos+1, xPos+2, yPos+2);
                            mGameState = GAME_WON;
                        } else if ((xPos == 0 && yPos == 2) && (mBoard[xPos+1][yPos-1] == mSymbol && mBoard[xPos+2][yPos-2] == mSymbol)) {
                            setWinningLineCoord(xPos, yPos, xPos+1, yPos-1, xPos+2, yPos-2);
                            mGameState = GAME_WON;
                        }
                    }
                }
            }
        }
        if (mTurn == 9 && mGameState != GAME_WON) {
            mGameState = GAME_DRAW;
        }
    }

    private void updateActivePlayer() {
        if (mActivePlayer == mPlayer1)
            mActivePlayer = mPlayer2;
        else
            mActivePlayer = mPlayer1;
    }

    private void setWinningLineCoord(int xPos1, int yPos1, int xPos2, int yPos2, int xPos3, int yPos3) {
        mWinningLineCoord[0][0] = xPos1;
        mWinningLineCoord[0][1] = yPos1;
        mWinningLineCoord[1][0] = xPos2;
        mWinningLineCoord[1][1] = yPos2;
        mWinningLineCoord[2][0] = xPos3;
        mWinningLineCoord[2][1] = yPos3;
    }
}
