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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

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

        String email = user_et.getText().toString();

        if (user_et.equals("")) {
            Toast.makeText(ForgetpassActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        try {

            job.put("email", email);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/forgot_password", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("key").equals("1")) {
                        Toast.makeText(ForgetpassActivity.this, "Check Email For New Password", Toast.LENGTH_SHORT).show();
                    } else if (response.getString("key").equals("0")) {
                        Toast.makeText(ForgetpassActivity.this, "Enter correct Email", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ForgetpassActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(ForgetpassActivity.this, "error", Toast.LENGTH_SHORT).show();

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

        AppController app = new AppController(ForgetpassActivity.this);
        app.addToRequestQueue(jobjreq);




    }
}
