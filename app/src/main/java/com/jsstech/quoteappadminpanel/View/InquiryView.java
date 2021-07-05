package com.jsstech.quoteappadminpanel.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jsstech.quoteappadminpanel.Adapter.InquiryAdapter;
import com.jsstech.quoteappadminpanel.Model.ModelInquiry;
import com.jsstech.quoteappadminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class InquiryView extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference databaseReference;
    private List<ModelInquiry> list;
    private InquiryAdapter inquiryAdapter;
    private ModelInquiry modelInquiry;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_view);

        listView = findViewById(R.id.inquiry_listview);
        list = new ArrayList<>();

        progressBar = findViewById(R.id.inquiryviews_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("inquiry");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    modelInquiry = snap.getValue(ModelInquiry.class);
                    list.add(modelInquiry);
                }
                inquiryAdapter = new InquiryAdapter(InquiryView.this, list);
                listView.setAdapter(inquiryAdapter);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
