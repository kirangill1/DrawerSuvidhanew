package com.example.drawersuvidha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
*/

import static android.view.View.Z;

public class AddCustomerActivity extends AppCompatActivity {
    TextView suvidha_tv , distributor_tv , ward_tv , contact_tv;
    EditText  add_et,   name_et, adhaar_et, pan_et, email_et ,app_et;
    EditText bankname_et , bankaccount_et , ifsccode_et , amount_et , payment_et ;
    EditText refer_name1 , refer_contact1 , refer_email1;
    EditText refer_name2 , refer_contact2 ,refer_email2 ;
    RadioButton dd , cheque , yes , no ,yess, noo ;
    LinearLayout bank_detail;
    Button button;
    //String loc = "";


    public static ImageView profile_photo;
    public static ImageView adhaar_card;
    public static ImageView pan_card;
    public static ImageView ptp_image;

    public static String profile_photo_string;
    public static String adhaar_card_string;
    public static String pan_card_string;
    public static String ptp_image_string;

    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_IMAGE_REQUEST1 = 2;
    private int PICK_IMAGE_REQUEST2 = 3;
    private int PICK_IMAGE_REQUEST3 = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);

        suvidha_tv = (TextView) findViewById(R.id.suvidha_center_name);
        distributor_tv = (TextView) findViewById(R.id.distributor_name);
        ward_tv = (TextView) findViewById(R.id.ward_no);
        contact_tv = (TextView) findViewById(R.id.contact_no);
        name_et = (EditText) findViewById(R.id.name_et);
        add_et = (EditText) findViewById(R.id.add_et);
        app_et =(EditText)findViewById(R.id.app_et) ;
        adhaar_et = (EditText) findViewById(R.id.adhaar_et);
        pan_et = (EditText) findViewById(R.id.pan_et);
        email_et = (EditText) findViewById(R.id.email_et);
        profile_photo = (ImageView) findViewById(R.id.img);
        adhaar_card = (ImageView) findViewById(R.id.adhaar);
        pan_card = (ImageView) findViewById(R.id.pan);
        ptp_image = (ImageView) findViewById(R.id.ptp_image);
        bank_detail=findViewById(R.id.bank_detail);

        bankname_et= (EditText)findViewById(R.id.bank_name);
        bankaccount_et= (EditText)findViewById(R.id.bank_account);
        ifsccode_et= (EditText)findViewById(R.id.ifsc_code);
        amount_et= (EditText)findViewById(R.id.amount);
        payment_et= (EditText)findViewById(R.id.payment);

        refer_name1 =(EditText)findViewById(R.id.refer_name1);
        refer_contact1 =(EditText)findViewById(R.id.refer_contact1);
        refer_email1 =(EditText)findViewById(R.id.refer_email1);
        refer_name2 =(EditText)findViewById(R.id.refer_name2);
        refer_contact2 =(EditText)findViewById(R.id.refer_contact2);
        refer_email2 =(EditText)findViewById(R.id.refer_email2);

        cheque= (RadioButton)findViewById(R.id.cheque);
        dd= (RadioButton)findViewById(R.id.dd);
        yes= (RadioButton)findViewById(R.id.yes);
        yess= (RadioButton)findViewById(R.id.yess);
        no= (RadioButton)findViewById(R.id.no);
        noo= (RadioButton)findViewById(R.id.noo);


        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);

        suvidha_tv.setText(sp.getString("suvidha_center_name_person",""));
        distributor_tv.setText(sp.getString("distributor_name_person",""));
        ward_tv.setText(sp.getString("suvidha_center_ward_no",""));
        contact_tv.setText(sp.getString("suvidha_center_contact_number",""));


       /* button=(Button)findViewById(R.id.submit);

        button.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{

                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {

                }
            }
        });*/
    }




   /* // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values
        String name = name_et.getText().toString();
        String email = email_et.getText().toString();
        String address = add_et.getText().toString();
        String application = app_et.getText().toString();

        String pan= pan_et.getText().toString().trim();
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher matcher = pattern .matcher(pan);



        String adhaar = adhaar_et.getText().toString();
        String bankname = bankname_et.getText().toString();
        String bankaccount = bankaccount_et.getText().toString();


        String ifsccode= ifsccode_et.getText().toString();
        String patternn = "[A-Z]{4}[0][A-Z0-9]{6}";



        String amount = amount_et.getText().toString();
        String payment = payment_et.getText().toString();
        String refername_1 = refer_name1.getText().toString();
        String refercontact_1 = refer_contact1.getText().toString();
        String referemail_1 = refer_email1.getText().toString();
        String refername_2 = refer_name2.getText().toString();
        String refercontact_2 = refer_contact2.getText().toString();
        String referemail_2 = refer_email2.getText().toString();

        // String address = loc;


        Boolean dd_rb = dd.isChecked();
        Boolean cheque_rb = cheque.isChecked();
        Boolean yes_rb = yes.isChecked();
        Boolean no_rb = no.isChecked();

        Boolean yess_rb = yess.isChecked();
        Boolean noo_rb = noo.isChecked();


        if (application.equals("")) {
        Toast.makeText(AddCustomerActivity.this, "enter the application no. ", Toast.LENGTH_SHORT).show();
        return;
    }
        if (name.length() < 3) {
        Toast.makeText(AddCustomerActivity.this, "customer name should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
        return;
    }

        if (address.equals("")) {
        Toast.makeText(AddCustomerActivity.this, "enter the address", Toast.LENGTH_SHORT).show();
        return;
        }
        if (adhaar.length() < 12|| adhaar.length()>12) {
        Toast.makeText(AddCustomerActivity.this, "please check your adhaar card no.", Toast.LENGTH_SHORT).show();
        return;
        }


        if (!matcher .matches())
        {
        Toast.makeText(getApplicationContext(), pan+" is not valid pan no.",
        Toast.LENGTH_LONG).show();
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Toast.makeText(AddCustomerActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
        return;
        }


        if (bankname.equals("")) {
        Toast.makeText(AddCustomerActivity.this, "enter the name", Toast.LENGTH_SHORT).show();
        return;
        }

        if (bankaccount.length() <19 || bankaccount.length()>8  ) {
        Toast.makeText(AddCustomerActivity.this, " account no. should be between 8-19 digits", Toast.LENGTH_SHORT).show();
         return;
        }
        if (!ifsccode.matches(patternn) ||ifsccode.length()< 11 || ifsccode.length() > 11) {
        Toast.makeText(AddCustomerActivity.this, " ifsc code is not valid", Toast.LENGTH_SHORT).show();
        return;
        }




        if (!dd_rb&&!cheque_rb){
        Toast.makeText(AddCustomerActivity.this, "select mode of payment", Toast.LENGTH_SHORT).show();
        return;
        }
        if (!yess_rb&&!noo_rb){
        Toast.makeText(AddCustomerActivity.this, "ECS signed or not?", Toast.LENGTH_SHORT).show();
        return;
        }
        if (amount.equals("")) {
        Toast.makeText(AddCustomerActivity.this, " amount  should  be greater than 0", Toast.LENGTH_SHORT).show();
        return;
        }

        if (payment.equals("")) {
        Toast.makeText(AddCustomerActivity.this, "enter the payment details", Toast.LENGTH_SHORT).show();
        return;
        }

        if (refername_1.length() < 3) {
        Toast.makeText(AddCustomerActivity.this, "name for first reference should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
        return;
        }
        if (refercontact_1.length() < 10) {
        Toast.makeText(AddCustomerActivity.this, "re-enter the contact for first reference ", Toast.LENGTH_SHORT).show();
        return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(referemail_1).matches()) {
        Toast.makeText(AddCustomerActivity.this, "enter valid email for first reference", Toast.LENGTH_SHORT).show();
        return;
        }

        if (refername_2.length() < 3) {
        Toast.makeText(AddCustomerActivity.this, "name for second reference should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
        return;
        }
        if (refercontact_2.length() < 10) {
        Toast.makeText(AddCustomerActivity.this, "re-enter the contact for second reference ", Toast.LENGTH_SHORT).show();
        return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(referemail_2).matches()) {
        Toast.makeText(AddCustomerActivity.this, "enter valid email for second reference", Toast.LENGTH_SHORT).show();
        return;
        }

        if(refername_1.equals("")&& referemail_1.equals("")&&refercontact_1.equals("")){

        Toast.makeText(AddCustomerActivity.this, "Please enter atleast one reference", Toast.LENGTH_SHORT).show();
        return;

        }
        if (!yes_rb&&!no_rb){
        Toast.makeText(AddCustomerActivity.this, "PTP form signed or not", Toast.LENGTH_SHORT).show();
        return;
        }


        // Create data variable for sent values to server

        String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(name, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(email, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(address, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(adhaar, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(pan, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(application, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(bankname, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(bankaccount, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(ifsccode, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(amount, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(payment, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(refername_1, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(refercontact_1, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(referemail_1, "UTF-8");

        data += "&" + URLEncoder.encode("user", "UTF-8")
                + "=" + URLEncoder.encode(refername_2, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(refercontact_2, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(referemail_2, "UTF-8");

        *//*if(dd_rb) {
            data += "&" + URLEncoder.encode("customers_mode_of_payment", "UTF-8")
                    + "=" + URLEncoder.encode(dd, "UTF-8");
        }

        if(cheque_rb)
        {
            data += "&" + URLEncoder.encode("customers_mode_of_payment", "UTF-8")
                    + "=" + URLEncoder.encode(cheque, "UTF-8");
        }

        if(yes_rb)
        {
            data += "&" + URLEncoder.encode("customers_ptp_form_signed", "UTF-8")
                    + "=" + URLEncoder.encode(yes, "UTF-8");

        }

        if(no_rb)
        {
            data += "&" + URLEncoder.encode("customers_ptp_form_signed", "UTF-8")
                    + "=" + URLEncoder.encode(no, "UTF-8");

        }

        if(yess_rb)
        {
            data += "&" + URLEncoder.encode("customers_ecs_signed", "UTF-8")
                    + "=" + URLEncoder.encode(cheque, "UTF-8");

        }

        if(noo_rb)
        {
            data += "&" + URLEncoder.encode("customers_ecs_signed", "UTF-8")
                    + "=" + URLEncoder.encode(cheque, "UTF-8");
            
        }
*//*



        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("/media/webservice/httppost.php");
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {
                reader.close();
            }

            catch(Exception ex) {}
        }

    }
*/




    // function to convert bitmap to string
    public String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void add_image(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        profile_photo.setVisibility(View.VISIBLE);
        //File file = new File(Environment.getExternalStorageDirectory(),
        //      counter+".jpg");
        //Uri photoPath = Uri.fromFile(file);
        // i.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void add_adhaar_card(View view) {
        Intent i = new Intent();
        adhaar_card.setVisibility(View.VISIBLE);
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST1);
    }
    public void add_pan_card(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        pan_card.setVisibility(View.VISIBLE);
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST2);
    }
    public void upload_ptp(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        ptp_image.setVisibility(View.VISIBLE);
        //File file = new File(Environment.getExternalStorageDirectory(),
        //      counter+".jpg");
        //Uri photoPath = Uri.fromFile(file);
        // i.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
        startActivityForResult(i.createChooser(i, "Select Picture"), PICK_IMAGE_REQUEST3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap = decodeUri(AddCustomerActivity.this, filePath, 700);
                profile_photo_string = getStringImage(bitmap);
                //Setting the Bitmap to ImageView
                profile_photo.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST1 && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap1 = decodeUri(AddCustomerActivity.this, filePath, 700);
                adhaar_card_string = getStringImage(bitmap1);
                //Setting the Bitmap to ImageView
                adhaar_card.setImageBitmap(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST2 && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap2 = decodeUri(AddCustomerActivity.this, filePath, 700);
                pan_card_string = getStringImage(bitmap2);
                //Setting the Bitmap to ImageView
                pan_card.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST3 && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap3 = decodeUri(AddCustomerActivity.this, filePath, 700);
                ptp_image_string = getStringImage(bitmap3);
                //Setting the Bitmap to ImageView
                ptp_image.setImageBitmap(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // function to scale down image
    public Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }
   public void submit(View view) {

        String name = name_et.getText().toString();
        String suvidha = suvidha_tv.getText().toString();
        String distributor = distributor_tv.getText().toString();
        String email = email_et.getText().toString();
        String address = add_et.getText().toString();
        String application = app_et.getText().toString();
        String ward = ward_tv.getText().toString();

        String pan= pan_et.getText().toString().trim();

        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

        Matcher matcher = pattern .matcher(pan);



        String contact = contact_tv.getText().toString();
        String adhaar = adhaar_et.getText().toString();
        String bankname = bankname_et.getText().toString();
        String bankaccount = bankaccount_et.getText().toString();


        String ifsccode= ifsccode_et.getText().toString();
        String patternn = "[A-Z]{4}[0][A-Z0-9]{6}";



        String amount = amount_et.getText().toString();
        String payment = payment_et.getText().toString();
        String refername_1 = refer_name1.getText().toString();
        String refercontact_1 = refer_contact1.getText().toString();
        String referemail_1 = refer_email1.getText().toString();
        String refername_2 = refer_name2.getText().toString();
        String refercontact_2 = refer_contact2.getText().toString();
        String referemail_2 = refer_email2.getText().toString();

       // String address = loc;


        Boolean dd_rb = dd.isChecked();
        Boolean cheque_rb = cheque.isChecked();
        Boolean yes_rb = yes.isChecked();
        Boolean no_rb = no.isChecked();

        Boolean yess_rb = yess.isChecked();
        Boolean noo_rb = noo.isChecked();


       /* if (suvidha.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the suvidha center name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (distributor.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the distributor name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ward.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the ward no.", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (application.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the application no. ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.length() < 3) {
            Toast.makeText(AddCustomerActivity.this, "customer name should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
            return;
        }
       /* if (contact.length() < 10) {
            Toast.makeText(AddCustomerActivity.this, "re-enter the contact no. ", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if (address.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adhaar.length() < 12|| adhaar.length()>12) {
            Toast.makeText(AddCustomerActivity.this, "please check your adhaar card no.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!matcher .matches())
        {
            Toast.makeText(getApplicationContext(), pan+" is not valid pan no.",
                    Toast.LENGTH_LONG).show();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(AddCustomerActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (bankname.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bankaccount.length() <19 || bankaccount.length()>8  ) {
            Toast.makeText(AddCustomerActivity.this, " account no. should be between 8-19 digits", Toast.LENGTH_SHORT).show();
           return;
       }
        if (!ifsccode.matches(patternn) ||ifsccode.length()< 11 || ifsccode.length() > 11) {
            Toast.makeText(AddCustomerActivity.this, " ifsc code is not valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!dd_rb&&!cheque_rb){
            Toast.makeText(AddCustomerActivity.this, "select mode of payment", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!yess_rb&&!noo_rb){
            Toast.makeText(AddCustomerActivity.this, "ECS signed or not?", Toast.LENGTH_SHORT).show();
            return;
        }
        if (amount.equals("")) {
            Toast.makeText(AddCustomerActivity.this, " amount  should  be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (payment.equals("")) {
            Toast.makeText(AddCustomerActivity.this, "enter the payment details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (refername_1.length() < 3) {
            Toast.makeText(AddCustomerActivity.this, "name for first reference should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
            return;
        }
        if (refercontact_1.length() < 10) {
            Toast.makeText(AddCustomerActivity.this, "re-enter the contact for first reference ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(referemail_1).matches()) {
            Toast.makeText(AddCustomerActivity.this, "enter valid email for first reference", Toast.LENGTH_SHORT).show();
            return;
        }

        if (refername_2.length() < 3) {
            Toast.makeText(AddCustomerActivity.this, "name for second reference should contain atleast 3 alphabets", Toast.LENGTH_SHORT).show();
            return;
        }
        if (refercontact_2.length() < 10) {
            Toast.makeText(AddCustomerActivity.this, "re-enter the contact for second reference ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(referemail_2).matches()) {
            Toast.makeText(AddCustomerActivity.this, "enter valid email for second reference", Toast.LENGTH_SHORT).show();
            return;
        }

        if(refername_1.equals("")&& referemail_1.equals("")&&refercontact_1.equals("")){

            Toast.makeText(AddCustomerActivity.this, "Please enter atleast one reference", Toast.LENGTH_SHORT).show();
            return;

        }
        if (!yes_rb&&!no_rb){
            Toast.makeText(AddCustomerActivity.this, "PTP form signed or not", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject job = new JSONObject();
        try {
            job.put("customers_name", name);
            job.put("customers_contact", contact);
            job.put("customers_email", email);
            job.put("customers_suvidha_center_id", suvidha);
            job.put("customers_distributor_id", distributor);
            job.put("customers_ward_no", ward);
            job.put("customers_address", address);
            job.put("customers_aadhar", adhaar);
            job.put("customers_pan_no", pan);
            job.put("customers_application_no", application);


            job.put("customer_id", getIntent().getStringExtra("customer_id"));
            job.put("customers_profile_photo", profile_photo_string);
            job.put("customers_adhar_upload", adhaar_card_string);
            job.put("customers_pan_upload", pan_card_string);
            job.put("customers_ptp_upload", ptp_image_string);

            job.put("customers_bank_name", bankname);
            job.put("customers_bank_account_no", bankaccount);
            job.put("customers_ifsc_code", ifsccode);
            job.put("customers_bank_charges", amount);
            job.put("customers_payment_details", payment);
            job.put("customers_refrence1_name", refername_1);
            job.put("customers_refrence1_phone", refercontact_1);
            job.put("customers_refrence1_email", referemail_1);
            job.put("customers_refrence2_name", refername_2);
            job.put("customers_refrence_phone", refercontact_2);
            job.put("customers_refrence_email", referemail_2);
            job.put("customers_status", "pending");


            if(dd_rb){

                job.put("customers_mode_of_payment","dd");
            }

            if(cheque_rb)
            {
                job.put("customers_mode_of_payment","cheque");
            }

            if(yes_rb)
            {
                job.put("customers_ptp_form_signed" , "yes");
            }

            if(no_rb)
            {
                job.put("customers_ptp_form_signed" , "no");
            }

            if(yess_rb)
            {
                job.put("customers_ecs_signed" , "yess");
            }

            if(noo_rb)
            {
                job.put("customers_ecs_signed" , "noo");
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://suraksha.reitindia.org/dashboard/insert_customers_data_api", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    if (response.getString("key").equals("0")) {
                        Toast.makeText(AddCustomerActivity.this, " not done", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.getString("key").equals("1")) {
                        Toast.makeText(AddCustomerActivity.this, "done", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(AddCustomerActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(AddCustomerActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                {
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });
        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));
        AppController app = new AppController(AddCustomerActivity.this);
        app.addToRequestQueue(jobjreq);

    }

    public void bank_details(View view) {
        bank_detail.setVisibility(View.VISIBLE);
    }
}


