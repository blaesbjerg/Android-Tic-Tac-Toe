package no.woact.blemar17.ultimatetictactoe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameboardInterface;

public class PlayerDrawViewFragment extends Fragment {

    GameboardInterface mCallBack;
    private Button btnMainMenu;
    private Button btnReplay;
    private TextView txtviewEndTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_draw_view, container, false);

        initWidgets(view);
        initListeners();
        setWidgets();

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
        btnMainMenu = view.findViewById(R.id.btnMainMenu);
        btnReplay = view.findViewById(R.id.btnReplay);
        txtviewEndTime = view.findViewById(R.id.txtviewEndTime);
    }


}
