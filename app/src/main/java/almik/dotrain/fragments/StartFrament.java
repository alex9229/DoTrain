package almik.dotrain.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import almik.dotrain.R;
import almik.dotrain.Utill.StartDialog;


public class StartFrament extends Fragment {
    private static StartFrament instanse;
    private StartDialog startDialog;

    public static StartFrament getInstance() {
        if (instanse == null) {
            instanse = new StartFrament();
        }
        return instanse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        startDialog = new StartDialog(getActivity());
        startDialog.show();


        return view;
    }


}
