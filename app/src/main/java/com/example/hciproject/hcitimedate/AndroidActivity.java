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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AndroidActivity extends ActionBarActivity implements
        View.OnClickListener {

        public String[] goal_dates;
        public String[] goal_times;
        public String[] input_dates = new String[2*MainActivity.TRIALS];
        public String[] input_times = new String[2*MainActivity.TRIALS];
        public int counter = 0;
        public long secs;
        //public int last = 1;
        Button btnDatePicker, btnTimePicker, ok;
        TextView txtDate, txtTime;
        long startTime, endTime;
        private int mYear, mMonth, mDay, mHour, mMinute;
        OutputStreamWriter outputWriter;
        CountDownTimer ctimer;
        DatePickerDialog datePickerDialog;
        TimePickerDialog timePickerDialog;
        String participant_id;
        String run = "1";
        boolean countdownStarted = false;
        public int request = 0;
        long averageTime = 0;
        int iHour = 12;
        int iMin = 0;
        int iDay = 1;
        int iMonth = 6;
        String setHour = "12", setMin = "00", setDay = "01", setMonth="07";
        String setAM = "AM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        for (int i =0; i < 2 * MainActivity.TRIALS; i++)
        {
            input_times[i]="";
            input_dates[i]="";
        }
        Intent intent = getIntent();
        //intent.putExtra("ID",participant_id);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            run = extras.getString("run");
            request = extras.getInt("request");
            Log.v("run",String.valueOf(run));
        }
        participant_id = intent.getStringExtra("ID");
        try {
            String fileName = participant_id;
            File output;
            if (run.equals("2"))
            {
                output = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "p"+fileName+"AN-2.txt");
            }
            else
            {
                output = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "p"+fileName+"AN.txt");
            }
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



        goal_dates = intent.getStringExtra(MainActivity.GOAL_DATES).split(",");
        goal_times = intent.getStringExtra(MainActivity.GOAL_TIMES).split(",");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (run.equals("2"))
        {
            counter = counter + MainActivity.TRIALS;
        }
        if (run.equals("2")) {
            if (request == MainActivity.FORTH_REQUEST){
                builder.setMessage("TASK 2\n\nFor this task, select the date and time as quickly as possible.  You will have a limited amount of time, so don’t worry about going back and correcting errors if you make one, just keep going. Don’t worry about selecting a year, you can leave the year defaulted at 2016. Press ok as soon as you’ve finished. You will then hit start and repeat this until you have done a total of 5 trials. Once complete, you will be prompted to hand back the phone. ");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("You're about to start trials on an Android-like interface. There will be " + goal_times.length / 2 + " trials for this interface.\n\nReady to begin?\n\nPlease enter\n" + getTitle(counter));
                builder2.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //startTime = System.nanoTime();
                        //if (run.equals("2")) {
                        //    ctimer = new MyCountDown(11000, 1000);
                        //}
                        // TODO: start timer
                        dialog.dismiss();
                    }
                });
                builder2.create();
                builder2.show();
            }
            else {
                builder.setMessage("You're about to start timed trials on an Android-like interface. There will be " + goal_times.length / 2 + " trials for this interface and you have 10 seconds for each trial.\n\nReady to begin?\n\nPlease enter\n" + getTitle(counter));
            }
        }
        else
        {
            if (request == MainActivity.FIRST_REQUEST)
            {
                builder.setMessage("TASK 1\n\nFor this task, select the date and time as accurately as possible. Don’t worry about selecting a year, you can leave the year defaulted at 2016. Press ok as soon as you’ve finished. You will then hit start and repeat this until you have done a total of 5 trials.  You will then be prompted to start Task 2.");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("You're about to start trials on an Android-like interface. There will be " + goal_times.length / 2 + " trials for this interface.\n\nReady to begin?\n\nPlease enter\n" + getTitle(counter));
                builder2.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         //startTime = System.nanoTime();
                        //if (run.equals("2")) {
                        //    ctimer = new MyCountDown(11000, 1000);
                        //}
                        // TODO: start timer
                        dialog.dismiss();
                    }
                });
                builder2.create();
                builder2.show();
            }
            else {
                builder.setMessage("You're about to start trials on an Android-like interface. There will be " + goal_times.length / 2 + " trials for this interface.\n\nReady to begin?\n\nPlease enter\n" + getTitle(counter));
            }
        }
        builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //startTime = System.nanoTime();
                //if (run.equals("2")) {
                //    ctimer = new MyCountDown(11000, 1000);
                //}
                // TODO: start timer
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
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
            if (run.equals("2")) {
                ctimer = new MyCountDown(11000, 1000);
                countdownStarted = true;
            }
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            iMonth = monthOfYear;
                            iDay = dayOfMonth;
                            int month = monthOfYear + 1;
                            if (month < 10)
                            {
                                if (dayOfMonth < 10)
                                {
                                    input_dates[counter] = "0" + month + "-0" + dayOfMonth + "-" + "2016";
                                }
                                else
                                {
                                    input_dates[counter] = "0" + month + "-" + dayOfMonth + "-" + "2016";
                                }
                            }
                            else
                            {
                                if (dayOfMonth < 10)
                                {
                                    input_dates[counter] = month + "-0" + dayOfMonth + "-" + "2016";
                                }
                                else
                                {
                                    input_dates[counter] = month + "-" + dayOfMonth + "-" + "2016";
                                }
                            }
                            //input_dates[counter] = monthOfYear+1 + "-" + dayOfMonth + "-" + "2016";
                            Log.v("Counter:",String.valueOf(counter));
                            Log.v("Input Date:", input_dates[counter]);
                            txtDate.setText(getMonth(monthOfYear + 1) + " " + dayOfMonth);
                            setMonth =input_dates[counter].split("-")[0];
                            setDay = input_dates[counter].split("-")[1];
                        }
                    }, 2016, iMonth, iDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            if (run.equals("2") && countdownStarted == false) {
                ctimer = new MyCountDown(11000, 1000);
                countdownStarted = true;
            }
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            iHour = hourOfDay;
                            iMin = minute;
                            System.out.println("Hour: " + hourOfDay + "Minutes: " + minute);
                            if (hourOfDay >= 12) {
                                if (hourOfDay != 12)
                                {
                                    hourOfDay = hourOfDay - 12;
                                }
                                if (hourOfDay < 10)
                                {
                                    if (minute < 10)
                                    {
                                        txtTime.setText("0" + hourOfDay + ":0" + minute + " PM");
                                    }
                                    else
                                    {
                                        txtTime.setText("0" + hourOfDay + ":" + minute + " PM");
                                    }
                                }
                                else
                                {
                                    if (minute < 10)
                                    {
                                        txtTime.setText(hourOfDay + ":0" + minute + " PM");
                                    }
                                    else
                                    {
                                        txtTime.setText(hourOfDay + ":" + minute + " PM");
                                    }
                                }
                                //txtTime.setText(hourOfDay + ":" + minute + " PM");
                            }
                            else
                            {
                                if (hourOfDay == 0)
                                {
                                    hourOfDay = 12;
                                }
                                if (hourOfDay < 10)
                                {
                                    if (minute < 10)
                                    {
                                        txtTime.setText("0" + hourOfDay + ":0" + minute + " AM");
                                    }
                                    else
                                    {
                                        txtTime.setText("0" + hourOfDay + ":" + minute + " AM");
                                    }
                                }
                                else
                                {
                                    if (minute < 10)
                                    {
                                        txtTime.setText(hourOfDay + ":0" + minute + " AM");
                                    }
                                    else
                                    {
                                        txtTime.setText(hourOfDay + ":" + minute + " AM");
                                    }
                                }
                            }
                            setHour = txtTime.getText().toString().split(" ")[0].split(":")[0];
                            setMin = txtTime.getText().toString().split(" ")[0].split(":")[1];
                            setAM = txtTime.getText().toString().split(" ")[1];
                            input_times[counter] = txtTime.getText().toString();
                            Log.v("Counter:", String.valueOf(counter));
                            Log.v("Input Date:", input_times[counter]);
                        }
                    }, iHour, iMin, false);
            timePickerDialog.show();
        }
        if (v == ok)
        {
            iHour = 12;
            iMin = 0;
            iDay = 1;
            iMonth = 6;
            if (ctimer!=null) {
                ctimer.cancel();
            }
            //Check if accurate
            boolean result = false;
            //01-01-2016
            //if (input_times[counter])
            String goalHour = (goal_times[counter].split(" "))[0].split(":")[0];
            String goalMin = (goal_times[counter].split(" "))[0].split(":")[1];
            String goalDay = (goal_dates[counter].split("-"))[1];
            String goalMonth = (goal_dates[counter].split("-"))[0];
            String goalAM = (goal_times[counter].split(" "))[1].split(":")[0];

            System.out.println("GoalHour: " + goalHour);
            System.out.println("GoalMin: " + goalMin);
            System.out.println("SetHour: " + setHour);
            System.out.println("SetMin: " + setMin);
            int points = 0;
            if (setHour.equals(goalHour))
            {
                points  = points + 1;
            }
            if (setMin.equals(goalMin))
            {
                points = points + 1;
            }
            if (setDay.equals(goalDay))
            {
                points = points + 1;
            }
            if (setMonth.equals(goalMonth))
            {
                points = points + 1;
            }
            if (setAM.equals(goalAM))
            {
                points = points + 1;
            }
    System.out.println("Points: " + points);

            if (input_times[counter].equals(goal_times[counter]) && goal_dates[counter].equals(input_dates[counter]))
            {
                result = true;
            }
            else
            {
                result = false;
            }
            endTime = System.nanoTime();
            counter++;
            System.out.println("Counter = " + counter);

            long minutesElapsed = ((endTime - startTime)/1000000000)/60;
            long secondsElapsed = (endTime - startTime)/1000000000;
            averageTime = averageTime + secondsElapsed;
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
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(System.currentTimeMillis());
                Log.v("Time:", time);
                outputWriter.append(participant_id + " " + sdf.format(resultdate) + " " + time.toString() + " " + result + " " + points + "\n");
                //outputWriter.write(participant_id + " " + System.currentTimeMillis() + " " + time + " " + result + "\n");
            }
            catch (Exception e)
            {

            }
