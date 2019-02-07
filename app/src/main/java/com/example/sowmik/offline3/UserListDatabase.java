package com.example.sowmik.offline3;

import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.sql.SQLOutput;

public class UserListDatabase extends SQLiteOpenHelper {


    String user_Sowmik ;

    private Context context;

    private static final String DATABASE_NAME = "userdetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final int VERSION_NUMBER = 4;

    private static final String ID = "id";
    private static final String USER_NAME = "name";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String PASSWORD = "password";
    private static final String MOBILE_NUMBER = "mobile_number";

    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + USER_NAME + " VARCHAR(50) NOT NULL , " + EMAIL + " TEXT NOT NULL ,"+ADDRESS+" TEXT NOT NULL ," + PASSWORD + " TEXT NOT NULL ," + MOBILE_NUMBER + " TEXT NOT NULL )";


    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;





    private static final String HISTORY_TABLE_NAME = "user_history";

    private static final String HISTORY_ID = "history_id";
    private static final String NAME = "user_name";
    private static final String DEPARTURE = "departure";
    private static final String ARRIVAL = "arrival";
    private static final String JOURNEY_DATE = "j_date";

    private static final String CREATE_HISTORY_TABLE = " CREATE TABLE " + HISTORY_TABLE_NAME + " (" + HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + NAME + " VARCHAR(50) , " + DEPARTURE + " TEXT ,"+ARRIVAL+" TEXT," + JOURNEY_DATE + " TEXT)";
    private static final String DROP_HISTORY_TABLE = " DROP TABLE IF EXISTS " + HISTORY_TABLE_NAME;






    public UserListDatabase(Context context ) {

        super(context, DATABASE_NAME , null, VERSION_NUMBER);
        this.context =  context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            Toast.makeText(context, "onCreate is called.", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_HISTORY_TABLE);


        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Toast.makeText(context, "onUpgrade is called.", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            db.execSQL(DROP_HISTORY_TABLE);
            onCreate(db);

        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }


    public long insertData(UserDetails userDetails){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(ADDRESS,userDetails.getAddress());
        contentValues.put(PASSWORD,userDetails.getPassword());
        contentValues.put(MOBILE_NUMBER,userDetails.getMobile_number());

        long rowId = sqLiteDatabase.insert(TABLE_NAME ,null,contentValues);
        return rowId;

    }

    public Boolean findPassword(String uname,String pas){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME ,null);

        Boolean result = false;

        if (cursor.getCount()==0)
        {
            Toast.makeText(context, "No data found.", Toast.LENGTH_SHORT).show();
        }
        else {

            while (cursor.moveToNext()){
                String username = cursor.getString(1);
                String password = cursor.getString(4);

                if (username.equals(uname) && password.equals(pas)){

                    this.user_Sowmik = uname;
                    result = true;
                    break;
                }
            }

        }

        return result;
    }


    public Cursor userProfile(String uname,String pas){


        final String str1 = uname;
        final String str2 = pas;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE ( " + USER_NAME + " = '" + str1 + "' AND " + PASSWORD + " = '" + str2 + "' )  ",null);

        return cursor;
    }


    public long insertUserTravelHistory(String name,String dep, String arr,String j_date){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String u_name = this.user_Sowmik;

        contentValues.put(NAME,name);
        contentValues.put(DEPARTURE,dep);
        contentValues.put(ARRIVAL,arr);
        contentValues.put(JOURNEY_DATE,j_date);

        long rowId = sqLiteDatabase.insert(HISTORY_TABLE_NAME ,null,contentValues);
        return rowId;

    }


    public Cursor SearchUserHistory(final String u_name){

        final String str1 = u_name;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + HISTORY_TABLE_NAME + " WHERE  ( " + NAME + " = '"+str1+"' ) ",null);

        return cursor;

    }




}
