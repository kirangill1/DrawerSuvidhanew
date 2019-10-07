package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticsActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        textView1 = (TextView) findViewById(R.id.customers);
        textView2 = (TextView) findViewById(R.id.suvidha_centre);
        textView3 = (TextView) findViewById(R.id.distributor);
        textView4 = (TextView) findViewById(R.id.super_distributor);


        get_values();
    }

    public void get_values() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("statistics", MODE_PRIVATE);

        String textview1 = sp.getString("customers", "");

        String textview2 = sp.getString("suvidha_centre", "");
        String textview3 = sp.getString("distributor", "");
        String textview4 = sp.getString("super_distributor", "");

        try {
            jobj.put("customers", textview1);
            jobj.put("suvidha_centre", textview2);
            jobj.put("distributor", textview3);
            jobj.put("super_distributor", textview4);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr = response.getJSONArray("result");

                    JSONObject job_box = (JSONObject) jarr.get(0);
                    textView1.setText(job_box.getString("customers"));
                    textView2.setText(job_box.getString("suvidha_centre"));
                    textView3.setText(job_box.getString("distributor"));
                    textView4.setText(job_box.getString("super_distributor"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(StatisticsActivity.this);
        app.addToRequestQueue(jobreq);
    }
}