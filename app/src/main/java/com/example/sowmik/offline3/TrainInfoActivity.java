package com.example.sowmik.offline3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class TrainInfoActivity extends AppCompatActivity {


    //DbHelper dbHelper;

    //private EditText trainName,trainNumber;
    private Button search1,search2;

    private AutoCompleteTextView autoCompleteTextViewName,autoCompleteTextViewNumber;
    private String[] trainNames,trainNumbers;

    String user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //dbHelper = new DbHelper(this);
        //SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        //trainName = findViewById(R.id.train_nameId);
        //trainNumber = findViewById(R.id.train_numberId);

        search1 = findViewById(R.id.search_button1id);
        search2 = findViewById(R.id.search_button2id);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {

            user_name = bundle.getString("name");
        }


        autoCompleteTextViewName = findViewById(R.id.train_nameId);
        trainNames = getResources().getStringArray(R.array.train_names);

        autoCompleteTextViewNumber = findViewById(R.id.train_numberId);
        trainNumbers = getResources().getStringArray(R.array.train_numbers);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trainNames);
        autoCompleteTextViewName.setAdapter(adapter);
        autoCompleteTextViewName.setThreshold(1);

        //1 ta character er sathe match sob string e dekhabe ei 1 dile r 2 dile 2 ta match ala string dekhabe

        autoCompleteTextViewName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = autoCompleteTextViewName.getText().toString();


                Toast.makeText(TrainInfoActivity.this, ""+value, Toast.LENGTH_SHORT).show();

            }
        });

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String t_name = autoCompleteTextViewName.getText().toString();

                if (t_name.isEmpty()) {

                    showError(autoCompleteTextViewName, R.string.required1);

                }
                else {

//                    Cursor cursor = dbHelper.SearchByName(t_name);
//
//                    if (cursor.getCount() == 0) {
//
//                        showSearchData("Sorry!", "No train found");
//                        return;
//                    }
//                    else {

                    Intent intent = new Intent(TrainInfoActivity.this,SameTrainNameList.class);

                    intent.putExtra("train_name",""+t_name);
                    intent.putExtra("u_name",user_name);
                    startActivity(intent);
                    // }

                }

            }

        });


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trainNumbers);
        autoCompleteTextViewNumber.setAdapter(adapter2);
        autoCompleteTextViewNumber.setThreshold(1);

        //1 ta character er sathe match sob string e dekhabe ei 1 dile r 2 dile 2 ta match ala string dekhabe

        autoCompleteTextViewNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = autoCompleteTextViewNumber.getText().toString();


                Toast.makeText(TrainInfoActivity.this, ""+value, Toast.LENGTH_SHORT).show();

            }
        });




        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String t_Number = autoCompleteTextViewNumber.getText().toString();

                if (t_Number.isEmpty()) {

                    showError(autoCompleteTextViewNumber, R.string.required2);

                }
                else {

//                    Cursor cursor = dbHelper.SearchByNumber(t_Number);
//
//                    if (cursor.getCount() == 0) {
//
//                        showSearchData("Sorry!", "No train found");
//                        return;
//                    }
//                    else
//                    {
                    Intent intent = new Intent(TrainInfoActivity.this,TrainDetails.class);

                    intent.putExtra("t_number","" + t_Number);
                    intent.putExtra("u_name",user_name);

                    startActivity(intent);

                    //}


                }

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.add_train,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.addtrainid){

            Intent intent = new Intent(TrainInfoActivity.this,AddNewTrain.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void showError(EditText field, int messageRes) {

        field.setError(getString(messageRes));
    }


//    public void showSearchData(String title,String message){
//
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setCancelable(true);
//        builder.show();
//    }


}

