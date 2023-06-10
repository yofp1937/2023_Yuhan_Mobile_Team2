package com.example.mobile3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobile3_1.Fragment.InfoFragment;
import com.example.mobile3_1.Fragment.RecordFragment;
import com.example.mobile3_1.Fragment.TimerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    //네비게이션바, 프래그먼트
    BottomNavigationView bottomNavigationView;
    TimerFragment timerFragment;
    InfoFragment infoFragment;
    RecordFragment recordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //네비게이션바, 프래그먼트 생성
        bottomNavigationView = findViewById(R.id.bottom_navi);
        timerFragment = new TimerFragment();
        infoFragment = new InfoFragment();
        recordFragment = new RecordFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, timerFragment).commitAllowingStateLoss();

        //네비게이션바 클릭리스너
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_timer) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, timerFragment).commitAllowingStateLoss();
                return true;
            }else if(item.getItemId() == R.id.bottom_info) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, infoFragment).commitAllowingStateLoss();
                return true;
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, recordFragment).commitAllowingStateLoss();
                return true;
            }
        });
    }
}