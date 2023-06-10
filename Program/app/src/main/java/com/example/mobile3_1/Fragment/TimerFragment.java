package com.example.mobile3_1.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobile3_1.MainActivity;
import com.example.mobile3_1.R;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //프래그먼트 타이머
    private static final int MAXTIME = 2 * 60 * 60000; // 1000=1초, 60000=1분, 7200000=2시간
    enum TimerValue {
        STARTED,
        STOPPED
    }
    TimerValue timerValue = TimerValue.STOPPED;
    ProgressBar progressBarCircle;
    TextView textViewTime;
    Button btnTimerStart;
    Button btnTimerStop;
    CountDownTimer countDownTimer;

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(7200000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //시간측정 카운트다운말고 다시짜야함
            }

            @Override
            public void onFinish() {
                textViewTime.setText(hmsTimeFormatter(0));
                progressBarCircle.setProgress(MAXTIME);
                timerValue = TimerValue.STOPPED;
            }
        };
        countDownTimer.start();
    }

    @SuppressLint("DefaultLocale")
    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container, false);

        //타이머 프래그먼트
        progressBarCircle = v.findViewById(R.id.progressBarCircle);
        textViewTime = v.findViewById(R.id.textViewTime);
        btnTimerStart = v.findViewById(R.id.btnTimerStart);
        btnTimerStop = v.findViewById(R.id.btnTimerStop);

        View.OnClickListener timerListener = view -> {
            switch (view.getId()){
                case R.id.btnTimerStart:
                    timerValue = TimerValue.STARTED;
                    startCountDownTimer();
                    btnTimerStart.setVisibility(View.INVISIBLE);
                    btnTimerStop.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnTimerStop:
                    timerValue = TimerValue.STOPPED;
                    countDownTimer.cancel();
                    btnTimerStart.setVisibility(View.VISIBLE);
                    btnTimerStop.setVisibility(View.INVISIBLE);
                    break;
            }
        };

        //온클릭리스너 넣으면 실행안됨
        btnTimerStart.setOnClickListener(timerListener);
        btnTimerStop.setOnClickListener(timerListener);

        // Inflate the layout for this fragment
        return v;
    }
}