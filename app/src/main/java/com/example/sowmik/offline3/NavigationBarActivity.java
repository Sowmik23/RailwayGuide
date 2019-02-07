package com.example.sowmik.offline3;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Boolean check = false;

    String flag = "whatever";
    private BroadcastReceiver reciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            flag = intent.getStringExtra("set");
        }
    };

    String userName = "null",password = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter("name"));


        drawerLayout = findViewById(R.id.drawerlayoutid);

        navigationView = findViewById(R.id.nav_viewid);
        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        //to display the navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

            userName = bundle.getString("u_name");

            setmyUsername(userName);

            password = bundle.getString("u_password");

            setmyPassword(password);

        }


        if(savedInstanceState==null)
        {

            Bundle bundle1 = new Bundle();
            bundle1.putString("name",""+userName);
            bundle1.putString("pass",""+password);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();

            homeFragment.setArguments(bundle1);

            transaction.replace(R.id.fragmentcontainerframeid,homeFragment);
            transaction.commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    //new HomeFragment()).commit();

            navigationView.setCheckedItem(R.id.homeid);
        }


    }


    @Override
    public void onBackPressed() {

        //super.onBackPressed();

        if(check==true){

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }

        Toast.makeText(this, "onBackPressed is clicked. Press again to exit.", Toast.LENGTH_SHORT).show();
        check = true;


        Bundle bundle = new Bundle();
        bundle.putString("name",""+userName);
        bundle.putString("pass",""+password);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();

        homeFragment.setArguments(bundle);

        transaction.replace(R.id.fragmentcontainerframeid,homeFragment);
        transaction.commit();

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                //new HomeFragment()).commit();


        //navigationView.setCheckedItem(R.id.homeid);

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


            Bundle bundle = new Bundle();
            bundle.putString("name",""+userName);
            bundle.putString("pass",""+password);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();

            homeFragment.setArguments(bundle);

            transaction.replace(R.id.fragmentcontainerframeid,homeFragment);
            transaction.commit();

           // getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    //new HomeFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.myprofileid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Profile", Toast.LENGTH_SHORT).show();



            Bundle bundle = new Bundle();
            bundle.putString("name",""+userName);
            bundle.putString("pass",""+password);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ProfileFragment profileFragment = new ProfileFragment();

            profileFragment.setArguments(bundle);

            transaction.replace(R.id.fragmentcontainerframeid,profileFragment);
            transaction.commit();



            // getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                   // new ProfileFragment()).commit();



        }
        else if (menuItem.getItemId()==R.id.settingid)
        {

            String flagNow = Main2Activity.reminderSet;
            check = false;
            System.out.println(flagNow);
            Toast.makeText(NavigationBarActivity.this, "Settings, " + flagNow, Toast.LENGTH_SHORT).show();

            if(flagNow.equals("true")){
                Intent intent_cr=new Intent();
                intent_cr.setComponent(new ComponentName("com.example.sowmik.offline3", "com.example.sowmik.offline3.SavedReminder"));
                startActivity(intent_cr);
            }
            else{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new SettingFragment()).commit();
            }


        }
        else if (menuItem.getItemId()==R.id.helpid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Help", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new HelpFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.creditsid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Credits", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new CreditsFragment()).commit();

        }
        else if (menuItem.getItemId()==R.id.signoutid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(NavigationBarActivity.this,WelcomeActivity.class);
            startActivity(intent);

        }



        else if (menuItem.getItemId()==R.id.shareid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Share", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");
            String subject = "Application name: Railway Guide";
            String body = "This app is very useful to locate train anytime. Also you can " +
                    "select your best train for your journey and can also set an alarm " +
                    "before your journey hour.\n\n" +
                    "Play Store download link: com.example.sowmik.offline3";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(intent.createChooser(intent,"share with"));

        }

        else if (menuItem.getItemId()==R.id.feedbackid)
        {

            check = false;
            Toast.makeText(NavigationBarActivity.this, "Feedback", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainerframeid,
                    new FeedbackFragment()).commit();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setmyUsername(String u_n){

        this.userName = u_n;
    }

    public String getUserName(){

        return userName;
    }

    public void setmyPassword(String u_p){

        this.password = u_p;
    }


    public String getmyUsername(){

        return this.userName;
    }

    public String getmyPassword(){

        return this.password;
    }


}
