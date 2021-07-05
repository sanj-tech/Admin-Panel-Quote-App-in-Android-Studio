package com.jsstech.quoteappadminpanel.Insert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jsstech.quoteappadminpanel.Model.QuotesModel;
import com.jsstech.quoteappadminpanel.R;
import com.jsstech.quoteappadminpanel.View.QuotesList;

public class QuotesInsert extends AppCompatActivity {

    private EditText qt_cat_id, qt_qt;
    private Spinner spinner_cat;
    private Button button;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    private String mystring[];
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_insert);

        qt_cat_id = findViewById(R.id.qt_cat_id);
        spinner_cat = findViewById(R.id.qt_cat);
        qt_qt = findViewById(R.id.qt_qt);
        button = findViewById(R.id.insert_quote_button);
        progressBar = findViewById(R.id.qt_pro);

        mystring = getResources().getStringArray(R.array.quotes_category);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, mystring);
        spinner_cat.setAdapter(arrayAdapter);

        spinner_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = mystring[position];

                switch (pos) {
                    case "Motivation":
                        qt_cat_id.setText("1");
                        break;

                    case "Inspiration":
                        qt_cat_id.setText("2");
                        break;

                    case "Success":
                        qt_cat_id.setText("3");
                        break;

                    case "Positive":
                        qt_cat_id.setText("4");
                        break;

                    case "Leadership":
                        qt_cat_id.setText("5");
                        break;

                    case "Life":
                        qt_cat_id.setText("6");
                        break;

                    case "Love":
                        qt_cat_id.setText("7");
                        break;

                    case "Attitude":
                        qt_cat_id.setText("8");
                        break;

                    case "Change":
                        qt_cat_id.setText("9");
                        break;

                    case "Patience":
                        qt_cat_id.setText("10");
                        break;

                    case "Peace":
                        qt_cat_id.setText("11");
                        break;

                    case "Education":
                        qt_cat_id.setText("12");
                        break;

                    case "Relationship":
                        qt_cat_id.setText("13");
                        break;

                    case "Failure":
                        qt_cat_id.setText("14");
                        break;

                    case "Faith":
                        qt_cat_id.setText("15");
                        break;

                    case "Power":
                        qt_cat_id.setText("16");
                        break;

                    case "Friendship":
                        qt_cat_id.setText("17");
                        break;

                    case "Happiness":
                        qt_cat_id.setText("18");
                        break;

                    case "Health":
                        qt_cat_id.setText("19");
                        break;

                    case "Trust":
                        qt_cat_id.setText("20");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("quotes");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CAT_ID = qt_cat_id.getText().toString();
                String CAT = spinner_cat.getSelectedItem().toString();
                String QUOTE = qt_qt.getText().toString();

                if (CAT_ID.isEmpty()) {
                    qt_cat_id.setError("Please enter ID");
                    qt_cat_id.requestFocus();
                    return;
                }


                if (QUOTE.isEmpty()) {
                    qt_qt.setError("Please enter quote");
                    qt_qt.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);

                String ID = databaseReference.push().getKey();

                QuotesModel quotesModel = new QuotesModel(ID, CAT_ID, CAT, QUOTE);

                databaseReference.child(ID).setValue(quotesModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuotesInsert.this, "Quotes Inserted", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(QuotesInsert.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                            }
                        });


            }
        });

    }

    public void viewQuoteList(View view) {
        startActivity(new Intent(QuotesInsert.this, QuotesList.class));
    }
}
