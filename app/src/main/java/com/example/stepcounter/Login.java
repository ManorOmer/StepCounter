package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mCreate, mLogin, mContinue;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);
        mCreate = findViewById(R.id.create);
        mLogin = findViewById(R.id.login);
        mContinue = findViewById(R.id.con);

        mAuth = FirebaseAuth.getInstance();


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(email.isEmpty()){
                    mEmail.setError("Email is required!");
                    mEmail.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    mPassword.setError("Password is Required");
                    mPassword.requestFocus();
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password length should be at least 6 chars");
                    mPassword.requestFocus();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Login.this, StepCounter.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login.this, "login has been failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, InsertHeight.class);
                startActivity(intent);
            }
        });
    }
}