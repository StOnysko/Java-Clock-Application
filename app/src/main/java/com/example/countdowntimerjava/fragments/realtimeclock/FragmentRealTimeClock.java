package com.example.countdowntimerjava.fragments.realtimeclock;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.LocaleManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countdowntimerjava.databinding.FragmentRealTimeClockBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentRealTimeClock extends Fragment {

    private FragmentRealTimeClockBinding binding;
    private Runnable runnable;
    private final Handler handler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRealTimeClockBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLocaleTime(Locale.getDefault());
        checkLocationPermission();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        handler.removeCallbacks(runnable);
    }

    private void setLocaleTime(Locale localeTime) {
        if (checkLocationPermission()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", localeTime);
                    String currentTime = sdf.format(new Date());
                    handler.postDelayed(this, 0);
                    binding.time.setText(currentTime);
                }
            };
            handler.post(runnable);
        } else {
            binding.time.setText("Permission not granted");
        }
    }

    // asking for location permission
    private boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
        return false;
    }
}