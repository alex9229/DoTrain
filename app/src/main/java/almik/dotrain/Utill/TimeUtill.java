package almik.dotrain.Utill;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import almik.dotrain.General;
import almik.dotrain.fragments.Rest;

/**
 * Created by alex on 15.02.16.
 */
public class TimeUtill {
    private TextView tvTime;
    private Integer time;

    public void ModeTime(TextView tvTime, Integer time ){
       this.tvTime = tvTime;
        MyTask myTask=new MyTask();
        myTask.execute();
        this.time=time;
    }

    class MyTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {int counter = 0;
                for (int i = 0; i < time; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(++counter);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Rest.getInstance().EndRest();

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
           tvTime.setText(Integer.toString(time- values[0])+" сек.");
        }
    }

}
