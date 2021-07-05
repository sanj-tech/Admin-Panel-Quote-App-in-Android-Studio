package com.jsstech.quoteappadminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPanel extends AppCompatActivity {
    EditText edtEmail,edtPass;
    Button btnLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_panel);

        edtEmail=findViewById(R.id.et_email);
        edtPass=findViewById(R.id.et_pass);
        btnLogin=findViewById(R.id.btlogin);

        firebaseAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EMAIL=edtEmail.getText().toString().trim();
                String PASS=edtPass.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(EMAIL,PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginPanel.this,"sucessullLogin" +EMAIL,Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(LoginPanel.this,MainActivity.class);
                            startActivity(intent);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginPanel.this,"Unsucessful login" +e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    public void signUp(View view) {
        String EMAIL=edtEmail.getText().toString().trim();
        String PASS=edtPass.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(EMAIL,PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginPanel.this,"user created",Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginPanel.this,"new user creation fail",Toast.LENGTH_SHORT).show();
            }
        });

    }
}