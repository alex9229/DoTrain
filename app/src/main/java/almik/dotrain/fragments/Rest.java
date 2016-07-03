package almik.dotrain.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import almik.dotrain.General;
import almik.dotrain.R;
import almik.dotrain.Utill.TimeUtill;

import static almik.dotrain.R.id.containerdown;


public class Rest extends Fragment {
    private TextView tVRest;
    private static Rest instanse;
    private SharedPreferences sPref;
    private Dialog dialog;
    public Rest() {
        // Required empty public constructor
    }


    public static Rest getInstance() {
        if(instanse==null){instanse = new Rest();}

        return instanse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rest, container, false);
        tVRest=(TextView)view.findViewById(R.id.tVTimer);
        sPref = getActivity().getSharedPreferences(getString(R.string.idShared), 1);
        new TimeUtill().ModeTime(tVRest, Integer.parseInt(sPref.getString(getString(R.string.TimeForRest), "")));
        createDialog();
        return view;
    }

    
    //TODO убрать этот костыльвызова конца отдыха из класса фрагмента(возможно перенести в сервис)
    private FragmentTransaction fragTrans ;
    public void EndRest(){
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(getString(R.string.Count), Integer.toString(Integer.parseInt(sPref.getString(getString(R.string.Count), "")) - 1));
        ed.commit();
        System.out.println(Integer.parseInt(sPref.getString(getString(R.string.Count), "")));
        if(Integer.parseInt(sPref.getString(getString(R.string.Count), ""))!=0){
        fragTrans = getFragmentManager().beginTransaction();    ///set the fragment
        fragTrans.replace(containerdown, Exercise.getInstance());
        fragTrans.commit();
        }

    }


    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.Information);
        builder.setMessage(R.string.TextInRest);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Отпускает диалоговое окно
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
