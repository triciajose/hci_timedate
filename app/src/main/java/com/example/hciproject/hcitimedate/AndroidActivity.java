package com.example.hciproject.hcitimedate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;


public class AndroidActivity extends ActionBarActivity implements
        View.OnClickListener {

        public String[] goal_dates;
        public String[] goal_times;
        public int counter = 0;
        public int last = 1;
        Button btnDatePicker, btnTimePicker, ok;
        TextView txtDate, txtTime, timer;
        long startTime, endTime;
        private int mYear, mMonth, mDay, mHour, mMinute;
        OutputStreamWriter outputWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
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
        //timer = new Chronometer(MainActivity.this);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        ok = (Button) findViewById(R.id.ok);
        txtDate=(TextView)findViewById(R.id.in_date);
        txtTime=(TextView)findViewById(R.id.in_time);

        timer = (TextView) findViewById(R.id.timer);

        Intent intent = getIntent();
        goal_dates = intent.getStringExtra(MainActivity.GOAL_DATES).split(",");
        goal_times = intent.getStringExtra(MainActivity.GOAL_TIMES).split(",");

        getSupportActionBar().setTitle(getTitle(counter));

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android, menu);
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

        if (v == btnDatePicker) {
            // Get Current Date
            startTime = System.nanoTime();
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if (hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                                txtTime.setText(hourOfDay + ":" + minute + " PM");
                            }
                            else
                            {
                                txtTime.setText(hourOfDay + ":" + minute + " AM");
                            }
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
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
            timer.setText(time);
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
