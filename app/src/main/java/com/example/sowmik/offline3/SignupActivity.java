package com.example.sowmik.offline3;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    UserListDatabase userListDatabase;

    private EditText name,email,address,password,mobile_number;
    private Button signUp;
    private AlertDialog.Builder alertDialogBuilder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        userListDatabase = new UserListDatabase(this);
        SQLiteDatabase sqLiteDatabase = userListDatabase.getWritableDatabase();



        name = findViewById(R.id.user_nameid);
        email = findViewById(R.id.email_id);
        address = findViewById(R.id.address_id);
        password = findViewById(R.id.password_id);
        mobile_number = findViewById(R.id.mobile_numberid);

        signUp = findViewById(R.id.sign_up_button_id);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _name = name.getText().toString();
                String _email = email.getText().toString();
                String _address = address.getText().toString();
                String _password = password.getText().toString();
                String _mobile_number = mobile_number.getText().toString();


                long rowId = userListDatabase.insertData(_name,_email,_address,_password,_mobile_number);
                
                if(rowId==-1)
                {
                    Toast.makeText(SignupActivity.this, "An error occur.", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(SignupActivity.this, "Data inserted in row "+rowId, Toast.LENGTH_SHORT).show();

                    //*********for showing alert dialog**********\\


                    alertDialogBuilder = new AlertDialog.Builder(SignupActivity.this);

                    //for setting title
                    alertDialogBuilder.setTitle("Successfully inserted data");

                    //for setting message
                    alertDialogBuilder.setMessage("Do you want to log in ?");
                    alertDialogBuilder.setCancelable(false);

                    //for setting icon
                    alertDialogBuilder.setIcon(R.drawable.ic_mood_successful);


                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //exit
                            //finish();

                            //*******for starting new intent********\\

                            Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(intent);


                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(SignupActivity.this, "Cancel", Toast.LENGTH_SHORT).show();

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }

            }
        });


    }
}
