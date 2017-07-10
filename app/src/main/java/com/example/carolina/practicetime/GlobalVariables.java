package com.example.carolina.practicetime;

import android.app.Application;

/**
 * Created by carolina on 06/07/17.
 */

public class GlobalVariables extends Application {
    private String variables;

    public void setVariables(String variables) {
        this.variables = variables;
    }


    public String getVariables() {
        return variables;
    }
}
