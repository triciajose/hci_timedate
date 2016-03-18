package com.example.hciproject.hcitimedate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class iOSActivity extends ActionBarActivity implements View.OnClickListener {
    public String[] goal_dates;
    public String[] goal_times;
    public int counter = 0;
    OutputStreamWriter outputWriter;
    TextView txtDate, txtTime, timer;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        goal_dates = intent.getStringExtra(MainActivity.GOAL_DATES).split(",");
        goal_times = intent.getStringExtra(MainActivity.GOAL_TIMES).split(",");

        String datetime = goal_dates[counter] + ", " + goal_times[counter];

        getSupportActionBar().setTitle(datetime);
        setContentView(R.layout.activity_i_os);
        try {
            File output = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mytextfile.txt");
            FileOutputStream fileOut = new FileOutputStream(output);

            //FileOutputStream fileOut=openFileOutput(my, MODE_PRIVATE);
            outputWriter=new OutputStreamWriter(fileOut);
            //outputWriter.write(textmsg.getText().toString());

            //display file saved message
        } catch (Exception e) {
            e.printStackTrace();
        }
        ok = (Button) findViewById(R.id.ok);
        txtDate=(TextView)findViewById(R.id.in_date_ios);
        txtTime=(TextView)findViewById(R.id.in_time_ios);
        ok.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_i_o, menu);
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
    public void onClick(View v) {

    }
}
