package com.example.countdowntimerjava.fragments.timer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countdowntimerjava.R;
import com.example.countdowntimerjava.databinding.FragmentTimerBinding;
import com.google.android.material.snackbar.Snackbar;

public class FragmentTimer extends Fragment {
    private static final int COUNTDOWN_INTERVAL = 1000;
    FragmentTimerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startClock();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void startClock() {
        binding.startTimerBtn.setOnClickListener(view -> new CountDownTimer(7000, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long l) {
                int time = (int) l / COUNTDOWN_INTERVAL;
                binding.circularProgressIndicator.setProgress(time, false);
                binding.startTimerBtn.setVisibility(View.GONE);
                binding.timerTextView.setText(Integer.toString(time));
            }

            @Override
            public void onFinish() {
                Snackbar.make(
                                binding.fragmentTopBar, "The timer has finished counting down!", Snackbar.LENGTH_SHORT
                        )
                        .setTextColor(getResources().getColor(R.color.white))
                        .setBackgroundTint(getResources().getColor(R.color.orange))
                        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                        .show();
                binding.startTimerBtn.setVisibility(View.VISIBLE);
            }
        }.start());
    }
}