//            Intent intent = getIntent();
            if (counter < goal_times.length && run.equals("2") || counter < MainActivity.TRIALS && run.equals("1")) {
                countdownStarted = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Ready for the next trial?\n\nPlease enter\n" + getTitle(counter));
                builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //startTime = System.nanoTime();
                        //if (run.equals("2")) {
                        //    ctimer = new MyCountDown(11000, 1000);
                        //}
                        // TODO: start timer
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();

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
        String day = (goal_dates[counter].split("-")[1]);
        String datetime = month + " " + day + ", " + goal_times[counter];

        return datetime;
    }

    private class MyCountDown extends CountDownTimer
    {
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            //frameAnimation.start();
            start();
        }

        @Override
        public void onFinish() {
            secs = 20;
            counter++;
            if (counter < goal_times.length) {
                countdownStarted = false;
                String goalHour = (goal_times[counter].split(" "))[0].split(":")[0];
                String goalMin = (goal_times[counter].split(" "))[0].split(":")[1];
                String goalDay = (goal_dates[counter].split("-"))[1];
                String goalMonth = (goal_dates[counter].split("-"))[0];
                String goalAM = (goal_times[counter].split(" "))[1].split(":")[0];
                int points = 0;
                if (setHour.equals(goalHour))
                {
                    points  = points + 1;
                }
                if (setMin.equals(goalMin))
                {
                    points = points + 1;
                }
                if (setDay.equals(goalDay))
                {
                    points = points + 1;
                }
                if (setMonth.equals(goalMonth))
                {
                    points = points + 1;
                }
                if (setAM.equals(goalAM))
                {
                    points = points + 1;
                }
                boolean result = false;
                double time = 10.1;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                    Date resultdate = new Date(System.currentTimeMillis());
                    outputWriter.append(participant_id + " " + sdf.format(resultdate) + " " + time + " " + result + " " + points + "\n");
                    //outputWriter.write("!~!" + participant_id + " " + System.currentTimeMillis() + " " + time + " " + result + "\n");
                }
                catch (Exception e)
                {

                }
                if (datePickerDialog != null) {
                    datePickerDialog.dismiss();
                }
                if (timePickerDialog!=null)
                {
                    timePickerDialog.dismiss();
                }//timePickerDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(AndroidActivity.this);
                builder.setMessage("Ready for the next trial?\n\nPlease enter\n" +getTitle(counter));
                builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //startTime = System.nanoTime();
                        //if (run.equals("2")) {
                        //    ctimer = new MyCountDown(11000, 1000);
                        //}
                        // TODO: start timer
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();

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

        @Override
        public void onTick(long duration) {
            //cd.setText(String.valueOf(secs));
            secs = secs - 1;
        }
    }
}
