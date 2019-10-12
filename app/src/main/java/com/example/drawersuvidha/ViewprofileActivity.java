package com.example.drawersuvidha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
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

        recyclerView = findViewById(R.id.view_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        System.out.println("2222222222222222222222222222222222222222222222");

        get_cutomers();

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
    user=sp.getString("email","");
    pass=sp.getString("pass","");

    }

    private void get_cutomers() {

        JSONObject jObject = new JSONObject();
        try {
            jObject.put("is_logged_in","true");
            jObject.put("username",user);
            jObject.put("password",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+jObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://suraksha.reitindia.org/customers/get_customers",jObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111"+response);

                System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

                Adapter adapter = new Adapter();
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+error);


            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        Volley.newRequestQueue(getBaseContext()).add(jsonObjectRequest);
    }
    private class Adapter extends RecyclerView.Adapter<view_holder> {


        @NonNull
        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cell, parent, false));

        }

        @Override
        public void onBindViewHolder(final view_holder holder, int position) {

            try {
                final JSONObject jsonObject = (JSONObject) jsonArray.get(position);
                holder.cust_name.setText(jsonObject.getString("customer_name"));
                holder.cust_num.setText(jsonObject.getString("customer_num"));

                holder.view_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), profile_details.class);

                        try {

                            i.putExtra("name", jsonObject.getString("customers_name"));
                            i.putExtra("customer_num", jsonObject.getString("customers_contact"));
                            i.putExtra("address", jsonObject.getString("customers_address"));
                            i.putExtra("ward_num", jsonObject.getString("customers_ward_no"));
                            i.putExtra("dist_name", jsonObject.getString("customers_distributor_id"));
                            i.putExtra("suvidha_num", jsonObject.getString("customers_suvidha_center_id"));
                            i.putExtra("adhar_num", jsonObject.getString("customers_aadhar"));
                            i.putExtra("pan_num", jsonObject.getString("customers_pan_no"));
                            i.putExtra("email", jsonObject.getString("customers_email"));
                            i.putExtra("bank_name", jsonObject.getString("customers_bank_name"));
                            i.putExtra("bank_num", jsonObject.getString("customers_bank_account_no"));
                            i.putExtra("ifsc_code", jsonObject.getString("customers_ifsc_code"));
                            i.putExtra("mode", jsonObject.getString("customers_mode_of_payment"));
                            i.putExtra("pay_details", jsonObject.getString("customers_payment_details"));
                            i.putExtra("amount", jsonObject.getString("customers_bank_charges"));
                            i.putExtra("reference_name", jsonObject.getString("customers_ref_id"));
                        /*    i.putExtra("reference_number", jsonObject.getString("reference_number"));
                            i.putExtra("reference_address", jsonObject.getString("reference_address"));
                            i.putExtra("reference_name_two", jsonObject.getString("reference_name_two"));
                            i.putExtra("reference_number_two", jsonObject.getString("reference_number_two"));
                            i.putExtra("reference_address_two", jsonObject.getString("reference_address_two"));

                            i.putExtra("adhar_img", jsonObject.getString("adhar_img"));
                            i.putExtra("pan_img", jsonObject.getString("pan_img"));
                            i.putExtra("profile_img", jsonObject.getString("profile_img"));*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(i);

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return jsonArray.length();
        }

    }


    private class view_holder extends RecyclerView.ViewHolder {

        TextView cust_num, cust_name ;
        Button view_details;

        view_holder(View itemView) {
            super(itemView);

            cust_num = itemView.findViewById(R.id.customer_num);
            view_details = itemView.findViewById(R.id.view_details);
            cust_name = itemView.findViewById(R.id.customer_name);
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        get_cutomers();
    }
}
