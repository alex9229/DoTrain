package almik.dotrain.Utill;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import almik.dotrain.R;
import almik.dotrain.fragments.Exercise;

import static almik.dotrain.R.id.containerdown;
public class StartDialog implements OnClickListener {

    private Button btLounch;
    private EditText EtCount;
    private EditText EtTimeRest;
    private SharedPreferences sPref;



        private Dialog dialog;
        private Activity activity;

        public StartDialog( Activity activity ){
            this.activity = activity;
            init();

        }

        private void init(){
            LayoutInflater inflater = LayoutInflater.from( this.activity );
            View layout = inflater.inflate(R.layout.dialog_choise, null);
            btLounch=(Button)layout.findViewById(R.id.BtStart);
            EtCount=(EditText)layout.findViewById(R.id.ETColPod);
            EtTimeRest=(EditText)layout.findViewById(R.id.ETTimeRest);
            btLounch.setOnClickListener(this);
            AlertDialog.Builder builder = new AlertDialog.Builder( this.activity );
            builder.setView(layout);
            dialog = builder.create();
            dialog.setTitle("Здраствуйте, введите нужную информацию");
            dialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        android.os.Process.killProcess(android.os.Process.myPid());//убивает насмерть
                    }
                    return false;//if true then can`t add number to editText
                }
            });
            dialog.setCancelable(false);   //commit if need clicable



        }

        public void show(){
            dialog.show();
        }


    @Override
    public void onClick(View v) {
        try {
            if (Integer.parseInt(EtCount.getText().toString())>0&&Integer.parseInt(EtTimeRest.getText().toString())>0){
                //передача здесь данные в синглтон или в преференсес
                sPref = activity.getSharedPreferences(activity.getString(R.string.idShared), 1);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(activity.getString(R.string.Count), EtCount.getText().toString());
                ed.putString(activity.getString(R.string.TimeForRest), EtTimeRest.getText().toString());
                ed.commit();
                FragmentTransaction fragTrans;
                fragTrans = activity.getFragmentManager().beginTransaction();    ///set the fragment
                fragTrans.replace(containerdown, Exercise.getInstance());
                fragTrans.commit();
                dialog.cancel();
            }
        }catch (Exception e){ Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
