package com.example.countdowntimerjava;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.countdowntimerjava.databinding.ActivityMainBinding;
import com.example.countdowntimerjava.fragments.alarmclock.FragmentAlarmClock;
import com.example.countdowntimerjava.fragments.realtimeclock.FragmentRealTimeClock;
import com.example.countdowntimerjava.fragments.stopwatch.FragmentStopwatch;
import com.example.countdowntimerjava.fragments.timer.FragmentTimer;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBottomNavigation();
    }

    @SuppressLint("NonConstantResourceId")
    private void initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.alarmClock:
                    fragmentSupportManager(new FragmentAlarmClock());
                    return true;
                case R.id.realTimeClock:
                    fragmentSupportManager(new FragmentRealTimeClock());
                    return true;
                case R.id.timer:
                    fragmentSupportManager(new FragmentTimer());
                    return true;
                case R.id.stopwatch:
                    fragmentSupportManager(new FragmentStopwatch());
                    return true;
                default:
                    return false;
            }
        });
    }

    private void fragmentSupportManager(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }
}
