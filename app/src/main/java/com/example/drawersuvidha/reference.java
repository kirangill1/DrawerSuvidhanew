package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class reference extends AppCompatActivity {
    TextView r_name,r_num,r_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        r_name=findViewById(R.id.refer_name);
        r_num=findViewById(R.id.p_num);
        r_address=findViewById(R.id.customer_address);
        r_name.setText(getIntent().getStringExtra("reference_name"));
        r_num.setText(getIntent().getStringExtra("reference_number"));
        r_address.setText(getIntent().getStringExtra("reference_address"));
    }
}
