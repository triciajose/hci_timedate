package com.example.hciproject.hcitimedate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import android.widget.RadioGroup;
public class MainActivity extends ActionBarActivity {

    public final static String GOAL_DATES = "com.mycompany.myfirstapp.DATES";
    public final static String GOAL_TIMES = "com.mycompany.myfirstapp.TIMES";
    public final static String LEFT = "com.mycompany.myfirstapp.LEFT";
    public final static String TASK_NO = "1";
    //month-date-year
    String dates = "01-01-2016,05-29-2016,03-23-2016,04-17-2016,11-30-2016,08-02-2016,10-01-2016,09-14-2016,04-01-2016,06-11-2016";
    String times = "08:07 AM,10:03 PM,6:00 PM,2:30 PM,11:45 AM,8:00 AM,12:00 PM,7:00 PM,11:11 AM,1:15 PM";

    static final int FIRST_REQUEST = 1;  // The request code
    int ANDROID = 10;
    int IOS = 20;
    int NEW = 30;
    static final int SECOND_REQUEST = 2;  // The request code
    static final int THIRD_REQUEST = 3;  // The request code
    boolean left = true;
    int first;
    int second;
    int third;
    String participant_id;
    CheckBox checkbox;
    Button start;
    TextView part_id;
    EditText part_id_inp;
    RadioGroup radio;
    // android, ios, new
    int [] order = {1, 2, 3};


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ain:
                if (checked) {
                    order[0] = 1;
                    order[1] = 2;
                    order[2] = 3;
                    // Pirates are the best
                    break;
                }
            case R.id.radio_ina:
                if (checked) {
                    order[0] = 2;
                    order[1] = 3;
                    order[2] = 1;
                    break;
                }
            case R.id.radio_nai:
                if (checked) {
                    order[0] = 3;
                    order[1] = 1;
                    order[2] = 2;
                    break;
                }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox=(CheckBox)findViewById(R.id.lefthanded);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                boolean checked = ((CheckBox) buttonView).isChecked();
                switch(buttonView.getId()) {
                    case R.id.lefthanded:
                        if (checked){
                            left = true;
                        }
                        else
                        {
                            left = false;
                        }
                        break;
                }
            }
        });
        start = (Button)findViewById(R.id.our_start);
        part_id = (TextView)findViewById(R.id.participant_id_tag);
        part_id_inp = (EditText)findViewById(R.id.participant_id);
        participant_id = part_id_inp.getText().toString();
        radio = (RadioGroup)findViewById(R.id.radioGroup);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkbox = (CheckBox) findViewById(R.id.lefthanded);
                checkbox.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
                part_id.setVisibility(View.GONE);
                part_id_inp.setVisibility(View.GONE);
                radio.setVisibility(View.GONE);
                participant_id = part_id_inp.getText().toString();
                Log.v("PARTICIPANT ID",String.valueOf(participant_id));
            }
        });
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

    public void startAndroid(int request, String run) {
        Intent intent = new Intent(this, AndroidActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        intent.putExtra("ID",participant_id);
        intent.putExtra("run", run);
        startActivityForResult(intent, request);
    }

    public void startiOS(int request, String run) {
        Intent intent = new Intent(this, iOSActivity.class);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        intent.putExtra("ID",participant_id);
        intent.putExtra("run", run);
        startActivityForResult(intent, request);
    }

    public void startNew(int request, String run) {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra(LEFT,left);
        intent.putExtra(GOAL_DATES, dates);
        intent.putExtra(GOAL_TIMES, times);
        intent.putExtra("ID",participant_id);
        intent.putExtra("run", run);
        startActivityForResult(intent, request);
    }

    public void startTask1(View view) {
        //order = shuffleArray(order);
        first = order[0];
        second = order[1];
        third = order[2];
        Log.v("first",String.valueOf(first));
        Log.v("second", String.valueOf(second));
        Log.v("third", String.valueOf(third));
        startRun("1");
    }
    public void startTask2(View view) {
        //order = shuffleArray(order);
        first = order[0];
        second = order[1];
        third = order[2];
        Log.v("first",String.valueOf(first));
        Log.v("second",String.valueOf(second));
        Log.v("third",String.valueOf(third));
        startRun("2");
    }

    public void startRun(String run) {
        switch(first) {
            case 1:
                Log.v("first", "android");
                startAndroid(FIRST_REQUEST,run);
                break;
            case 2:
                Log.v("first", "ios");
                startiOS(FIRST_REQUEST,run);
                break;
            case 3:
                Log.v("first", "new");
                startNew(FIRST_REQUEST,run);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras = getIntent().getExtras();
        String run = "1";
        if (extras != null) {
            run = extras.getString("run");
            Log.v("run",String.valueOf("run"));
        }
        // Check which request we're responding to
        if (requestCode == FIRST_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                switch (second) {

                    case 1:
                        Log.v("second", "android");
                        startAndroid(SECOND_REQUEST, run);
                        break;
                    case 2:
                        Log.v("second", "ios");
                        startiOS(SECOND_REQUEST, run);
                        break;
                    case 3:
                        Log.v("third", "new");
                        startNew(SECOND_REQUEST, run);
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
                        startAndroid(THIRD_REQUEST, run);
                        break;
                    case 2:
                        Log.v("second", "ios");
                        startiOS(THIRD_REQUEST, run );
                        break;
                    case 3:
                        Log.v("third", "new");
                        startNew(THIRD_REQUEST, run);
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
                startRun("2");
                // do nothing
            }
        }
    }

}
