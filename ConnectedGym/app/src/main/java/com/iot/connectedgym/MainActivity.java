package com.iot.connectedgym;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage, myBeacon;
    private final String TAG_DEBUG = "DemoActivity";
    final private String DEBUG_TAG= TAG_DEBUG;
    final private int REQUEST_ENABLE_BT = 125;
    private int request=0, max_request=99;
    private int ID_response = -1;
    private BeaconManager beaconManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_account:
                    mTextMessage.setText(R.string.title_account);
                    Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_settings);
                    Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myBeacon = (TextView) findViewById(R.id.mybeacon);

        // Add Beacon Manager
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                myBeacon.setText("Beacon found!");
                Toast.makeText(getApplicationContext(), "Beacon found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onExitedRegion(Region region) {
                myBeacon.setText("Beacon exit!");
                Toast.makeText(getApplicationContext(), "No more beacon", Toast.LENGTH_LONG).show();
            }
        });

        beaconManager.setBackgroundScanPeriod(2000, 2000);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region("Room", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));
            }
        });

    }

}
