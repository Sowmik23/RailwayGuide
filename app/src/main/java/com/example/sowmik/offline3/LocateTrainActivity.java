package com.example.sowmik.offline3;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocateTrainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 111;

    private Button  locateTrain;
    //private EditText trainNumber;
    private AutoCompleteTextView autoCompleteTextView;
    String[] trainNumbers;


    Intent intent;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_train);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //whereAmI = findViewById(R.id.where_am_i_button_id);
        locateTrain = findViewById(R.id.locate_train_button_id);

        autoCompleteTextView = findViewById(R.id.train_noId);
        autoCompleteTextView.setText("");

        trainNumbers = getResources().getStringArray(R.array.train_numbers);

        intent = new Intent(getApplicationContext(),LocateTrainActivity.class);
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        //locateTrain.setEnabled(false);

//        if (checkPermission(Manifest.permission.SEND_SMS)){
//
//            locateTrain.setEnabled(true);
//        }
//        else{
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
//        }



        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trainNumbers);
        autoCompleteTextView.setAdapter(adapter2);
        autoCompleteTextView.setThreshold(1);


        //1 ta character er sathe match sob string e dekhabe ei 1 dile r 2 dile 2 ta match ala string dekhabe

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = autoCompleteTextView.getText().toString();


                Toast.makeText(LocateTrainActivity.this, ""+value, Toast.LENGTH_SHORT).show();

            }
        });



/*        whereAmI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(LocateTrainActivity.this, "WhereAmI is clicked.", Toast.LENGTH_SHORT).show();
            }
        });*/


        locateTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = autoCompleteTextView.getText().toString();

                if (str.isEmpty()) {

                    showError(autoCompleteTextView, R.string.required2);

                }
                else {

                    String phoneNo = "16318";
                    String t_no = "";
                    t_no = autoCompleteTextView.getText().toString();

                    String message = "Tr " + t_no;

                    //Toast.makeText(LocateTrainActivity.this, "LocateTrain is clicked", Toast.LENGTH_SHORT).show();

                    if (!autoCompleteTextView.getText().toString().equals(null)) {

                        //Toast.makeText(LocateTrainActivity.this, "text is " + t_no, Toast.LENGTH_SHORT).show();
                        if (!TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(message)) {

                            if (checkPermission(Manifest.permission.SEND_SMS)) {

                                try {

                                    //Intent intent = new Intent(getApplicationContext(), LocateTrainActivity.class);
                                    //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneNo, null, message, pendingIntent, null);


                                    //Toast.makeText(LocateTrainActivity.this, "Successfully sent message.", Toast.LENGTH_SHORT).show();

                                    Toast.makeText(LocateTrainActivity.this, "Please check your inbox.", Toast.LENGTH_LONG).show();


                                } catch (Exception e) {

                                    Toast.makeText(LocateTrainActivity.this, "SMS faild, please try again.", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(LocateTrainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                            }
                        }
//                        else {
//                            Toast.makeText(LocateTrainActivity.this, "Please enter a train number.", Toast.LENGTH_SHORT).show();
//                        }
                    }
//                    else {
//                        Toast.makeText(LocateTrainActivity.this, "Please enter a train number.", Toast.LENGTH_SHORT).show();
//                    }

                }

                //eitai use korte hobe // showErrorNotice("Successfully sent message.","Please check your inbox.");

            }

            //bosbe na because return type
        });

        //showErrorNotice("Successfully sent message.","Please check your inbox."); //bosbe na because of agei dekhay j sent...

    }



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


    private void showError(EditText field, int messageRes) {
        field.setError(getString(messageRes));
    }

    public void showErrorNotice(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }


}
