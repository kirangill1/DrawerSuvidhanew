package com.example.drawersuvidha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewprofileActivity extends AppCompatActivity {
    JSONArray jsonArray;
String user,pass;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);


        get_details();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
    user=sp.getString("email","");
    pass=sp.getString("pass","");
    }

    private void get_details() {

        JSONObject jObject = new JSONObject();
        try {

            jObject.put("username",user);
            jObject.put("password",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://suraksha.reitindia.org/customers/get_details",jObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

              /*  try {*/
                   /* i.putExtra("name", response.getString("customers_name"));


                    i.putExtra("customer_num", response.getString("customers_contact"));
                    i.putExtra("address", response.getString("customers_address"));
                    i.putExtra("ward_num", response.getString("customers_ward_no"));
                    i.putExtra("dist_name", response.getString("customers_distributor_id"));
                    i.putExtra("suvidha_num", response.getString("customers_suvidha_center_id"));
                    i.putExtra("adhar_num", response.getString("customers_aadhar"));
                    i.putExtra("pan_num", response.getString("customers_pan_no"));
                    i.putExtra("email", response.getString("customers_email"));
                    i.putExtra("bank_name", response.getString("customers_bank_name"));
                    i.putExtra("bank_num", response.getString("customers_bank_account_no"));
                    i.putExtra("ifsc_code", response.getString("customers_ifsc_code"));
                    i.putExtra("mode", response.getString("customers_mode_of_payment"));
                    i.putExtra("pay_details", response.getString("customers_payment_details"));
                    i.putExtra("amount", response.getString("customers_bank_charges"));
                    i.putExtra("reference_name", response.getString("customers_ref_id"));
                        *//*    i.putExtra("reference_number", jsonObject.getString("reference_number"));
                            i.putExtra("reference_address", jsonObject.getString("reference_address"));
                            i.putExtra("reference_name_two", jsonObject.getString("reference_name_two"));
                            i.putExtra("reference_number_two", jsonObject.getString("reference_number_two"));
                            i.putExtra("reference_address_two", jsonObject.getString("reference_address_two"));

                            i.putExtra("adhar_img", jsonObject.getString("adhar_img"));
                            i.putExtra("pan_img", jsonObject.getString("pan_img"));
                            i.putExtra("profile_img", jsonObject.getString("profile_img"));*//*

                } catch (JSONException e) {
                    e.printStackTrace();

                }
*/
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);


            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        Volley.newRequestQueue(getBaseContext()).add(jsonObjectRequest);
    }
}
