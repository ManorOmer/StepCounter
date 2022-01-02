package com.example.stepcounter;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mAge, mWeight, mHeight;
    Button mRegisterButton, mReturn;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegisterButton = findViewById(R.id.register);
        mReturn = findViewById(R.id.login);
        mAge = findViewById(R.id.age);
        mWeight = findViewById(R.id.weight);
        mHeight = findViewById(R.id.height);

        mAuth = FirebaseAuth.getInstance();


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname = mFullName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String age = mAge.getText().toString().trim();
                final String weight = mWeight.getText().toString().trim();
                final String height = mHeight.getText().toString().trim();

                if(fullname.isEmpty()){
                    mFullName.setError("Full Name is Required");
                    mFullName.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    mEmail.setError("Full Name is Required");
                    mEmail.requestFocus();
                    return;
                }

                //if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                //    mEmail.setError("Email is not valid");
                //    mEmail.requestFocus();
                //    return;
                //}

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

                if(age.isEmpty()){
                    mAge.setError("Age is Required");
                    mPassword.requestFocus();
                    return;
                }

                if(weight.isEmpty()){
                    mWeight.setError("Weight is required");
                    mWeight.requestFocus();
                    return;
                }

                if(height.isEmpty()){
                    mHeight.setError("Height is required");
                    mHeight.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    User user = new User(fullname, age, weight, height, email );
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "User Has Been registered to system!", Toast.LENGTH_LONG).show();
                                            }
                                            else{
                                                Toast.makeText(Register.this, "User registration has been failed!" + email, Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(Register.this, "User registration has been failed!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });

        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}