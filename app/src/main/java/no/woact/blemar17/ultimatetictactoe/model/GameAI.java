package no.woact.blemar17.ultimatetictactoe.model;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class GameAI {

    private final static int NOT_TAKEN = 0;
    private final static int TILE_TAKEN = -100;

    Random rand = new Random();

    private int mbrickAI;
    private int mbrickPlayer;
    private int[][] mMoveBoard = new int[][] {
            { 7, 5, 7 },
            { 5, 9, 5 },
            { 7, 5, 7 }
    };

    public GameAI(int brickAI, int brickPlayer) {
        this.mbrickAI = brickAI;
        this.mbrickPlayer = brickPlayer;
    }

    public int[] move(int[][] gameBoard) {
        readGameBoard(gameBoard);
        int[] nextMove = calcNextMove();
        gameBoard[nextMove[0]][nextMove[1]] = mbrickAI;
        readGameBoard(gameBoard);
        return nextMove;
    }

    private int[] calcNextMove() {
        int nextMove[] = new int[2];
        int topMove = 0;
        for (int xPos = 0; xPos < 3; xPos++) {
            for (int yPos = 0; yPos < 3; yPos++) {
                if (mMoveBoard[xPos][yPos] > topMove) {
                    topMove = mMoveBoard[xPos][yPos];
                    nextMove[0] = xPos;
                    nextMove[1] = yPos;
                } else if ( mMoveBoard[xPos][yPos] == topMove) {
                    int result = rand.nextInt(2) + 1;
                    if ((result % 2) == 0) {
                        nextMove[0] = xPos;
                        nextMove[1] = yPos;
                    }
                }
            }
        }
        return nextMove;
    }

    private void readGameBoard(int[][] gameBoard) {
        for (int xPos = 0; xPos < 3; xPos++) {
            for (int yPos = 0; yPos < 3; yPos++) {
                if (gameBoard[xPos][yPos] == mbrickPlayer || gameBoard[xPos][yPos] == mbrickAI) {
                    if (mMoveBoard[xPos][yPos] > -50) {
                        calcMoveBoard(gameBoard, xPos, yPos);
                    }
                    mMoveBoard[xPos][yPos] = TILE_TAKEN;
                }
            }
        }
        Log.d("MoveArray", "Moveboard: " + Arrays.deepToString(mMoveBoard));
    }

    private void calcMoveBoard(int[][] gameBoard, int xPos, int yPos) {
        if (xPos == 0 && yPos == 0) {
            checkXTop(gameBoard, xPos, yPos);
            checkYRight(gameBoard, xPos, yPos);
            checkUpRCorner(gameBoard, xPos, yPos);
        } else if (xPos == 0 && yPos == 1) {
            checkXTop(gameBoard, xPos, yPos);
            checkYMid(gameBoard, xPos, yPos);
        } else if (xPos == 0 && yPos == 2) {
            checkXTop(gameBoard, xPos, yPos);
            checkYLeft(gameBoard, xPos, yPos);
            checkUpLCorner(gameBoard, xPos, yPos);
        } else if (xPos == 1 && yPos == 0) {
            checkXMid(gameBoard, xPos, yPos);
            checkYRight(gameBoard, xPos, yPos);
        } else if (xPos == 1 && yPos == 1) {
            checkXMid(gameBoard, xPos, yPos);
            checkYMid(gameBoard, xPos, yPos);
            checkCenter(gameBoard, xPos, yPos);
        } else if (xPos == 1 && yPos == 2) {
            checkXMid(gameBoard, xPos, yPos);
            checkYLeft(gameBoard, xPos, yPos);
        } else if (xPos == 2 && yPos == 0) {
            checkXBot(gameBoard, xPos, yPos);
            checkYRight(gameBoard, xPos, yPos);
            checkLowRCorner(gameBoard, xPos, yPos);
        } else if (xPos == 2 && yPos == 1) {
            checkXBot(gameBoard, xPos, yPos);
            checkYMid(gameBoard, xPos, yPos);
        } else if (xPos == 2 && yPos == 2) {
            checkXBot(gameBoard, xPos, yPos);
            checkYLeft(gameBoard, xPos, yPos);
            checkLowLCorner(gameBoard, xPos, yPos);
        }
    }

    private void checkXTop(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos+1][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos+1][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos], xPos+2, yPos);
        }
        if (gameBoard[xPos+2][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos+2][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+2][yPos], xPos+1, yPos);
        }
    }

    private void checkXMid(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos-1][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos-1][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos], xPos+1, yPos);
        }
        if (gameBoard[xPos+1][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos+1][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos], xPos-1, yPos);
        }
    }

    private void checkXBot(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos-1][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos-1][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos], xPos-2, yPos);
        }
        if (gameBoard[xPos-2][yPos] == NOT_TAKEN) {
            mMoveBoard[xPos-2][yPos] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-2][yPos], xPos-1, yPos);
        }
    }

    private void checkYRight(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos][yPos+1] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos+1], xPos, yPos+2);
        }
        if (gameBoard[xPos][yPos+2] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos+2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos+2], xPos, yPos+1);
        }
    }

    private void checkYMid(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos][yPos-1] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos-1], xPos, yPos+1);
        }
        if (gameBoard[xPos][yPos+1] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos+1], xPos, yPos-1);
        }
    }

    private void checkYLeft(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos][yPos-1] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos-1], xPos, yPos-2);
        }
        if (gameBoard[xPos][yPos-2] == NOT_TAKEN) {
            mMoveBoard[xPos][yPos-2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos][yPos-2], xPos, yPos-1);
        }
    }

    private void checkUpRCorner(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos+1][yPos+1] != mbrickPlayer ||gameBoard[xPos+1][yPos+1] != mbrickAI) {
            mMoveBoard[xPos+1][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos+1], xPos+2, yPos+2);
        }
        if (gameBoard[xPos+2][yPos+2] == NOT_TAKEN) {
            mMoveBoard[xPos+2][yPos+2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+2][yPos+2], xPos+1, yPos+1);
        }
    }

    private void checkUpLCorner(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos+1][yPos-1] == NOT_TAKEN) {
            mMoveBoard[xPos+1][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos-1], xPos+2, yPos-2);
        }
        if (gameBoard[xPos+2][yPos-2] == NOT_TAKEN) {
            mMoveBoard[xPos+2][yPos-2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+2][yPos-2], xPos+1, yPos-1);
        }
    }

    private void checkCenter(int[][] gameBoard, int xPos, int yPos) {
        //Upper right to lower left
        if (gameBoard[xPos-1][yPos-1] == 0) {
            mMoveBoard[xPos-1][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos-1], xPos+1, yPos+1);
        }
        if (gameBoard[xPos+1][yPos+1] == NOT_TAKEN) {
            mMoveBoard[xPos+1][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos+1], xPos-1, yPos-1);
        }

        //Upper left to lower right
        if (gameBoard[xPos-1][yPos+1] == NOT_TAKEN) {
            mMoveBoard[xPos-1][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos+1], xPos+1, yPos-1);
        }
        if (gameBoard[xPos+1][yPos-1] == NOT_TAKEN) {
            mMoveBoard[xPos+1][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos+1][yPos-1], xPos-1, yPos+1);
        }
    }

    private void checkLowRCorner(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos-1][yPos+1] == NOT_TAKEN) {
            mMoveBoard[xPos-1][yPos+1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos+1], xPos-2, yPos+2);
        }
        if (gameBoard[xPos-2][yPos+2] == NOT_TAKEN) {
            mMoveBoard[xPos-2][yPos+2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-2][yPos+2], xPos-1, yPos+1);
        }
    }

    private void checkLowLCorner(int[][] gameBoard, int xPos, int yPos) {
        if (gameBoard[xPos-1][yPos-1] == NOT_TAKEN) {
            mMoveBoard[xPos-1][yPos-1] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-1][yPos-1], xPos-2, yPos-2);
        }
        if (gameBoard[xPos-2][yPos-2] == NOT_TAKEN) {
            mMoveBoard[xPos-2][yPos-2] += 1;
        } else {
            gameBrickCheck(gameBoard[xPos][yPos], gameBoard[xPos-2][yPos-2], xPos-1, yPos-1);
        }
    }

    private void gameBrickCheck(int tile1, int tile2, int xPos, int yPos) {
        if (tile1 == tile2) {
            mMoveBoard[xPos][yPos] += 15;
        } else {
            mMoveBoard[xPos][yPos] -= 5;
        }
    }
}
