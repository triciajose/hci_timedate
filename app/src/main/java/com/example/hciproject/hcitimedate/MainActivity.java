package com.example.hciproject.hcitimedate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends ActionBarActivity {

    public final static String GOAL_DATES = "com.mycompany.myfirstapp.DATES";
    public final static String GOAL_TIMES = "com.mycompany.myfirstapp.TIMES";
    //month-date-year
    String dates = "01-01-2016,05-29-1991";
    String times = "08:07 AM,10:03 PM";
    static final int FIRST_REQUEST = 1;  // The request code
    int ANDROID = 10;
    int IOS = 20;
    int NEW = 30;
    static final int SECOND_REQUEST = 2;  // The request code
    static final int THIRD_REQUEST = 3;  // The request code
    int first;
    int second;
    int third;
    int [] order = {1, 2, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void startAndroid(int request) {
        Intent intent = new Intent(this, AndroidActivity.class);

        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivityForResult(intent, request);
    }

    public void startiOS(int request) {
        Intent intent = new Intent(this, iOSActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivityForResult(intent, request);
    }

    public void startNew(int request) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivityForResult(intent, request);
    }

    public void start(View view) {
        order = shuffleArray(order);
        first = order[0];
        second = order[1];
        third = order[2];
        Log.v("first",String.valueOf(first));
        Log.v("second",String.valueOf(second));
        Log.v("third",String.valueOf(third));

        startRun();
    }

    public void startRun() {

        switch(first) {
            case 1:
                Log.v("first", "android");
                startAndroid(FIRST_REQUEST);
                break;
            case 2:
                Log.v("first", "ios");
                startiOS(FIRST_REQUEST);
                break;
            case 3:
                Log.v("first", "new");
                startNew(FIRST_REQUEST);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == FIRST_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                switch (second) {
                    case 1:
                        Log.v("second", "android");
                        startAndroid(SECOND_REQUEST);
                        break;
                    case 2:
                        Log.v("second", "ios");
                        startiOS(SECOND_REQUEST);
                        break;
                    case 3:
                        Log.v("third", "new");
                        startNew(SECOND_REQUEST);
                        break;
                    default:
                        break;
                }
            }

        }
        // Check which request we're responding to
        if (requestCode == SECOND_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                switch (third) {
                    case 1:
                        Log.v("second", "android");
                        startAndroid(THIRD_REQUEST);
                        break;
                    case 2:
                        Log.v("second", "ios");
                        startiOS(THIRD_REQUEST);
                        break;
                    case 3:
                        Log.v("third", "new");
                        startNew(THIRD_REQUEST);
                        break;
                    default:
                        break;
                }
            }

        }
        // Check which request we're responding to
        if (requestCode == THIRD_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // do nothing
            }
        }
    }
    static int[] shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}
