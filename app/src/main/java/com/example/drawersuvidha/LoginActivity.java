package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText user_id, password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id = (EditText) findViewById(R.id.edittext_user);
        password = (EditText) findViewById(R.id.edittext_pass);
    }
    public void signin(View view) {

        final String user = user_id.getText().toString();
        final String pass = password.getText().toString();


        if (user.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your user id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user.length() < 5) {
            Toast.makeText(LoginActivity.this, "enter user id atleast 5 alphabets", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.length() < 2) {
            Toast.makeText(LoginActivity.this, "enter password atleast three digit", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        try {
            job.put("username", user_id);
            job.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest(" http://reitindia.org/suraksha/login_user/login", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("key").equals("false")) {
                        Toast.makeText(LoginActivity.this, "check your user id", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.getString("key").equals("true")) {
                        Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();

                        sp.putString("username" , user);
                        sp.putString("password",pass);

                        sp.commit();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                {
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));

        AppController app = new AppController(LoginActivity.this);
        app.addToRequestQueue(jobjreq);

    }


}
