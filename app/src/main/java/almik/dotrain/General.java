package almik.dotrain;

import almik.dotrain.fragments.Exercise;
import almik.dotrain.fragments.SplashScreen;
import almik.dotrain.fragments.StartFrament;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static almik.dotrain.R.id.containerdown;

public class General extends Activity {
    public FragmentTransaction fragTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        fragTrans = getFragmentManager().beginTransaction();    ///set the fragment
        fragTrans.add(containerdown, SplashScreen.getInstance());
        fragTrans.commit();
    }

    @Override
    public void onBackPressed() {
        ///TODO ДОБАВИТЬ ОБРАБОТКУ кнопки назад в алерт диалог
    }
}
