package com.example.sowmik.offline3;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    Button btnSet, btn, but, btnSetAlarm;
    EditText et1, et2, et3, et5, et6, et7;
    TextView etVal;
    DatePicker date;
    TimePicker time;
    static String title, desc, from, to, via;
    public static String reminderSet = "false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnSet = findViewById(R.id.btnSetAlarm);
        etVal =  findViewById(R.id.etVal);
        et1 =  findViewById(R.id.et1);
        et2 =  findViewById(R.id.et2);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);

        //but = (Button) findViewById(R.id.button);


        date =  findViewById(R.id.datepicker);
        time =  findViewById(R.id.timepicker);
        btnSetAlarm =  findViewById(R.id.btnSetAlarm);


        // perform click event on submit button
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker

                //get texts
                title = et1.getText().toString();
                desc = et2.getText().toString();
                from = et5.getText().toString();
                to = et6.getText().toString();
                via = et7.getText().toString();


                String day = "" + date.getDayOfMonth();
                String month = "" + (date.getMonth() + 1);
                String year = "" + date.getYear();

                String hour = "" + time.getHour();
                String minute = "" + time.getMinute();

                String second = "00";

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(from) || TextUtils.isEmpty(to) || TextUtils.isEmpty(via)) {
                    System.out.println("Found a null!");
                    Toast.makeText(getApplicationContext(), "Can't leave any field empty", Toast.LENGTH_LONG).show();
                }

                // display the values by using a toast
                //Toast.makeText(getApplicationContext(), "Day: " + day + "/" + month + "/" + year + "\n" + "Time: " + hour + ":" + minute, Toast.LENGTH_LONG).show();

                else{
                    //Calculation
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    long time = 0, timeInMinute = 0;
                    Date d1 = null;
                    Date d2 = Calendar.getInstance().getTime();
                    String dt1 = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":00";

                    try {
                        d1 = format.parse(dt1);
                        time = d1.getTime() - d2.getTime();
                        System.out.println(d2.getTime() + ", " + d1.getTime() + ", " + "Time: " + time + " (ms)/ Current time: " + System.currentTimeMillis());
                        timeInMinute = time / (1000 * 60);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    AlarmManager a = (AlarmManager) getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlarmMod.class);
                    PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);

                    //Send data to SavedReminder

                    Intent intent_cr = new Intent(Main2Activity.this, SavedReminder.class);
                    intent_cr.putExtra("t1", title);
                    intent_cr.putExtra("d", desc);
                    intent_cr.putExtra("f", from);
                    intent_cr.putExtra("t2", to);
                    intent_cr.putExtra("v", via);

                    startActivity(intent_cr);
                    Log.d("send", "msg");
                    reminderSet = "true";
                    Intent intent_setting = new Intent("name").putExtra("set", reminderSet);
                    LocalBroadcastManager.getInstance(Main2Activity.this).sendBroadcast(intent_setting);

                    a.setExact(AlarmManager.RTC, System.currentTimeMillis() + time, p1);
                    System.out.println("Reminder set successfully, after " + time/(1000) + " seconds");
                    //Toast.makeText(getApplicationContext(),"Alarm set in "+time+"seconds",Toast.LENGTH_LONG).show();
                    //System.out.println(mine.getTitle()+": "+mine.getDesc());
                    //Toast.makeText(getApplicationContext(), "Alarm Set at " + dt1 + "\nafter " + time/1000 + " seconds", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
