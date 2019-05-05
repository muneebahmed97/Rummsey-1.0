package com.example.muneeb.rummsey;

import android.app.ActionBar;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //declaration of cardview
    CardView cvGPSMode, cvSurveillanceMode, cvAutoMode, cvGloveMode;

    //delaration of Button
    Button btnJoyStickMode, btnBluetoothOn, btnBluetoothOff, btnBluetoothList;

    //Bluetooth Adapter declaration
    BluetoothAdapter mBluetoothAdapter;

    //ListView declaration
    ListView lvPairedDevices;

    Intent bluetoothIntent;

    int REQUEST_ENABLE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of cardview
        cvGPSMode = findViewById(R.id.cv_gpsMode);
        cvAutoMode = findViewById(R.id.cv_autoMode);
        cvSurveillanceMode = findViewById(R.id.cv_surveillanceMode);
        cvGloveMode = findViewById(R.id.cv_gloveMode);

        //initialization of Buttons
        btnJoyStickMode = findViewById(R.id.btn_joystickMode);
        btnBluetoothOn = findViewById(R.id.btn_bluetooth_on);
        btnBluetoothOff = findViewById(R.id.btn_bluetooth_off);
        btnBluetoothList = findViewById(R.id.btn_bluetooth_list);

        //initialization of Bluetooth Adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Intent to turn on Bluetooth
        bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        turnOnBluetooth();
        turnOffBluetooth();
        pairedDevices();

        //Joystick Button onClickListener
        btnJoyStickMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoystickActivity.class);
                startActivity(intent);
            }
        });

        //GPS CardView onClick
        cvGPSMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        //Autonomous mode CardView onClickListener
        cvAutoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AutonomousActivity.class);
                startActivity(intent);
            }
        });

        //Surveillance Mode CardView onClickListener
        cvSurveillanceMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SurveillanceActivity.class);
                startActivity(intent);
            }
        });

        //Glove Mode CardView onClickListener
        cvGloveMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GLoveActivity.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getActionBar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_ENABLE_CODE) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, "Bluetooth Enabled!", Toast.LENGTH_SHORT).show();
            else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Bluetooth Enabling Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }

    private void turnOnBluetooth() {
        btnBluetoothOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBluetoothAdapter == null)
                    Toast.makeText(MainActivity.this, "Bluetooth not supported on this device!!", Toast.LENGTH_SHORT).show();
                else if (!mBluetoothAdapter.isEnabled())
                    startActivityForResult(bluetoothIntent, REQUEST_ENABLE_CODE);
            }
        });
    }

    private void turnOffBluetooth() {
        btnBluetoothOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBluetoothAdapter.isEnabled())
                    mBluetoothAdapter.disable();
            }
        });
    }

    private void pairedDevices() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.paired_devices_list);

        lvPairedDevices = dialog.findViewById(R.id.lv_paired_devices);

        btnBluetoothList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> bluetoothDevices = mBluetoothAdapter.getBondedDevices();
                String[] devicesArray = new String[bluetoothDevices.size()];
                int index = 0;

                if (bluetoothDevices.size() > 0) {
                    for (BluetoothDevice device : bluetoothDevices) {
                        devicesArray[index] = device.getName();
                        index++;
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, devicesArray);
                    lvPairedDevices.setAdapter(arrayAdapter);
                }
                dialog.show();
            }
        });
    }

    //implementing signout at the end of toolbar with the 3 dots
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_scan_bluetooth:
                startActivity(new Intent(this, ScanDevicesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}