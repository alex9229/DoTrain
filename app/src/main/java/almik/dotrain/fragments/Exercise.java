package almik.dotrain.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import almik.dotrain.R;

import static almik.dotrain.R.id.containerdown;


public class Exercise extends Fragment implements View.OnTouchListener {
    Integer count;// счетчик
    TextView tvCount;
    private static Exercise exercise;
    private SharedPreferences sPref;

    public Exercise() {
        // Required empty public constructor
    }


    public static Exercise getInstance() {
        if (exercise == null) {
            exercise = new Exercise();
        }
        return exercise;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        FrameLayout ll = (FrameLayout) view.findViewById(R.id.FLexercise);
        ll.setOnTouchListener(this);// Устанавливаем данный класс в качестве слушателя MotionEvent'ов для нашего LinearLayout
//        Intent intentfrom = getIntent();
//        count=Integer.parseInt(intentfrom.getStringExtra(getString(R.string.countInIntentToMain)));
//        timeofrest=Integer.parseInt(intentfrom.getStringExtra(getString(R.string.timeRestInIntentToMain)));
//        КОСТЫЛЬ
//

        sPref = getActivity().getSharedPreferences(getString(R.string.idShared), 1);
        count = Integer.parseInt(sPref.getString(getString(R.string.TimeForRest), ""));

        tvCount = (TextView) view.findViewById(R.id.tvCount);
        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        count = Integer.parseInt(sPref.getString(getString(R.string.Count), ""));
        if (count > 1) {
            tvCount.setText("вам осталось " + count + " повторений");
            FragmentTransaction fragTrans;
            fragTrans = getFragmentManager().beginTransaction();    ///set the fragment
            fragTrans.replace(containerdown, Rest.getInstance());
            fragTrans.commit();
        } else {


            AlertDialog.Builder ad;
            Context context;
            context = getActivity();
            ad = new AlertDialog.Builder(context);
            ad.setTitle(R.string.TitleDialogfinish);  // заголовок
            ad.setMessage(R.string.messageMoreExercises); // сообщение
            ad.setPositiveButton(R.string.OneMore, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    FragmentTransaction fragTrans;
                    fragTrans = getFragmentManager().beginTransaction();    ///set the fragment
                    fragTrans.replace(containerdown, StartFrament.getInstance());
                    fragTrans.commit();

                    //positive
                }
            });
            ad.setNegativeButton(R.string.FinishTrain, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
//                    System.exit(0);
//
//                    getActivity().finish();///мочит чисто активити

                    android.os.Process.killProcess(android.os.Process.myPid());//убивает насмерть


                    ///negative
                }
            });
            ad.setCancelable(false);
            ad.show();

            /// Выход на страницу выхода
        }
        return false;
    }
}
