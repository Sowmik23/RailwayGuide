package com.example.sowmik.offline3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class NavigationBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);


        drawerLayout = findViewById(R.id.drawerlayoutid);

        navigationView = findViewById(R.id.nav_viewid);
        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        //to display the navigation bar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new HomeFragment()).commit();


            navigationView.setCheckedItem(R.id.homeid);
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

            Toast.makeText(NavigationBarActivity.this, "Home", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new HomeFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.myprofileid)
        {

            Toast.makeText(NavigationBarActivity.this, "Profile", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new ProfileFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.settingid)
        {

            Toast.makeText(NavigationBarActivity.this, "Setting", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId()==R.id.helpid)
        {

            Toast.makeText(NavigationBarActivity.this, "Help", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new HelpFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.creditsid)
        {

            Toast.makeText(NavigationBarActivity.this, "Credits", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new CreditsFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.signoutid)
        {

            Toast.makeText(NavigationBarActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
        }



        else if (menuItem.getItemId()==R.id.shareid)
        {

            Toast.makeText(NavigationBarActivity.this, "Share", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");
            String subject = "My application lab app";
            String body = "This app is very useful to buy train ticket in online\n you can also easily locate any train easily by\nthis app.";
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(intent.createChooser(intent,"share with"));

        }

        else if (menuItem.getItemId()==R.id.feedbackid)
        {

            Toast.makeText(NavigationBarActivity.this, "Feedback", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new FeedbackFragment()).commit();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}
