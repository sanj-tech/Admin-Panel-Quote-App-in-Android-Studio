package com.jsstech.quoteappadminpanel.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.jsstech.quoteappadminpanel.Adapter.AboutAdapter;
import com.jsstech.quoteappadminpanel.Adapter.ContactAdapter;
import com.jsstech.quoteappadminpanel.Model.AboutModel;
import com.jsstech.quoteappadminpanel.Model.ContactModel;
import com.jsstech.quoteappadminpanel.R;

import java.util.ArrayList;
import java.util.List;


public class AboutViewData extends AppCompatActivity {


    private TextView line1, line2, line3, line4, line5;

    private static final String LINE1_KEY = "com.example.firebasecrud.LINE1_";
    private static final String LINE2_KEY = "com.example.firebasecrud.LINE2_";
    private static final String LINE3_KEY = "com.example.firebasecrud.LINE3_";
    private static final String LINE4_KEY = "com.example.firebasecrud.LINE4_";
    private static final String LINE5_KEY = "com.example.firebasecrud.LINE5_";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_view_data);


        line1 = findViewById(R.id.view_line1);
        line2 = findViewById(R.id.view_line2);
        line3 = findViewById(R.id.view_line3);
        line4 = findViewById(R.id.view_line4);
        line5 = findViewById(R.id.view_line5);

        Intent intent = getIntent();

        String LINE1 = intent.getStringExtra(LINE1_KEY);
        String LINE2 = intent.getStringExtra(LINE2_KEY);
        String LINE3 = intent.getStringExtra(LINE3_KEY);
        String LINE4 = intent.getStringExtra(LINE4_KEY);
        String LINE5 = intent.getStringExtra(LINE5_KEY);

        line1.setText(LINE1);
        line2.setText(LINE2);
        line3.setText(LINE3);
        line4.setText(LINE4);
        line5.setText(LINE5);
    }
}
