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
TextView customer_name,num,role,dist_name,ward,suvidha,adhar,pan,email,bank,acount,ifsc,mode,amount,details;
String profile_,pan_,adhar_,r_name,r_num,r_address,r_name_two,r_num_two,r_address_two;
ImageView profile_img,pan_img,adhar_img;

LinearLayout suvidha_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        customer_name=findViewById(R.id.customer_name);
        num=findViewById(R.id.customer_num);
        role=findViewById(R.id.customer_role);
       /* ward=findViewById(R.id.ward_num);
        suvidha=findViewById(R.id.suvidha_num);
        adhar=findViewById(R.id.adhar_num);
        pan=findViewById(R.id.pan_num);*/
        email=findViewById(R.id.email);
      /*  bank=findViewById(R.id.bank_name);
        acount=findViewById(R.id.bank_account);
        ifsc=findViewById(R.id.ifsc_code);
        mode=findViewById(R.id.mode_of_payment);
        amount=findViewById(R.id.Amount);
        details=findViewById(R.id.payment_details);
        dist_name=findViewById(R.id.dist_name);

        pan_img=findViewById(R.id.pan_img);
        adhar_img=findViewById(R.id.adhar_img);*/

        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        suvidha_details=findViewById(R.id.suvidha_center_details);
        customer_name.setText(sp.getString("pcr_admin_name",""));
        num.setText(sp.getString("pcr_admin_contact",""));
        role.setText(sp.getString("pcr_admin_role",""));
     /*   ward.setText(getIntent().getStringExtra("ward_num"));
        dist_name.setText(getIntent().getStringExtra("dist_name"));
        suvidha.setText(getIntent().getStringExtra("suvidha_num"));*/
        /*adhar.setText(getIntent().getStringExtra("adhar_num"));
        pan.setText(getIntent().getStringExtra("pan_num"));*/
        email.setText(sp.getString("pcr_admin_email",""));
       /* bank.setText(getIntent().getStringExtra("bank_name"));
        acount.setText(getIntent().getStringExtra("bank_num"));
        ifsc.setText(getIntent().getStringExtra("ifsc_code"));
        mode.setText(getIntent().getStringExtra("mode"));
        details.setText(getIntent().getStringExtra("pay_details"));
        amount.setText(getIntent().getStringExtra("amount"));*/
       /* pan_=getIntent().getStringExtra("pan_img");
        profile_=getIntent().getStringExtra("profile_img");
        adhar_=getIntent().getStringExtra("adhar_img");*/
      /*  r_name=getIntent().getStringExtra("reference_name");*/
       /* r_num=getIntent().getStringExtra("reference_number");
        r_address=getIntent().getStringExtra("reference_address");
        r_name_two=getIntent().getStringExtra("reference_name_two");
        r_num_two=getIntent().getStringExtra("reference_number_two");
        r_address_two=getIntent().getStringExtra("reference_address_two");*/


      /*  pan_img.setImageBitmap(getBitmapFromURL(pan_));
        adhar_img.setImageBitmap(getBitmapFromURL(adhar_));
        profile_img.setImageBitmap(getBitmapFromURL(profile_));
*/


    }




   /* public static Bitmap getBitmapFromURL(String img) {
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        InputStream image=new ByteArrayInputStream(decodedString);
        return BitmapFactory.decodeStream(image);
    }*/



    public void suvidha_details(View view) {
        suvidha_details.setVisibility(View.VISIBLE);
    }
}
