package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetpassActivity extends AppCompatActivity {
 EditText user_et;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        user_et = (EditText) findViewById(R.id.edit1);
        button = (Button) findViewById(R.id.register);


    }

    public void next(View view) {

        String user_id = user_et.getText().toString();

        if (user_et.equals("")) {
            Toast.makeText(ForgetpassActivity.this, "enter the username", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent i = new Intent(ForgetpassActivity.this,ChangepasswordActivity.class);
        i.putExtra("user_id",user_id);
        startActivity(i);
        finish();
    }
}
