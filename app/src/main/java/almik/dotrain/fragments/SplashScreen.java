package almik.dotrain.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import almik.dotrain.R;

import static almik.dotrain.R.id.containerdown;

/**
 *
 */
public class SplashScreen extends Fragment {


    private static SplashScreen instanse;

    public static SplashScreen getInstance() {
        if (instanse == null) {
            instanse = new SplashScreen();
        }
        return instanse;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyTask myTask = new MyTask();
        myTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }


    class MyTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            FragmentTransaction fragTrans;
            fragTrans = getFragmentManager().beginTransaction();    ///set the fragment
            fragTrans.replace(containerdown, StartFrament.getInstance());
            fragTrans.commit();
        }
    }

}
