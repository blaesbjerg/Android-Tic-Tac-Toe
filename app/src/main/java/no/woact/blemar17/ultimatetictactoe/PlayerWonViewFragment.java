package no.woact.blemar17.ultimatetictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameboardInterface;


public class PlayerWonViewFragment extends Fragment {

    GameboardInterface mCallBack;
    private ImageView imgvPlayerWon;
    private Button btnMainMenu;
    private Button btnReplay;
    private TextView txtvPlayerWon;
    private TextView txtviewEndTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_won_view, container, false);

        initWidgets(view);
        initListeners();
        setWidgets();
        setWinner();

        return view;
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

    private void setWinner() {
        String winner = mCallBack.getGameManager().getWinnerPlayer().getName();
        if (winner != null) {
            if (mCallBack.getGameManager().getSymbol() == 'X') {
                imgvPlayerWon.setImageResource(R.drawable.x_tile_small);
            } else if (mCallBack.getGameManager().getSymbol() == 'O') {
                imgvPlayerWon.setImageResource(R.drawable.o_tile_small);
            }
            txtvPlayerWon.setText(winner);
        } else {
            txtvPlayerWon.setText("No winner");
        }
    }

    private void setWidgets() {
        txtviewEndTime.setText(mCallBack.getFinishTime());
    }

    private void initListeners() {
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.endActivity();
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.restartActivity();
            }
        });
    }

    private void initWidgets(View view) {
        imgvPlayerWon = view.findViewById(R.id.imgvPlayerWon);
        btnMainMenu = view.findViewById(R.id.btnMainMenu);
        btnReplay = view.findViewById(R.id.btnReplay);
        txtvPlayerWon = view.findViewById(R.id.txtvPlayerWon);
        txtviewEndTime = view.findViewById(R.id.txtviewEndTime);
    }
}
