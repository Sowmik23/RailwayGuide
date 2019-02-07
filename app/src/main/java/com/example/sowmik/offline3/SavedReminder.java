package com.example.sowmik.offline3;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SavedReminder extends AppCompatActivity {

    TextView title, desc, from, to , via;
    ImageView del, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_reminder);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Toast.makeText(getApplicationContext(), "Reminder Created, new", Toast.LENGTH_LONG).show();
        home = findViewById(R.id.homeimg);
        del = findViewById(R.id.delimg);
        title = findViewById(R.id.tv1);
        desc = findViewById(R.id.tv2);
        from = findViewById(R.id.tv3);
        to = findViewById(R.id.tv4);
        via = findViewById(R.id.tv5);

        Intent intent = getIntent();
        String Title = intent.getStringExtra("t1");
        String Desc = intent.getStringExtra("d");
        String From = intent.getStringExtra("f");
        String To = intent.getStringExtra("t2");
        String Via = intent.getStringExtra("v");


        System.out.println(Title+", "+Desc);
        title.setText(Main2Activity.title);
        desc.setText(Main2Activity.desc);
        from.setText(Main2Activity.from);
        to.setText(Main2Activity.to);
        via.setText(Main2Activity.via);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SavedReminder.this, Main2Activity.class);
                startActivity(intent1);
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(SavedReminder.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(SavedReminder.this);
                }

                builder.setTitle("Delete")
                        .setMessage("Sure to delete the Journey Reminder?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                AlarmManager a=(AlarmManager)getSystemService(ALARM_SERVICE);
                                Intent in=new Intent(getApplicationContext(), AlarmMod.class);
                                PendingIntent p1=PendingIntent.getBroadcast(getApplicationContext(),1, in,0);

                                a.cancel(p1);
                                Main2Activity.reminderSet = "false";
                                System.out.println("Alarm cancelled");

                                Intent intent1 = new Intent(SavedReminder.this,  Main2Activity.class);
                                startActivity(intent1);

                                Toast.makeText(getApplicationContext(), "Reminder deleted", Toast.LENGTH_LONG);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

}
