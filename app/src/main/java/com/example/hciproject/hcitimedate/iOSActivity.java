package com.example.hciproject.hcitimedate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
    long startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        goal_dates = intent.getStringExtra(MainActivity.GOAL_DATES).split(",");
        goal_times = intent.getStringExtra(MainActivity.GOAL_TIMES).split(",");

        getSupportActionBar().setTitle(getTitle(counter));

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

        if (v == ok)
        {

            //Check if accurate
            endTime = System.nanoTime();
            counter++;

            long minutesElapsed = ((endTime - startTime)/1000000000)/60;
            long secondsElapsed = (endTime - startTime)/1000000000;
            long millisElapsed = ((endTime - startTime)%1000000000)/1000000;
            String time = "";
            if (minutesElapsed < 10)
            {
                time = time + "0" + minutesElapsed;
            }
            else
            {
                time = time + minutesElapsed;
            }
            if (secondsElapsed < 10)
            {
                time = time + ":0" + secondsElapsed;
            }
            else
            {
                time = time + ":" + secondsElapsed;
            }
            if (millisElapsed < 10)
            {
                time = time + ".00" + millisElapsed;
            }
            else if (millisElapsed < 100)
            {
                time = time + ".0" + millisElapsed;
            }
            else
            {
                time = time + "." +millisElapsed;
            }
            try {
                outputWriter.write(time + "\n");
            }
            catch (Exception e)
            {

            }
//            Intent intent = getIntent();
            if (counter < goal_times.length) {

                getSupportActionBar().setTitle(getTitle(counter));

                txtTime.setText("");
                txtDate.setText("");
            }
            else
            {
                try {
                    if (outputWriter != null) {
                        outputWriter.close();
                    }
                }
                catch (IOException e)
                {

                }
//                Intent intent = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    public String getMonth(int month) {
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public String getTitle(int counter) {
        String month = getMonth(Integer.parseInt(goal_dates[counter].split("-")[0]));
        Log.v("month", month);
        String dayAndYear = (goal_dates[counter].split("-")[1]) + ", " + (goal_dates[counter].split("-")[2]);
        String datetime = month + " " + dayAndYear + ", " + goal_times[counter];

        return datetime;
    }
}
