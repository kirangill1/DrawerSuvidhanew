package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    public static Activity main_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_activity);

        main_activity = MainActivity.this;

    }

    public void open_drawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }




    public void shareapp(View view) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the app via play store now...";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    public void rating(View view) {
        final String appPackageName = "com.example.drawersuvidha";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


    public void about_us(View view) {
        Intent i = new Intent(MainActivity.this  , AboutusActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);


    }

    public void open_Contacus(View view) {
        Intent i = new Intent(MainActivity.this  , ContactusActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);


    }

    public void log_out(View view) {
        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void view_profile(View view) {
        Intent i = new Intent(MainActivity.this  , ViewprofileActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }


    public void statistics(View view) {
        Intent i = new Intent(MainActivity.this  , StatisticsActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void add_booking(View view) {
        Intent i = new Intent(MainActivity.this  , AddBookingActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }
}
