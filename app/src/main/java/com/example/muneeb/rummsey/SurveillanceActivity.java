package com.example.muneeb.rummsey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SurveillanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveillance);

        Toast.makeText(this, "Welcome to Surveillance mode!", Toast.LENGTH_SHORT).show();
    }
}
