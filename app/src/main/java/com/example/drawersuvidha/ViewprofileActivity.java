package com.example.drawersuvidha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        recyclerView =findViewById(R.id.view_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext() ,LinearLayoutManager.VERTICAL, false));
    get_cutomers();
    }

    private void get_cutomers() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("module", "get_customer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    jsonArray = response.getJSONArray("result");
                    Adapter adapter = new Adapter();
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        Volley.newRequestQueue(getBaseContext()).add(jsonObjectRequest);
    }
    private class Adapter extends RecyclerView.Adapter<view_holder> {


        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cell, parent, false));

        }

        @Override
        public void onBindViewHolder(final view_holder holder, int position) {

            try {
                final JSONObject jsonObject = jsonArray.getJSONObject(position);
                holder.cust_name.setText(jsonObject.getString("customer_name"));
                holder.cust_num.setText(jsonObject.getString("customer_num"));

                holder.view_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), profile_details.class);

                        try {
                            i.putExtra("name", jsonObject.getString("customer_name"));
                            i.putExtra("customer_num", jsonObject.getString("customer_num"));
                            i.putExtra("address", jsonObject.getString("address"));
                            i.putExtra("ward_num", jsonObject.getString("ward_num"));
                            i.putExtra("dist_name", jsonObject.getString("dist_name"));
                            i.putExtra("suvidha_num", jsonObject.getString("suvidha_num"));
                            i.putExtra("adhar_num", jsonObject.getString("adhar_num"));
                            i.putExtra("pan_num", jsonObject.getString("pan_num"));
                            i.putExtra("email", jsonObject.getString("email"));
                            i.putExtra("bank_name", jsonObject.getString("bank_name"));
                            i.putExtra("bank_num", jsonObject.getString("bank_num"));
                            i.putExtra("ifsc_code", jsonObject.getString("ifsc_code"));
                            i.putExtra("mode", jsonObject.getString("mode"));
                            i.putExtra("pay_details", jsonObject.getString("pay_details"));
                            i.putExtra("amount", jsonObject.getString("amount"));

                            i.putExtra("adhar_img", jsonObject.getString("adhar_img"));
                            i.putExtra("pan_img", jsonObject.getString("pan_img"));
                            i.putExtra("profile_img", jsonObject.getString("profile_img"));

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

        public view_holder(View itemView) {
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
