package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4;
    private DrawerLayout drawerLayout;
    @SuppressLint("StaticFieldLeak")
    public static Activity main_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_activity);

        main_activity = MainActivity.this;


        textView1 = (TextView) findViewById(R.id.customers_of_center);
        textView2 = (TextView) findViewById(R.id.monthly_customers);
        textView3 = (TextView) findViewById(R.id.weekly_customers);
        textView4 = (TextView) findViewById(R.id.daily_customers);


        get_values();
        get_values_month();
        get_values_week();
        get_values_today();

    }

    public void open_drawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void get_values() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("get_customers_count", MODE_PRIVATE);

        String textview1 = sp.getString("customers_of_center", "");

//        String textview2 = sp.getString("monthly_customers", "");
//        String textview3 = sp.getString("weekly_customers", "");
//        String textview4 = sp.getString("daily_customers", "");

        try {
            jobj.put("customers_of_center", textview1);
//            jobj.put("monthly_customers", textview2);
//            jobj.put("weekly_customers", textview3);
//            jobj.put("daily_customers", textview4);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/get_customers_count_api", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("kkkkkkkkkkkkkkkkkkkkk"+response);

                try {

                    textView1.setText(response.getString("key"));
                    /*textView2.setText(response.getString("monthly_customers"));
                    textView3.setText(response.getString("weekly_customers"));
                    textView4.setText(response.getString("daily_customers"));*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("gggggggggggggggggggggggg"+error);

            }
        });


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobreq);
    }

    public void get_values_month() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("get_customers_count", MODE_PRIVATE);

        //  String textview1 = sp.getString("customers_of_center", "");

        String textview2 = sp.getString("monthly_customers", "");
//        String textview3 = sp.getString("weekly_customers", "");
//        String textview4 = sp.getString("daily_customers", "");

        try {
            //    jobj.put("customers_of_center", textview1);
            jobj.put("monthly_customers", textview2);
//            jobj.put("weekly_customers", textview3);
//            jobj.put("daily_customers", textview4);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/get_customers_count_api_monthly", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("kkkkkkkkkkkkkkkkkkkkk"+response);

                try {

                    textView2.setText(response.getString("key"));
                    /*textView2.setText(response.getString("monthly_customers"));
                    textView3.setText(response.getString("weekly_customers"));
                    textView4.setText(response.getString("daily_customers"));*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("gggggggggggggggggggggggg"+error);

            }
        });


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobreq);
    }

    public void get_values_week() {
        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("get_customers_count", MODE_PRIVATE);

//        String textview1 = sp.getString("customers_of_center", "");
//        String textview2 = sp.getString("monthly_customers", "");
        String textview3 = sp.getString("weekly_customers", "");
//        String textview4 = sp.getString("daily_customers", "");

        try {
//            jobj.put("customers_of_center", textview1);
//            jobj.put("monthly_customers", textview2);
            jobj.put("weekly_customers", textview3);
//            jobj.put("daily_customers", textview4);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/get_customers_count_api_weekly", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("kkkkkkkkkkkkkkkkkkkkk"+response);

                try {

                    textView3.setText(response.getString("key"));
                    /*textView2.setText(response.getString("monthly_customers"));
                    textView3.setText(response.getString("weekly_customers"));
                    textView4.setText(response.getString("daily_customers"));*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("gggggggggggggggggggggggg"+error);

            }
        });


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobreq);
    }

    public void get_values_today() {

        JSONObject jobj = new JSONObject();

        SharedPreferences sp = getSharedPreferences("get_customers_count", MODE_PRIVATE);

        // String textview1 = sp.getString("customers_of_center", "");

//        String textview2 = sp.getString("monthly_customers", "");
//        String textview3 = sp.getString("weekly_customers", "");
        String textview4 = sp.getString("daily_customers", "");

        try {
            //       jobj.put("customers_of_center", textview1);
//            jobj.put("monthly_customers", textview2);
//            jobj.put("weekly_customers", textview3);
            jobj.put("daily_customers", textview4);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/get_customers_count_api_today", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("kkkkkkkkkkkkkkkkkkkkk"+response);

                try {

                    textView4.setText(response.getString("key"));
                    /*textView2.setText(response.getString("monthly_customers"));
                    textView3.setText(response.getString("weekly_customers"));
                    textView4.setText(response.getString("daily_customers"));*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("gggggggggggggggggggggggg"+error);

            }
        });


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));
        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobreq);
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
        SharedPreferences.Editor sp = getSharedPreferences("user_info", Context.MODE_PRIVATE).edit();
        sp.clear();
        sp.apply();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void view_profile(View view) {
        Intent i = new Intent(MainActivity.this  , profile_details.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }




    public void add_customer(View view) {
        Intent i = new Intent(MainActivity.this  , AddCustomerActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void show_customers(View view) {
        Intent i = new Intent(MainActivity.this  , MainActivity.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void call_logs(View view) {
        Intent i = new Intent(MainActivity.this  , Call_logs.class);
        startActivity(i);

        drawerLayout.closeDrawer(GravityCompat.START);
    }
}