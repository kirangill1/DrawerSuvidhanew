package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class profile_details extends AppCompatActivity {
TextView user_tv,suvidha_tv ,ward_tv , contact_tv,email_tv,customer_tv,distributor_id, distributor_tv ;
/*String profile_,pan_,adhar_,r_name,r_num,r_address,r_name_two,r_num_two,r_address_two;
ImageView profile_img,pan_img,adhar_img;
LinearLayout suvidha_details;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        user_tv = (TextView) findViewById(R.id.user_id);
        suvidha_tv = (TextView) findViewById(R.id.suvidha_center_name);
        ward_tv = (TextView) findViewById(R.id.ward_no);
        contact_tv = (TextView) findViewById(R.id.contact_no);
        email_tv = (TextView) findViewById(R.id.suvidha_center_email);
        /*customer_tv = (TextView) findViewById(R.id.customer_name);*/
        distributor_id = (TextView) findViewById(R.id.distributor_id);
        distributor_tv = (TextView) findViewById(R.id.distributor_name);

        /*customer_name=findViewById(R.id.customer_name);
        num=findViewById(R.id.customer_num);
        role=findViewById(R.id.customer_role);
        ward=findViewById(R.id.ward_num);
        suvidha=findViewById(R.id.suvidha_num);
        adhar=findViewById(R.id.adhar_num);
        pan=findViewById(R.id.pan_num);
        email=findViewById(R.id.email);
        bank=findViewById(R.id.bank_name);
        acount=findViewById(R.id.bank_account);
        ifsc=findViewById(R.id.ifsc_code);
        mode=findViewById(R.id.mode_of_payment);
        amount=findViewById(R.id.Amount);
        details=findViewById(R.id.payment_details);
        dist_name=findViewById(R.id.dist_name);
        pan_img=findViewById(R.id.pan_img);
        adhar_img=findViewById(R.id.adhar_img);*/

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        user_tv.setText(sp.getString("username",""));
        suvidha_tv.setText(sp.getString("suvidha_center_name_person",""));
        ward_tv.setText(sp.getString("suvidha_center_ward_no",""));
        contact_tv.setText(sp.getString("suvidha_center_contact_number",""));
        email_tv.setText(sp.getString("suvidha_center_email",""));
        /*customer_tv.setText(sp.getString("customer_name",""));*/
        distributor_id.setText(sp.getString("distributor_id",""));
        distributor_tv.setText(sp.getString("distributor_name_person",""));



        /*adhar.setText(getIntent().getStringExtra("adhar_num"));
        pan.setText(getIntent().getStringExtra("pan_num"));
        bank.setText(getIntent().getStringExtra("bank_name"));
        acount.setText(getIntent().getStringExtra("bank_num"));
        ifsc.setText(getIntent().getStringExtra("ifsc_code"));
        mode.setText(getIntent().getStringExtra("mode"));
        details.setText(getIntent().getStringExtra("pay_details"));
        amount.setText(getIntent().getStringExtra("amount"));
        pan_=getIntent().getStringExtra("pan_img");
        profile_=getIntent().getStringExtra("profile_img");
        adhar_=getIntent().getStringExtra("adhar_img");*/
    }

}
