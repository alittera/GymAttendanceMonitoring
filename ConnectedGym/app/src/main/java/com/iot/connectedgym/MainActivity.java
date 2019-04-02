package com.iot.connectedgym;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.mgmtsdk.feature.bulk_updater.BulkUpdater;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BulkUpdater bulkUpdater;

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

        // Estimote Credentials
        EstimoteSDK.initialize(getApplicationContext(), "connected-gym-76c", "3216c5ace0d91a371ba79e801584b99e");
        EstimoteSDK.enableDebugLogging(true);
    }

}
