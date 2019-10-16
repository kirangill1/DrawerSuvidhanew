package com.example.drawersuvidha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;


public class LoginActivity extends AppCompatActivity  {
    EditText user_id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = findViewById(R.id.edittext_user);
        password =  findViewById(R.id.edittext_pass);
    }

    public void signin(View view) {

        final String user = user_id.getText().toString();
        final String pass = password.getText().toString();



        if (user.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your user id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user.length() < 4) {
            Toast.makeText(LoginActivity.this, "enter user id atleast 4 alphabets", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.length() < 2) {
            Toast.makeText(LoginActivity.this, "enter password atleast 3 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        try {
            job.put("username", user);

            job.put("password", pass);
            System.out.println(job);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://suraksha.reitindia.org/api/login_api", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("is_logged_in").equals("false")) {


                        Toast.makeText(LoginActivity.this, "check your user id and passwrod", Toast.LENGTH_SHORT).show();

                    }
                    else if (response.getString("is_logged_in").equals("true")) {
                        Toast.makeText(LoginActivity.this, "done", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();

                        sp.putString("username" , user);
                        sp.putString("pass",pass);
                        sp.putString("is_logged_in","true");
                        sp.putString("suvidha_center_id",response.getString("suvidha_center_id"));
                        sp.putString("suvidha_center_ward_no",response.getString("suvidha_center_ward_no"));
                        sp.putString("suvidha_center_contact_number",response.getString("suvidha_center_contact_number"));
                        sp.putString("suvidha_center_name_person",response.getString("suvidha_center_name_person"));
                        sp.putString("suvidha_center_email",response.getString("suvidha_center_email"));
                        sp.putString("distributor_id",response.getString("distributor_id"));
                        sp.putString("distributor_name_person",response.getString("distributor_name_person"));
                        sp.apply();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
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
                    @SuppressWarnings("ThrowablePrintedToSystemOut")
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));

        AppController app = new AppController(LoginActivity.this);
        app.addToRequestQueue(jobjreq);

    }

    public void forget(View view) {
        Intent i = new Intent(LoginActivity.this, ForgetpassActivity.class);
        startActivity(i);

    }
}