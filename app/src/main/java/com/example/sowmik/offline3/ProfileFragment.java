package com.example.sowmik.offline3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileFragment extends Fragment {

    UserListDatabase userListDatabase;
    NavigationBarActivity navigationBarActivity;

    Context context;
    View view;

    String user_name;

    private TextView name,email,mobile,address;
    private Button historyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);


        name = view.findViewById(R.id.nid);
        email = view.findViewById(R.id.eid);
        mobile = view.findViewById(R.id.mid);
        address = view.findViewById(R.id.aid);

        historyButton = view.findViewById(R.id.historybuttonid);

        userListDatabase = new UserListDatabase(view.getContext());
        SQLiteDatabase sqLiteDatabase = userListDatabase.getWritableDatabase();



        Bundle bundle = getArguments();

        final String u_name = bundle.getString("name");
        String u_pass = bundle.getString("pass");



        Cursor cursor = userListDatabase.userProfile(u_name,u_pass);


        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {

            name.setText(cursor.getString(1));
            user_name = cursor.getString(1);
            email.setText(cursor.getString(2));
            address.setText(cursor.getString(3));
            mobile.setText(cursor.getString(5));

        }


        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),TravelHistoryActivity.class);
                intent.putExtra("name",user_name);

                startActivity(intent);
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.edit_profile_menu,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.edit_profile_menu_id)
        {
            Toast.makeText(getActivity(), "Edit Profile is clicked.", Toast.LENGTH_SHORT).show();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
