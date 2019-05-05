package com.example.muneeb.rummsey;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JoystickActivity extends AppCompatActivity {

    private JoystickView mJoyStickT, mJoyStickB;
    private TextView mTvTAngle,mTvTStrenght, mTvBAngle, mTvBStrenght;
    private Button mBtnSend;
    private TextView mTvResponse;

    OkHttpClient okHttpClient;
    Request request;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        mJoyStickT = findViewById(R.id.joystick_T);
        mJoyStickB = findViewById(R.id.joystick_B);

        mTvTAngle = findViewById(R.id.tvAngleTVal);
        mTvTStrenght = findViewById(R.id.tvStrengthTVal);
        mTvBAngle = findViewById(R.id.tvAngleBVal);
        mTvBStrenght = findViewById(R.id.tvStrengthBVal);

        mBtnSend = findViewById(R.id.btn_send);

        mTvResponse = findViewById(R.id.tv_response);

        okHttpClient = new OkHttpClient();
        String url = "";
        request = new Request.Builder()
                .url(url)
                .build();

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String mResponse = response.body().string();

                            JoystickActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTvResponse.setText(mResponse);
                                }
                            });
                        }
                    }
                });
            }
        });

        mJoyStickT.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTvTAngle.setText(String.valueOf(angle));
                mTvTStrenght.setText(String.valueOf(strength));
            }
        });

        mJoyStickB.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTvBAngle.setText(String.valueOf(angle));
                mTvBStrenght.setText(String.valueOf(strength));
            }
        });

    }
}