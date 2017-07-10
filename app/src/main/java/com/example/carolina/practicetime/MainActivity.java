package com.example.carolina.practicetime;

import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carolina.practicetime.database.Database;
import com.example.carolina.practicetime.model.Data;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, AsyncFragment.ParentActivity {

    private static final String LOG_TEXT_KEY = "LOG_TEXT_KEY ";
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.button3)
     Button button3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.textView5)
    TextView textView5;
    Data data = new Data();
    Database database = new Database();


    private GlobalVariables globalVariables;
    private AsyncFragment mFragment;
    private static final String FRAGMENT_TAG =  "FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentManager manager = getFragmentManager();
        mFragment = (AsyncFragment) manager.findFragmentByTag(FRAGMENT_TAG);
        if (mFragment == null){
            mFragment = new AsyncFragment();
            manager.beginTransaction().add(mFragment, FRAGMENT_TAG).commit();
        }

        globalVariables = (GlobalVariables) getApplicationContext();
        textView3.setText(globalVariables.getVariables());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(data);
                add2();
                editText.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });


    }


    public void add(Data data) {

        String info = editText.getText().toString().trim();
        if (!info.isEmpty()) {
            data.setData(info);
            textView.setText(info);
        }
    }

    public void add2() {
        String hello = editText.getText().toString().trim();
        Database.database(this, textView2, hello, true);

    }

    public void clear() {
        editText.setText("");
        textView.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Database.database(this, textView, editText.getText().toString(), true);
        outState.putString(LOG_TEXT_KEY, textView.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString(LOG_TEXT_KEY));
        Database.database(this, textView2, textView.getText().toString(), true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.database(this, textView3, editText.getText().toString(), true);
        globalVariables.setVariables(textView.getText().toString().trim());
    }

    @Override
    public void onFragmentInteraction() {
        Database.database(this, textView3, textView.getText().toString(), true);
    }

    @OnClick(R.id.button3)
    public void onViewClicked() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mFragment.runAsyntask("pepi", "pepo", "hello");
            }
        });

    }

    public final void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public final void showInfoAsynTask(String s){
        textView4.setText(s);
    }

    public void displayMessage (String message){
        textView5.append(message + "\n");

    }

    @Override
    public void handleTaskUpdate(String message) {
        displayMessage(message);
    }


}
