package com.example.muneeb.rummsey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AutonomousActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous);

        Toast.makeText(this, "Welcome to Autonomous Mode!", Toast.LENGTH_SHORT).show();
    }
}
