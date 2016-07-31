package almik.dotrain.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import almik.dotrain.R;
import almik.dotrain.Utill.TimeUtill;

import static almik.dotrain.R.id.containerdown;


public class Rest extends Fragment {
    private TextView tVRest; //Place for text about rest
    private static Rest instanse;

    private SharedPreferences sPref; //place for Time of rest and Count


    public static Rest getInstance() {
        if (instanse == null) {
            instanse = new Rest();
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
        View view = inflater.inflate(R.layout.fragment_rest, container, false);
        tVRest = (TextView) view.findViewById(R.id.tVTimer);
        sPref = getActivity().getSharedPreferences(getString(R.string.idShared), 1);
        new TimeUtill().ModeTime(tVRest, Integer.parseInt(sPref.getString(getString(R.string.TimeForRest), "")));
        new Dial().execute();


        return view;
    }


    public void EndRest() {
        SharedPreferences.Editor ed = sPref.edit();
        //TODO remove this
        try {
            ed.putString(getString(R.string.Count), Integer.toString(Integer.parseInt(sPref.getString(getString(R.string.Count), "")) - 1));
            ed.commit();
            if (Integer.parseInt(sPref.getString(getString(R.string.Count), "")) != 0) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.replace(containerdown, Exercise.getInstance());
                fragTrans.commit();

            }
        } catch(Exception e){}

    }



    class Dial extends AsyncTask<Void, Void, Void> {
        AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            dialog = builder.create();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
//            регулятор загрузки
//            try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (dialog != null) {
                dialog.cancel();
                dialog.dismiss();
                dialog = null;
            }

        }
    }
}
