package no.woact.blemar17.ultimatetictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameSetupInterface;

public class GameModeFragment extends Fragment {

    GameSetupInterface mCallBack;

    public static final int AIMODE = 1;
    public static final int VSMODE = 2;

    private Button btnVs;
    private Button btnAi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_mode, container, false);

        initWidgets(view);
        initListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (GameSetupInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    private void initListeners() {
        btnVs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.setGameMode(VSMODE);
            }
        });

        btnAi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.setGameMode(AIMODE);
            }
        });
    }

    private void initWidgets(View view) {
        btnVs = view.findViewById(R.id.btnVs);
        btnAi = view.findViewById(R.id.btnAi);
    }
}
