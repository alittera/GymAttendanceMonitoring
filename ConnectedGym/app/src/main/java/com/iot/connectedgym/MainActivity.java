package com.iot.connectedgym;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage, myBeacon;
    private BeaconManager beaconManager;
    private int room_1 = 53723, room_2 = 44680;

    private final String DEBUG_TAG = "DEBUG";
    final private int REQUEST_ENABLE_BT = 125;
    private int request=0, max_request=99;

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

        beaconManager.setBackgroundScanPeriod(1000, 1000);

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                if(list.get(0).getMinor() == room_1) {
                    myBeacon.setText("Beacon found: Room 1");
                }
                else if (list.get(0).getMinor() == room_2) {
                    myBeacon.setText("Beacon found: Room 2");
                }
                beaconManager.startRanging(region);
                Toast.makeText(getApplicationContext(), "Beacon found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onExitedRegion(Region region) {
                myBeacon.setText("Beacon exit!");
                Toast.makeText(getApplicationContext(), "No more beacon", Toast.LENGTH_LONG).show();
            }
        });

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    nearestBeacon.getRssi();
                    nearestBeacon.getMeasuredPower();
                    Utils.Proximity pos = Utils.computeProximity(nearestBeacon);
                    Log.d(DEBUG_TAG, "  Utils.computeProximity(nearestBeacon): " + Utils.computeProximity(nearestBeacon));
                    String msg = "";
                    if (pos == Utils.Proximity.IMMEDIATE) {
                        msg = "IMMEDIATE";
                    } else if (pos == Utils.Proximity.NEAR) {
                        msg = "NEAR";
                    } else if (pos == Utils.Proximity.FAR) {
                        msg = "FAR";

                    } else if (pos == Utils.Proximity.UNKNOWN) {
                        msg = "UNKNOWN";
                    }
                    Log.d(DEBUG_TAG, "Distance from Beacon: "+msg);
                }
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region("Room", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "onResume()");
        // ENABLE BLUETooth
        enableBluetoothPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(DEBUG_TAG, "onPause()");
    }

    @Override
    protected  void onStop(){
        super.onStop();
        Log.d(DEBUG_TAG, "onStop()");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.d(DEBUG_TAG, "onDestroy()");
    }

    private void enableBLT(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        if(request<max_request) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            }
            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }else{
            Log.d(DEBUG_TAG,"request more than 4");
        }

    }
    @Override
    //return from  startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT) in enableBLT()
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user open the bluethoot.
                // The Intent's data Uri identifies which contact was selected.
                Log.d(DEBUG_TAG,"L utente ha dato il permesso");
                // Do something with the contact here (bigger example below)
            }else{ //if(requestCode == RESULT_CANCELED){
                Log.d(DEBUG_TAG,"L utente non ha dato il permesso");
                request++;

            }
        }
    }


    private void enableBluetoothPermission() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            //shouldShowRequestPermissionRationale() = If this function is called on pre-M, it will always return false.
            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                showMessageOKCancel("You need to allow access for BLT scanning on Android 6.0 and above.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ENABLE_BT);
                            }
                        });

                return;
            }
            //If this function is called on pre-M, OnRequestPermissionsResultCallback will be suddenly called with correct PERMISSION_GRANTED or PERMISSION_DENIED result.
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ENABLE_BT);
            return;
        }
        //once i have got the ACCESS_COARSE_LOCATION i can check if the blt is turns on.
        enableBLT();
    }

    //return from  enableBluetoothPermission()
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Log.d(DEBUG_TAG,"PERMESSO DATO!!!");
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "PERMISSION_GRANTED Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
