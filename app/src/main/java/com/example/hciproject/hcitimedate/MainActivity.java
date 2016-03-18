package com.example.hciproject.hcitimedate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    public final static String GOAL_DATES = "com.mycompany.myfirstapp.DATES";
    public final static String GOAL_TIMES = "com.mycompany.myfirstapp.TIMES";
    String dates = "01-01-2016, 29-05-1991";
    String times = "08:07 AM, 10:03 PM";

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

    public void startAndroid(View view) {
        Intent intent = new Intent(this, AndroidActivity.class);

        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivity(intent);
    }

    public void startiOS(View view) {
        Intent intent = new Intent(this, iOSActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivity(intent);
    }

    public void startNew(View view) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        startActivity(intent);
    }
}
