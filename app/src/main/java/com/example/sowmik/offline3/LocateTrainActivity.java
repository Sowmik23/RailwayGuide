package com.example.sowmik.offline3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocateTrainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 111;

    Button whereAmI, locateTrain;
    EditText trainNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_train);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.SEND_SMS)) {
//
//                ActivityCompat.requestPermissions(LocateTrainActivity.this,
//                        new String[]{Manifest.permission.SEND_SMS}, 1);
//
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.SEND_SMS},
//                        1);
//            }
//        }


        whereAmI = findViewById(R.id.where_am_i_button_id);
        locateTrain = findViewById(R.id.locate_train_button_id);

        trainNumber = findViewById(R.id.train_numberId);



        //whereAmI.setEnabled(false);
        locateTrain.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)){
            locateTrain.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        }


        whereAmI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = trainNumber.getText().toString();
                String message = trainNumber.getText().toString();

                Toast.makeText(LocateTrainActivity.this, "WhereAmI is clicked", Toast.LENGTH_SHORT).show();

//                try {
//
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
//
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//
//                    Toast.makeText(LocateTrainActivity.this, "SMS faild, please try again.", Toast.LENGTH_SHORT).show();
//                }


            }
        });


        locateTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = trainNumber.getText().toString();
                String message = trainNumber.getText().toString();

                Toast.makeText(LocateTrainActivity.this, "LocateTrain is clicked", Toast.LENGTH_SHORT).show();

                if (!TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(message)){

                    if (checkPermission(Manifest.permission.SEND_SMS)){

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo,null,message,null,null);
                    }
                    else {
                        Toast.makeText(LocateTrainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LocateTrainActivity.this, "Enter a train number.", Toast.LENGTH_SHORT).show();
                }

//                try {
//
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
//
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//
//                    Toast.makeText(LocateTrainActivity.this, "SMS faild, please try again.", Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[],
//                                           int[] grantResults) {
//
//        switch (requestCode) {
//            case 1: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (ContextCompat.checkSelfPermission(LocateTrainActivity.this,
//                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//
//                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//
//                    Toast.makeText(this, "SMS faild, please try again.",
//                            Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//        }
//    }


    private boolean checkPermission(String permission)
    {
        int checkPermission = ContextCompat.checkSelfPermission(this,permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    locateTrain.setEnabled(true);
                }
                break;
        }

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
