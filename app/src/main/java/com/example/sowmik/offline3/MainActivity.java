package com.example.sowmik.offline3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UserListDatabase userListDatabase;

    private EditText userName,password;
    private Button loginButton;
    private TextView textView;
    private int cnt=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userListDatabase = new UserListDatabase(this);
        SQLiteDatabase sqLiteDatabase = userListDatabase.getWritableDatabase();


        userName = findViewById(R.id.usernameid);
        password = findViewById(R.id.passwordid);

        loginButton = findViewById(R.id.loginbuttonid);
        textView = findViewById(R.id.textViewid);

        textView.setText("Number of attempts remaining: 03");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userName.getText().toString();
                String pass = password.getText().toString();


                //****************###***************\\


                Boolean result = userListDatabase.findPassword(username,pass);


                if(result==true)
                {

                    Intent intent = new Intent(MainActivity.this,NavigationBarActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                    cnt--;
                    textView.setText("Number of attempts remaining: 0"+cnt);
                    if (cnt==0)
                    {
                        loginButton.setEnabled(false);
                    }
                }

            }
        });


    }

}
