package almik.dotrain.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import almik.dotrain.R;
import almik.dotrain.Utill.StartDialog;

import static almik.dotrain.R.id.containerdown;


public class StartFrament extends Fragment {
   private static StartFrament instanse;
    private StartDialog startDialog;
    public static StartFrament getInstance() {
        if (instanse==null){instanse = new StartFrament();}
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




//        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
//        adb.setTitle("Custom dialog");
//        // создаем view из dialog.xml
//       LinearLayout  viewD = (LinearLayout) getActivity().getLayoutInflater()
//                .inflate(R.layout.dialog_choise, null);
//        // устанавливаем ее, как содержимое тела диалога
//        adb.setView(viewD);
//        adb.create();
//        adb.show();





        return view;
    }


}
