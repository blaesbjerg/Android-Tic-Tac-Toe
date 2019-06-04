package no.woact.blemar17.ultimatetictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import no.woact.blemar17.ultimatetictactoe.interfaces.GameSetupInterface;

public class CreatePlayerFragment extends Fragment {

    GameSetupInterface mCallBack;

    CreatePlayerFragment tester = this; //NOT NICE?!

    private EditText edtPlayername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_player, container, false);
        initWidget(view);
        initListener();
        setPlayer();
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

    private void setPlayer() {
        edtPlayername.setText(getArguments().getString("StringKey"));
    }

    private void initListener() {
        edtPlayername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mCallBack.setString(edtPlayername.getText().toString(), tester); //NOT NICE eller....!!!
            }
        });
    }

    private void initWidget(View view) {
        edtPlayername = view.findViewById(R.id.edtPlayername);
    }

    public String getName() {
        return edtPlayername.getText().toString();
    }
}
