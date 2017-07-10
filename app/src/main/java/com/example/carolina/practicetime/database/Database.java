package com.example.carolina.practicetime.database;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by carolina on 05/07/17.
 */

public class Database {

    public static void database (Context context, TextView tv, String message, boolean b)
    {
        if (tv != null && message != null && !message.isEmpty()){
            if(b){
                tv.append(message);
                }
            else {
                tv.setText(message);
            }
            Log.i(context.getClass().getSimpleName(), message);
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    }
}
