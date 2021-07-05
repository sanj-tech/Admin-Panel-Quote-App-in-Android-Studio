package com.jsstech.quoteappadminpanel.Insert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.jsstech.quoteappadminpanel.Model.ContactModel;
import com.jsstech.quoteappadminpanel.R;
import com.jsstech.quoteappadminpanel.View.ContactView;

public class ContactInsert extends AppCompatActivity {

    private EditText num1, num2, email, website, address;
    private Button insert, viewData;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_insert);

        num1 = findViewById(R.id.insert_contact1);
        num2 = findViewById(R.id.insert_contact2);
        email = findViewById(R.id.insert_email);
        website = findViewById(R.id.insert_website);
        address = findViewById(R.id.insert_address);
        insert = findViewById(R.id.insert_button);
        progressBar = findViewById(R.id.contactUSProgress);
        viewData = findViewById(R.id.insert_viewdata_button);

        databaseReference = FirebaseDatabase.getInstance().getReference("contactus");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NUM1 = num1.getText().toString().trim();
                String NUM2 = num2.getText().toString().trim();
                String EMAIL = email.getText().toString().trim();
                String WEBSITE = website.getText().toString().trim();
                String ADDRESS = address.getText().toString().trim();

                if (NUM1.isEmpty()) {
                    num1.setError("Please enter num 1");
                    num1.requestFocus();
                    return;
                }

                if (NUM2.isEmpty()) {
                    num2.setError("Please enter num 2");
                    num2.requestFocus();
                    return;
                }

                if (EMAIL.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
                    email.setError("Email is not valid");
                    email.requestFocus();
                    return;
                }

                if (WEBSITE.isEmpty()) {
                    website.setError("Please enter website");
                    website.requestFocus();
                    return;
                }

                if (ADDRESS.isEmpty()) {
                    address.setError("Please enter address");
                    address.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                insert.setVisibility(View.GONE);

                String ID = databaseReference.push().getKey();

                ContactModel contactModel = new ContactModel(ID, NUM1, NUM2, EMAIL, WEBSITE, ADDRESS);

//                databaseReference.child(EMAIL).setValue(contactModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                databaseReference.child(ID).setValue(contactModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            insert.setVisibility(View.VISIBLE);
                            num1.setText("");
                            num2.setText("");
                            email.setText("");
                            website.setText("");
                            address.setText("");

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ContactInsert.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                insert.setVisibility(View.VISIBLE);
                            }
                        });


            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactInsert.this, ContactView.class));
            }
        });

    }

    public void updateContactData(View view) {






    }
}
