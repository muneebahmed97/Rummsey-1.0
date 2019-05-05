package com.example.muneeb.rummsey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GLoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glove);

        Toast.makeText(this, "Welcome to Glove mode!", Toast.LENGTH_SHORT).show();
    }
}
