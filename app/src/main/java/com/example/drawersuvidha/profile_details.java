package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class profile_details extends AppCompatActivity {
TextView customer_name,num,address,dist_name,ward,suvidha,adhar,pan,email,bank,acount,ifsc,mode,amount,details;
String profile_,pan_,adhar_,r_name,r_num,r_address,r_name_two,r_num_two,r_address_two;
ImageView profile_img,pan_img,adhar_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        customer_name=findViewById(R.id.customer_name);
        num=findViewById(R.id.customer_num);
        address=findViewById(R.id.customer_address);
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
        profile_img=findViewById(R.id.profile_img);
        pan_img=findViewById(R.id.pan_img);
        adhar_img=findViewById(R.id.adhar_img);
        customer_name.setText(getIntent().getStringExtra("customer_name"));
        num.setText(getIntent().getStringExtra("customer_num"));
        address.setText(getIntent().getStringExtra("address"));
        ward.setText(getIntent().getStringExtra("ward_num"));
        dist_name.setText(getIntent().getStringExtra("dist_name"));
        suvidha.setText(getIntent().getStringExtra("suvidha_num"));
        adhar.setText(getIntent().getStringExtra("adhar_num"));
        pan.setText(getIntent().getStringExtra("pan_num"));
        email.setText(getIntent().getStringExtra("email"));
        bank.setText(getIntent().getStringExtra("bank_name"));
        acount.setText(getIntent().getStringExtra("bank_num"));
        ifsc.setText(getIntent().getStringExtra("ifsc_code"));
        mode.setText(getIntent().getStringExtra("mode"));
        details.setText(getIntent().getStringExtra("pay_details"));
        amount.setText(getIntent().getStringExtra("amount"));
        pan_=getIntent().getStringExtra("pan_img");
        profile_=getIntent().getStringExtra("profile_img");
        adhar_=getIntent().getStringExtra("adhar_img");
        r_name=getIntent().getStringExtra("reference_name");
        r_num=getIntent().getStringExtra("reference_number");
        r_address=getIntent().getStringExtra("reference_address");
        r_name_two=getIntent().getStringExtra("reference_name_two");
        r_num_two=getIntent().getStringExtra("reference_number_two");
        r_address_two=getIntent().getStringExtra("reference_address_two");


        pan_img.setImageBitmap(getBitmapFromURL(pan_));
        adhar_img.setImageBitmap(getBitmapFromURL(adhar_));
        profile_img.setImageBitmap(getBitmapFromURL(profile_));



    }




    public static Bitmap getBitmapFromURL(String img) {
        try {
            Log.e("src",img);
            URL url = new URL(img);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    public void one(View view) {
        Intent i = new Intent(getBaseContext(), reference.class);
        i.putExtra("reference_name", r_name);
        i.putExtra("reference_number", r_num);
        i.putExtra("reference_address", r_address);
startActivity(i);
    }

    public void two(View view) {
        Intent i = new Intent(getBaseContext(), reference.class);
        i.putExtra("reference_name", r_name_two);
        i.putExtra("reference_number", r_num_two);
        i.putExtra("reference_address", r_address_two);
        startActivity(i);
    }
}
