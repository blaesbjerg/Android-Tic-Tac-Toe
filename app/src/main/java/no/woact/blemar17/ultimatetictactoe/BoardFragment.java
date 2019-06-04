package no.woact.blemar17.ultimatetictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameboardInterface;

public class BoardFragment extends Fragment {

    GameboardInterface mCallBack;

    private ImageButton btn00;
    private ImageButton btn01;
    private ImageButton btn02;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn20;
    private ImageButton btn21;
    private ImageButton btn22;

    private ImageButton[][] btnArray;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        initWidgets(view);
        initListeners();
        initArray();

        return view;
    }

    private void initArray() {
        btnArray = new ImageButton[3][3];
        btnArray[0][0] = btn00;
        btnArray[0][1] = btn01;
        btnArray[0][2] = btn02;
        btnArray[1][0] = btn10;
        btnArray[1][1] = btn11;
        btnArray[1][2] = btn12;
        btnArray[2][0] = btn20;
        btnArray[2][1] = btn21;
        btnArray[2][2] = btn22;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (GameboardInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    private void initListeners() {
        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn00);
                btn00.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(0,0));
            }
        });

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn01);
                btn01.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(0,1));
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn02);
                btn02.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(0, 2));
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn10);
                btn10.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(1,0));
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn11);
                btn11.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(1,1));
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn12);
                btn12.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(1,2));
            }
        });

        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn20);
                btn20.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(2,0));
            }
        });

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn21);
                btn21.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(2,1));
            }
        });

        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSymbol(btn22);
                btn22.setClickable(false);
                checkGameState(mCallBack.getGameManager().runTurn(2,2));
            }
        });
    }

    private void initWidgets(View view) {
        btn00 = (ImageButton)view.findViewById(R.id.btn00);
        btn01 = (ImageButton)view.findViewById(R.id.btn10);
        btn02 = (ImageButton)view.findViewById(R.id.btn20);
        btn10 = (ImageButton)view.findViewById(R.id.btn011);
        btn11 = (ImageButton)view.findViewById(R.id.btn11);
        btn12 = (ImageButton)view.findViewById(R.id.btn21);
        btn20 = (ImageButton)view.findViewById(R.id.btn02);
        btn21 = (ImageButton)view.findViewById(R.id.btn12);
        btn22 = (ImageButton)view.findViewById(R.id.btn22);
    }

    private void setSymbol(ImageButton btn){
        if (mCallBack.getGameManager().getSymbol() == 'X') {
            btn.setImageResource(R.drawable.x_tile);
        } else if (mCallBack.getGameManager().getSymbol() == 'O') {
            btn.setImageResource(R.drawable.o_tile);
        }
    }

    private void checkGameState(int gameState) {
        if (gameState == 1) {
            disableGameboard();
            setWinnerLiner();
            mCallBack.gameOverScreen(1);
        } else if (gameState == 2) {
            disableGameboard();
            mCallBack.gameOverScreen(2);
        } else if (gameState == 3) {
            int[] aiMove = mCallBack.getGameManager().runAI();
            setSymbol(btnArray[aiMove[0]][aiMove[1]]);
            btnArray[aiMove[0]][aiMove[1]].setClickable(false);
            checkGameState(mCallBack.getGameManager().runTurn(aiMove[0],aiMove[1]));
        }
    }

    private void disableGameboard() {
        for (int xPos = 0; xPos < 3; xPos++) {
            for (int yPos = 0; yPos < 3; yPos++) {
                btnArray[xPos][yPos].setClickable(false);
            }
        }
    }

    private void setWinnerLiner() {
        int imageRes = 0;
        if (mCallBack.getGameManager().getSymbol() == 'X') {
            imageRes = R.drawable.x_win_tile;
        } else if (mCallBack.getGameManager().getSymbol() == 'O') {
            imageRes = R.drawable.o_win_tile;
        }
        int[][] winnerCoord = mCallBack.getGameManager().getWinningLineCoord();
        btnArray[winnerCoord[0][0]][winnerCoord[0][1]].setImageResource(imageRes);
        btnArray[winnerCoord[1][0]][winnerCoord[1][1]].setImageResource(imageRes);
        btnArray[winnerCoord[2][0]][winnerCoord[2][1]].setImageResource(imageRes);
    }
}
