package com.example.sowmik.offline3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);


        drawerLayout = findViewById(R.id.drawerlayoutid);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        //to display the navigation bar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav_viewid);

        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null)
        {



            navigationView.setCheckedItem(R.id.nav_viewid);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId()==R.id.homeid)
        {

            Intent intent = new Intent(NavigationBarActivity.this,SecondActivity.class);
            startActivity(intent);

            Toast.makeText(NavigationBarActivity.this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.myprofileid)
        {

            Toast.makeText(NavigationBarActivity.this, "Profile", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.settingid)
        {

            Toast.makeText(NavigationBarActivity.this, "Setting", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.helpid)
        {

            Toast.makeText(NavigationBarActivity.this, "Help", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.signoutid)
        {

            Toast.makeText(NavigationBarActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
        }



        else if (menuItem.getItemId()==R.id.shareid)
        {

            Toast.makeText(NavigationBarActivity.this, "Share", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.feedbackid)
        {

            Toast.makeText(NavigationBarActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.messageid)
        {

            Toast.makeText(NavigationBarActivity.this, "Message", Toast.LENGTH_SHORT).show();
        }


        return true;
    }
}
