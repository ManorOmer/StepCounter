package com.example.stepcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertHeight extends AppCompatActivity {
    EditText mHeight;
    Button btnCon;
    public static double heightMeter;

    public static double getHeightMeter() {
        return heightMeter;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.height_screen);

        mHeight = (EditText)findViewById(R.id.etHeight);
        btnCon = (Button)findViewById(R.id.con);

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHeight = mHeight.getText().toString().trim();
                int intHeight = Integer.valueOf(mHeight.getText().toString().trim());
                if(strHeight.isEmpty()){
                    mHeight.setError("Height is Required");
                    mHeight.requestFocus();
                    return;
                }
                //if(strHeight.matches("[0-9]+") && strHeight.length() >= 2 && strHeight.length() < 4 && intHeight > 20 && intHeight < 280){
                //    mHeight.setError("Height is not valid");
                //    mHeight.requestFocus();
                //    return;
                //}

                heightMeter = intHeight / 100;

                Intent intent = new Intent(InsertHeight.this, StepCounter.class);
                startActivity(intent);

            }
        });



    }
}
