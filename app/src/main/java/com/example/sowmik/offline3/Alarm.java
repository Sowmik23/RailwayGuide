package com.example.sowmik.offline3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends BroadcastReceiver  {

    MediaPlayer mp;
    String title, desc, from, to , via;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Journey",Toast.LENGTH_LONG).show();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(context.getApplicationContext(), notification);

        mp.start();
        title = intent.getStringExtra("t1");
        desc = intent.getStringExtra("d");
        System.out.println("Fired alarm on " + System.currentTimeMillis());

        Intent i = new Intent();
        i.setClassName("com.example.sowmik.offline3",     "com.example.sowmik.offline3.AlertAcitivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        //Toast.makeText(context,"Turned off",Toast.LENGTH_LONG).show();
                        mp.stop();
                    }
                },
                8000
        );

    }

}
