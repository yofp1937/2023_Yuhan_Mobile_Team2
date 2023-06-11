package com.example.mobile3_1.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobile3_1.MainActivity;
import com.example.mobile3_1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    ProgressBar progressBarCircle;
    TextView textViewTime;
    Button btnTimerStart;
    Button btnTimerStop;
    Button btnTimerEnd;

    //상태를 표시하는 '상수' 지정
    public static final int INIT = 0;//처음
    public static final int RUN = 1;//실행중
    public static final int PAUSE = 2;//정지

    //상태값을 저장하는 변수
    public static int status = INIT;

    private int progress = 3600000;
    String timeList;

    //타이머 시간 값을 저장할 변수
    private long baseTime,pauseTime;

    //RecordFragment로 정보를 전송할 Bundle
    Bundle bundle;
    RecordFragment recordFragment;

    private void startbtn(){
        baseTime = SystemClock.elapsedRealtime();
        handler.sendEmptyMessage(0);
        status = RUN;
    }

    private void stopbtn(){
        switch (status){
            case RUN:
                handler.removeMessages(0);
                pauseTime = SystemClock.elapsedRealtime();
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);
                handler.sendEmptyMessage(0);
                status = RUN;
                break;
        }
    }

    private void endbtn(){
        handler.removeMessages(0);
        timeList = textViewTime.getText().toString();
        textViewTime.setText(timeList);
        baseTime = 0;
        pauseTime = 0;
        status = INIT;
        progress = 3600000;
    }

    private String getTime(){
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;

        long h = overTime/1000/60/60;
        long m = overTime/1000/60;
        long s = (overTime/1000)%60;

        if(progress > 0)//주황색 게이지 꽉차면 시간만 바뀌게 설정
        progressBarCircle.setProgress(progress-(int)overTime);

        String recTime = String.format("%02d:%02d:%02d",h,m,s);

        return recTime;
    }

    Handler handler = new Handler(){

        @NonNull
        @Override
        public void handleMessage(@NonNull Message msg) {
            textViewTime.setText(getTime());
            handler.sendEmptyMessage(0);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        btnTimerEnd = v.findViewById(R.id.btnTimerEnd);
        recordFragment = new RecordFragment();
        FragmentTransaction transcation = getActivity().getSupportFragmentManager().beginTransaction();

        View.OnClickListener timerListener = view -> {
            switch (view.getId()){
                case R.id.btnTimerStart:
                    startbtn();
                    btnTimerStart.setVisibility(View.INVISIBLE);
                    btnTimerStop.setVisibility(View.VISIBLE);
                    btnTimerEnd.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnTimerStop:
                    stopbtn();
                    break;
                case R.id.btnTimerEnd:
                    endbtn();
                    bundle = new Bundle();
                    bundle.putString("time", timeList);
                    recordFragment.setArguments(bundle);
                    transcation.replace(R.id.fragmentFrame, recordFragment);
                    transcation.commit();
                    btnTimerStart.setVisibility(View.VISIBLE);
                    btnTimerStop.setVisibility(View.INVISIBLE);
                    btnTimerEnd.setVisibility(View.INVISIBLE);
                    break;
            }
        };

        //온클릭리스너 넣으면 실행안됨
        btnTimerStart.setOnClickListener(timerListener);
        btnTimerStop.setOnClickListener(timerListener);
        btnTimerEnd.setOnClickListener(timerListener);

        // Inflate the layout for this fragment
        return v;
    }
}