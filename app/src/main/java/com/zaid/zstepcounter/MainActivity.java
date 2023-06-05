package com.zaid.zstepcounter;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private static final long REFRESH_INTERVAL = 30 * 1000; // 30 seconds
    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 1001;
    private TextView stepCountTV;
    //    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler;
    private Runnable refreshRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepCountTV = findViewById(R.id.step_counter);

        ImageView refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(view -> {
            accessGoogleFitData();
            Toast.makeText(MainActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
            // Rotate 360 degree.
            ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
            rotationAnimator.setDuration(2000);
            rotationAnimator.start();
        });
        requestFitPermissions();
        setupRefreshAutomatic();
    }

    private void requestFitPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.ACTIVITY_RECOGNITION")
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{"android.permission.ACTIVITY_RECOGNITION"},
                    MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);


        } else {


            FitnessOptions fitnessOptions = FitnessOptions.builder()
                    .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                    .build();
            // if not sign in then Lets Sign in from google account connected with Google Fit.
            if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
                GoogleSignIn.requestPermissions(
                        this,
                        REQUEST_PERMISSIONS_REQUEST_CODE,
                        GoogleSignIn.getLastSignedInAccount(this),
                        fitnessOptions);
            } else {
                accessGoogleFitData();
            }
        }
    }

    private void accessGoogleFitData() {
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();

        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(readRequest)
                .addOnSuccessListener(dataReadResponse -> {
                    int totalStepCount = 0;
                    for (DataSet dataSet : dataReadResponse.getDataSets()) {
                        for (DataPoint dataPoint : dataSet.getDataPoints()) {
                            for (Field field : dataPoint.getDataType().getFields()) {
                                int stepCount = dataPoint.getValue(field).asInt();
                                totalStepCount += stepCount;
                            }
                        }
                    }

                    stepCountTV.setText(String.valueOf(totalStepCount));

                })
                .addOnFailureListener(e -> {
                    // Handle the error accordingly
                    Log.d("zaid", "accessGoogleFitData: --> ", e);
                });
    }

    private void setupRefreshAutomatic() {
        handler = new Handler();
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                accessGoogleFitData();
                handler.postDelayed(this, REFRESH_INTERVAL);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        startRefreshHandler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRefreshHandler();
    }

    private void startRefreshHandler() {
        handler.postDelayed(refreshRunnable, REFRESH_INTERVAL);
    }

    private void stopRefreshHandler() {
        handler.removeCallbacks(refreshRunnable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                accessGoogleFitData();
                startRefreshHandler();
            } else {
                // Permission denied by the user
                Toast.makeText(MainActivity.this, "This app not work Without Permission", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                requestFitPermissions();

            } else {
                // Permission is not granted
                Toast.makeText(MainActivity.this, "This app not work Without Permission", Toast.LENGTH_SHORT).show();
                requestFitPermissions();

            }
        }
    }

}
