package com.jsstech.quoteappadminpanel.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jsstech.quoteappadminpanel.R;


public class ContactViewData extends AppCompatActivity {

    private TextView num1, num2, email, website, address;

    private static final String NUM1_KEY = "com.example.firebasecrud.NUM1_";
    private static final String NUM2_KEY = "com.example.firebasecrud.NUM2_";
    private static final String EMAIL_KEY = "com.example.firebasecrud.EMAIL_";
    private static final String WEBSITE_KEY = "com.example.firebasecrud.WEBSITE_";
    private static final String ADDRESS_KEY = "com.example.firebasecrud.ADDRESS_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view_data);

        num1 = findViewById(R.id.view_num1);
        num2 = findViewById(R.id.view_num2);
        email = findViewById(R.id.view_email);
        website = findViewById(R.id.view_website);
        address = findViewById(R.id.view_address);

        Intent intent = getIntent();

        String NUM1 = intent.getStringExtra(NUM1_KEY);
        String NUM2 = intent.getStringExtra(NUM2_KEY);
        String EMAIL = intent.getStringExtra(EMAIL_KEY);
        String WEBSITE = intent.getStringExtra(WEBSITE_KEY);
        String ADDRESS = intent.getStringExtra(ADDRESS_KEY);

        num1.setText(NUM1);
        num2.setText(NUM2);
        email.setText(EMAIL);
        website.setText(WEBSITE);
        address.setText(ADDRESS);
    }
}
