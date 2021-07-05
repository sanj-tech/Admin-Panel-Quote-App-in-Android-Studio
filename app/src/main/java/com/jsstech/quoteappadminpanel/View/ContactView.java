package com.jsstech.quoteappadminpanel.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jsstech.quoteappadminpanel.Adapter.ContactAdapter;
import com.jsstech.quoteappadminpanel.Model.ContactModel;
import com.jsstech.quoteappadminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class ContactView extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<ContactModel> list;
    ContactAdapter contactAdapter;
    ContactModel contactModel;

    private static final String NUM1_KEY = "com.example.firebasecrud.NUM1_";
    private static final String NUM2_KEY = "com.example.firebasecrud.NUM2_";
    private static final String EMAIL_KEY = "com.example.firebasecrud.EMAIL_";
    private static final String WEBSITE_KEY = "com.example.firebasecrud.WEBSITE_";
    private static final String ADDRESS_KEY = "com.example.firebasecrud.ADDRESS_";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        listView = (ListView) findViewById(R.id.listViewContact1);
        list = new ArrayList<>();

        progressBar = findViewById(R.id.pro_activity_contact_view);
        progressBar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("contactus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    contactModel = snap.getValue(ContactModel.class);
                    list.add(contactModel);
                }
                contactAdapter = new ContactAdapter(ContactView.this, list);
                listView.setAdapter(contactAdapter);
                progressBar.setVisibility(View.INVISIBLE);

//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        Toast.makeText(ContactView.this, "Hello world", Toast.LENGTH_SHORT).show();
//
//                        ContactModel contactModel = list.get(position);
//                        Intent intent = new Intent(ContactView.this, ContactViewData.class);
//                        intent.putExtra(NUM1_KEY, contactModel.getNum1());
//                        intent.putExtra(NUM2_KEY, contactModel.getNum2());
//                        intent.putExtra(EMAIL_KEY, contactModel.getEmail());
//                        intent.putExtra(WEBSITE_KEY, contactModel.getWebsite());
//                        intent.putExtra(ADDRESS_KEY, contactModel.getAddress());
//                        startActivity(intent);
//                    }
//                });

//                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                        User user = list.get(position);
//                        showUpdateDialog(user.getId(), user.getName(), user.getEmail(), user.getContact(), user.getCity(), user.getLang());
//
//                        return false;
//                    }
//                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contactModel = list.get(position);
                Intent intent = new Intent(ContactView.this, ContactViewData.class);
                intent.putExtra(NUM1_KEY, contactModel.getNum1());
                intent.putExtra(NUM2_KEY, contactModel.getNum2());
                intent.putExtra(EMAIL_KEY, contactModel.getEmail());
                intent.putExtra(WEBSITE_KEY, contactModel.getWebsite());
                intent.putExtra(ADDRESS_KEY, contactModel.getAddress());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contactModel = list.get(position);
                showUpdateDialog(contactModel.getId(), contactModel.getNum1(), contactModel.getNum2(), contactModel.getEmail(), contactModel.getWebsite(), contactModel.getAddress());
                return false;
            }
        });
        //extra code

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                contactModel = list.get(position);
//                showUpdateDialog(contactModel.getId(), contactModel.getNum1(), contactModel.getNum2(), contactModel.getEmail(), contactModel.getWebsite(), contactModel.getAddress());
//                return false;
//            }
//        });
    }

    private void showUpdateDialog(final String id, final String num1, final String num2, final String email, String website, String address) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ContactView.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.contact_update_layout, null);
        builder.setView(view);

        final EditText edit_num1 = (EditText) view.findViewById(R.id.update_num1);
        final EditText edit_num2 = (EditText) view.findViewById(R.id.update_num2);
        final EditText edit_email = (EditText) view.findViewById(R.id.update_email);
        final EditText edit_website = (EditText) view.findViewById(R.id.update_website);
        final EditText edit_address = (EditText) view.findViewById(R.id.update_address);

        Button btn_button = (Button) view.findViewById(R.id.update_button);
        Button btn_delete = (Button) view.findViewById(R.id.delete_button);

        builder.setTitle("Updating for " + email);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        edit_num1.setText(contactModel.getNum1());
        edit_num2.setText(contactModel.getNum2());
        edit_email.setText(contactModel.getEmail());
        edit_website.setText(contactModel.getWebsite());
        edit_address.setText(contactModel.getAddress());

        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_num1 = edit_num1.getText().toString().trim();
                String u_num2 = edit_num2.getText().toString().trim();
                String u_email = edit_email.getText().toString().trim();
                String u_website = edit_website.getText().toString().trim();
                String u_address = edit_address.getText().toString().trim();

                if (u_num1.isEmpty()) {
                    edit_num1.setError("Num 1 required");
                    edit_num2.requestFocus();
                    return;
                }

                updateUser(id, u_num1, u_num2, u_email, u_website, u_address);

                alertDialog.dismiss();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(id);
                alertDialog.dismiss();
            }
        });
    }

    private boolean updateUser(String id, String u_num1, String u_num2, String u_email, String u_website, String u_address) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("contactus").child(id);

        contactModel = new ContactModel(id, u_num1, u_num2, u_email, u_website, u_address);

        databaseReference.setValue(contactModel);

        Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_LONG).show();

        return true;
    }

    private void deleteUser(String id) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("contactus").child(id);
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
