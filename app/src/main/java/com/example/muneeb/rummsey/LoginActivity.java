package com.example.muneeb.rummsey;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mEtUsername, mEtPwd;
    Button mBtnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUsername = findViewById(R.id.et_username);
        mEtPwd = findViewById(R.id.et_password);

        final String username = mEtUsername.getText().toString();
        final String pwd = mEtPwd.getText().toString();

        mBtnSignIn = findViewById(R.id.btn_sign_in);
        final Intent intent = new Intent(this, MainActivity.class);
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.equals("muneeb") && pwd.equals("muneeb")) {
                    startActivity(intent);
                } else if (username.equals("saim") && pwd.equals("saim")) {
                    startActivity(intent);
                } else if (username.equals("rabia") && pwd.equals("rabia")) {
                    startActivity(intent);
                } else if (username.equals("usama") && pwd.equals("usama")) {
                    startActivity(intent);
                } else if (username.equals("musaddiq") && pwd.equals("musaddiq")) {
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Username or Password is not correct!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
