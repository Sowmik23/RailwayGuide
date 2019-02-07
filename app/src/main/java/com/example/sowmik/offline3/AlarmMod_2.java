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

public class AlarmMod_2 extends BroadcastReceiver {
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Journey", Toast.LENGTH_LONG).show();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(context.getApplicationContext(), notification);

        mp.start();

        System.out.println("Fired alarm on " + System.currentTimeMillis());
        Main2Activity.reminderSet = "false";

        Intent i = new Intent();
        i.setClassName("com.example.sowmik.offline3",     "com.example.sowmik.offline3.Alert_Auto");
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
