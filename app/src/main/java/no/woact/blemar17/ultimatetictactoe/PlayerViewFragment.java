package no.woact.blemar17.ultimatetictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import no.woact.blemar17.ultimatetictactoe.interfaces.GameboardInterface;
import no.woact.blemar17.ultimatetictactoe.model.GameTimer;


public class PlayerViewFragment extends Fragment {

    GameboardInterface mCallBack;
    Handler handler;
    GameTimer gameTimer = new GameTimer();

    private TextView txtvPlayer1;
    private TextView txtvPlayer2;
    private TextView txtvTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_view, container, false);

        handler = new Handler();

        initWidget(view);
        setPlayerNames();
        gameTimer.initTimer();
        handler.postDelayed(runnable, 50);
        return view;
    }


    @Override
    public void onStop() {
        mCallBack.setFinishTime(gameTimer.getMinutes(), gameTimer.getSeconds(), gameTimer.getMilliseconds());
        super.onStop();
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

    private void setPlayerNames() {
        txtvPlayer1.setText(mCallBack.getGameManager().getPlayer1().getName());
        txtvPlayer2.setText(mCallBack.getGameManager().getPlayer2().getName());
    }

    private void initWidget(View view) {
        txtvPlayer1 = view.findViewById(R.id.txtvPlayer1);
        txtvPlayer2 = view.findViewById(R.id.txtvPlayer2);
        txtvTimer = view.findViewById(R.id.txtvTimer);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gameTimer.run();
            txtvTimer.setText("" +  String.format("%02d", gameTimer.getMinutes()) + ":" + String.format("%02d", gameTimer.getSeconds()) + ":" + gameTimer.getMilliseconds());
            handler.postDelayed(this, 50);
        }
    };
}
