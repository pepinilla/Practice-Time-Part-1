package com.example.carolina.practicetime;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by carolina on 06/07/17.
 */

public class AsyncFragment extends Fragment{

    private ParentActivity mParent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public interface ParentActivity{
        void handleTaskUpdate(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParent = (ParentActivity) context;
        Log.e("AsyncFragment", "attached");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParent = (ParentActivity) activity;
        Log.e("AsyncFragment", "attached");
    }

    public void runAsyntask (String... params){
        MyTask task = new MyTask();
        task.execute(params);
    }
    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for (String s : params) {
                publishProgress("I got " + s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mParent.handleTaskUpdate(values[0]);

        }
    }
}
