package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangepasswordActivity extends AppCompatActivity {
    EditText newpass_et, confirmpass_et;
    Button button;
String user_id;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        newpass_et = (EditText) findViewById(R.id.edit1);
        confirmpass_et = (EditText) findViewById(R.id.edit2);
         user_id=getIntent().getStringExtra("user_id");
        button = (Button) findViewById(R.id.register);
    }


    public void change_pass(View view) {


        String new_pass = newpass_et.getText().toString();
        String confirm_pass = confirmpass_et.getText().toString();


        if (new_pass.equals("")) {
            Toast.makeText(ChangepasswordActivity.this, "enter the new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirm_pass.equals("")) {
            Toast.makeText(ChangepasswordActivity.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!new_pass.equals(confirm_pass)) {
            Toast.makeText(ChangepasswordActivity.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        try {
            job.put("new_pass", new_pass);
            job.put("username", user_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://suraksha.reitindia.org/login_user/login", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("key").equals("1")) {
                        Toast.makeText(ChangepasswordActivity.this, "Password Changed              ", Toast.LENGTH_SHORT).show();
                    } else if (response.getString("key").equals("0")) {
                        Toast.makeText(ChangepasswordActivity.this, "error", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ChangepasswordActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(ChangepasswordActivity.this, "error", Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(ChangepasswordActivity.this);
        app.addToRequestQueue(jobjreq);
    }
}
