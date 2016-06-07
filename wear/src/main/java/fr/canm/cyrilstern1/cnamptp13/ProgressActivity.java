package fr.canm.cyrilstern1.cnamptp13;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

public class ProgressActivity extends Activity {

    private TextView mTextView;
    public CircledImageView mCircledImageView;
    private float time;
    private  AsyncTask asyncTask;

    @Override
    protected void onStart() {
        super.onStart();
        asyncTask.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        asyncTask.cancel(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        time = 0.0f;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mCircledImageView = (CircledImageView) findViewById(R.id.circledimageview);
                mCircledImageView.setProgress(time);
            }
        });

       asyncTask= new AsyncTask() {
          @Override
          protected Object doInBackground(Object[] params) {
        while (time < 1f) {
            time += 0.02f;
            publishProgress(time);
            SystemClock.sleep(50);
            Log.i("blble", String.valueOf(time));

        }
              time=0;
              return null;
          }

          @Override
          protected void onProgressUpdate(Object[] values) {
              super.onProgressUpdate(values);
            mCircledImageView.setProgress((Float) values[0]);
          }
      };


    }


}
