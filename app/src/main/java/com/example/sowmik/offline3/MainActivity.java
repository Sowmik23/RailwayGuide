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

                Cursor cursor = userListDatabase.checkUser(username);

                if (cursor.getCount()==0){

                    //there is no data in the Accounts table
                    //Toast.makeText(MainActivity.this, "Please create an account first.", Toast.LENGTH_SHORT).show();

                    showData("Error","No data found");

                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext())
                {
                    stringBuffer.append("User name: "+cursor.getString(0)+"\n");
                    stringBuffer.append("Email: "+cursor.getString(1)+"\n");
                    stringBuffer.append("Address: "+cursor.getString(2)+"\n");
                    stringBuffer.append("Password: "+cursor.getString(3)+"\n");
                    stringBuffer.append("Mobile Number: "+cursor.getString(4)+"\n\n\n");
                }

                showData("ResultSet",stringBuffer.toString());


//                if(username.equals("admin") && pass.endsWith("1234"))
//                {
//
//                    //Intent intent = new Intent(MainActivity.this,NavigationBarActivity.class);
//                    //startActivity(intent);
//
//                }
//                else
//                {
//                    cnt--;
//                    textView.setText("Number of attempts remaining: 0"+cnt);
//                    if (cnt==0)
//                    {
//                        loginButton.setEnabled(false);
//                    }
//                }

            }
        });


    }


    public void showData(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();

    }


}
