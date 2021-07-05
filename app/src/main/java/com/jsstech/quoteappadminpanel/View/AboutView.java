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
import com.jsstech.quoteappadminpanel.Adapter.AboutAdapter;
import com.jsstech.quoteappadminpanel.Model.AboutModel;
import com.jsstech.quoteappadminpanel.Model.ContactModel;
import com.jsstech.quoteappadminpanel.R;

import java.util.ArrayList;
import java.util.List;

public class AboutView extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    List<AboutModel> list;
    AboutAdapter aboutAdapter;
    AboutModel aboutModel;

    private static final String LINE1_KEY = "com.example.firebasecrud.LINE1_";
    private static final String LINE2_KEY = "com.example.firebasecrud.LINE2_";
    private static final String LINE3_KEY = "com.example.firebasecrud.LINE3_";
    private static final String LINE4_KEY = "com.example.firebasecrud.LINE4_";
    private static final String LINE5_KEY = "com.example.firebasecrud.LINE5_";

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_view);


        listView = findViewById(R.id.aboutListView1);
        list = new ArrayList<>();

        progressBar = findViewById(R.id.aboutProgress);
        progressBar.setVisibility(View.VISIBLE);


        databaseReference = FirebaseDatabase.getInstance().getReference("aboutUs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    aboutModel = snap.getValue(AboutModel.class);
                    list.add(aboutModel);
                }
                aboutAdapter = new AboutAdapter(AboutView.this,list);
                listView.setAdapter(aboutAdapter);
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {

                aboutModel = list.get(position);
                Intent intent = new Intent(AboutView.this,AboutViewData.class);
                intent.putExtra(LINE1_KEY,aboutModel.getLine1());
                intent.putExtra(LINE2_KEY,aboutModel.getLine2());
                intent.putExtra(LINE3_KEY,aboutModel.getLine3());
                intent.putExtra(LINE4_KEY,aboutModel.getLine4());
                intent.putExtra(LINE5_KEY,aboutModel.getLine5());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view,int i,long l) {
                aboutModel = list.get(i);
                showUpdateAboutDialog(aboutModel.getID(),aboutModel.getLine1(),aboutModel.getLine2(),aboutModel.getLine3(),aboutModel.getLine4(),aboutModel.getLine5());
                return false;
            }
        });
    }

    private void showUpdateAboutDialog(String id,String line1,String line2,String line3,String line4,String line5) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AboutView.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.about_update_layout,null);
        builder.setView(view);

        final EditText eline1 = (EditText) view.findViewById(R.id.update_line1);
        final EditText eline2 = (EditText) view.findViewById(R.id.update_line2);
        final EditText eline3 = (EditText) view.findViewById(R.id.update_line3);
        final EditText eline4 = (EditText) view.findViewById(R.id.update_line4);
        final EditText eline5 = (EditText) view.findViewById(R.id.update_line5);

        Button btn_buttonupdate = (Button) view.findViewById(R.id.update_abtButton);
        Button btn_buttondelete = (Button) view.findViewById(R.id.delete_abtButton);

        builder.setTitle("Updating for " + line1);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        eline1.setText(aboutModel.getLine1());
        eline2.setText(aboutModel.getLine1());
        eline3.setText(aboutModel.getLine1());
        eline4.setText(aboutModel.getLine1());
        eline5.setText(aboutModel.getLine1());

        btn_buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String up_line1 = eline1.getText().toString().trim();
                String up_line2 = eline2.getText().toString().trim();
                String up_line3 = eline3.getText().toString().trim();
                String up_line4 = eline4.getText().toString().trim();
                String up_line5 = eline5.getText().toString().trim();


                updateAbtuser(id,up_line1,up_line2,up_line3,up_line4,up_line5);

                alertDialog.dismiss();


            }
        });

        btn_buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteabtUser(id);
                alertDialog.dismiss();
            }
        });
    }


    private boolean updateAbtuser(String id,String up_line1,String up_line2,String up_line3,String up_line4,String up_line5) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("aboutUs").child(id);

        aboutModel = new AboutModel(id,up_line1,up_line2,up_line3,up_line4,up_line5);

        databaseReference.setValue(aboutModel);

        Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_LONG).show();

        return true;
    }

    private void deleteabtUser(String id) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("aboutUs").child(id);

        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"About deleted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

    }

}
