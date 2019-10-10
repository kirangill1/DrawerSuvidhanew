package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        Handler h =new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
if (sp.getString("is_logged_in","").toString().equals("")) {
    Intent i = new Intent(getBaseContext(), LoginActivity.class);
    startActivity(i);
    finish();
}
else {
    Intent i = new Intent(getBaseContext(), MainActivity.class);
    startActivity(i);
    finish();
}
            }
        },1000);
    }
}
