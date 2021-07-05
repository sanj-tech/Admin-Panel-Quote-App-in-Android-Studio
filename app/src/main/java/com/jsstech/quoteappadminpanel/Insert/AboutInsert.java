package com.jsstech.quoteappadminpanel.Insert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jsstech.quoteappadminpanel.Model.AboutModel;
import com.jsstech.quoteappadminpanel.R;
import com.jsstech.quoteappadminpanel.View.AboutView;

public class AboutInsert extends AppCompatActivity {

    private EditText line1, line2, line3, line4, line5;
    private Button buttonInsert;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_insert);

        line1 = findViewById(R.id.abotUsLine1);
        line2 = findViewById(R.id.abotUsLine2);
        line3 = findViewById(R.id.abotUsLine3);
        line4 = findViewById(R.id.abotUsLine4);
        line5 = findViewById(R.id.abotUsLine5);
        buttonInsert = findViewById(R.id.aboutInsertButton);

        progressBar = findViewById(R.id.aboutProg);

        databaseReference = FirebaseDatabase.getInstance().getReference("aboutUs");

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String LINE1 = line1.getText().toString();
                String LINE2 = line2.getText().toString();
                String LINE3 = line3.getText().toString();
                String LINE4 = line4.getText().toString();
                String LINE5 = line5.getText().toString();

                if (LINE1.isEmpty()) {
                    line1.setError("Please insert content");
                    line1.requestFocus();
                    return;
                }


                if (LINE2.isEmpty()) {
                    line2.setError("Please insert content");
                    line2.requestFocus();
                    return;
                }


                if (LINE3.isEmpty()) {
                    line3.setError("Please insert content");
                    line3.requestFocus();
                    return;
                }


                if (LINE4.isEmpty()) {
                    line4.setError("Please insert content");
                    line4.requestFocus();
                    return;
                }


                if (LINE5.isEmpty()) {
                    line5.setError("Please insert content");
                    line5.requestFocus();
                    return;
                }

                buttonInsert.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                String ID = databaseReference.push().getKey();

                AboutModel aboutModel = new AboutModel(ID, LINE1, LINE2, LINE3, LINE4, LINE5);

//                databaseReference.child(ID).setValue(aboutModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                databaseReference.child(ID).setValue(aboutModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AboutInsert.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            buttonInsert.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AboutInsert.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                buttonInsert.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });

    }

    public void openAboutViewData(View view) {
        startActivity(new Intent(AboutInsert.this, AboutView.class));
    }
}
