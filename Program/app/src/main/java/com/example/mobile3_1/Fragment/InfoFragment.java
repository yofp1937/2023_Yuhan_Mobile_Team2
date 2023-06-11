package com.example.mobile3_1.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mobile3_1.R;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Spinner spinner;
    LinearLayout linear_none;
    LinearLayout info_Linear_0;
    LinearLayout info_Linear_1;
    LinearLayout info_Linear_2;
    LinearLayout info_Linear_3;
    LinearLayout info_Linear_4;
    VideoView video_0;
    VideoView video_1;
    VideoView video_2;
    VideoView video_3;
    VideoView video_4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        //Spinner 설정
        spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.spinnerarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                Uri uri0 = Uri.parse("https://youtu.be/Fk9j6pQ6ej8");
                Uri uri1 = Uri.parse("https://youtu.be/EBjYQeeBI-0");
                Uri uri2 = Uri.parse("https://youtu.be/0DsXTSHo3lU");
                Uri uri3 = Uri.parse("https://youtu.be/aoH7qNedO8k");
                Uri uri4 = Uri.parse("https://youtu.be/kNv-0UEUb2Q");
                switch (position){
                    case 0:
                        linear_none.setVisibility(View.INVISIBLE);
                        info_Linear_0.setVisibility(View.VISIBLE);
                        info_Linear_1.setVisibility(View.INVISIBLE);
                        info_Linear_2.setVisibility(View.INVISIBLE);
                        info_Linear_3.setVisibility(View.INVISIBLE);
                        info_Linear_4.setVisibility(View.INVISIBLE);
                        //0번 스쿼트
                        video_0.setMediaController(new MediaController(getActivity()));
                        video_0.requestFocus();
                        video_0.setVideoURI(uri0);
                        video_0.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                video_0.start();
                            }
                        });
                        video_1.stopPlayback();
                        video_2.stopPlayback();
                        video_3.stopPlayback();
                        video_4.stopPlayback();
                        break;
                    case 1:
                        linear_none.setVisibility(View.INVISIBLE);
                        info_Linear_0.setVisibility(View.INVISIBLE);
                        info_Linear_1.setVisibility(View.VISIBLE);
                        info_Linear_2.setVisibility(View.INVISIBLE);
                        info_Linear_3.setVisibility(View.INVISIBLE);
                        info_Linear_4.setVisibility(View.INVISIBLE);
                        //1번 데드리프트
                        video_1.setMediaController(new MediaController(getActivity()));
                        video_1.requestFocus();
                        video_1.setVideoURI(uri1);
                        video_1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                video_1.start();
                            }
                        });
                        video_0.stopPlayback();
                        video_2.stopPlayback();
                        video_3.stopPlayback();
                        video_4.stopPlayback();
                        break;
                    case 2:
                        linear_none.setVisibility(View.INVISIBLE);
                        info_Linear_0.setVisibility(View.INVISIBLE);
                        info_Linear_1.setVisibility(View.INVISIBLE);
                        info_Linear_2.setVisibility(View.VISIBLE);
                        info_Linear_3.setVisibility(View.INVISIBLE);
                        info_Linear_4.setVisibility(View.INVISIBLE);
                        //2번 벤치프레스
                        video_2.setMediaController(new MediaController(getActivity()));
                        video_2.requestFocus();
                        video_2.setVideoURI(uri2);
                        video_2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                video_2.start();
                            }
                        });
                        video_0.stopPlayback();
                        video_1.stopPlayback();
                        video_3.stopPlayback();
                        video_4.stopPlayback();
                        break;
                    case 3:
                        linear_none.setVisibility(View.INVISIBLE);
                        info_Linear_0.setVisibility(View.INVISIBLE);
                        info_Linear_1.setVisibility(View.INVISIBLE);
                        info_Linear_2.setVisibility(View.INVISIBLE);
                        info_Linear_3.setVisibility(View.VISIBLE);
                        info_Linear_4.setVisibility(View.INVISIBLE);
                        //3번 푸쉬업
                        video_3.setMediaController(new MediaController(getActivity()));
                        video_3.requestFocus();
                        video_3.setVideoURI(uri3);
                        video_3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                video_3.start();
                            }
                        });
                        video_0.stopPlayback();
                        video_1.stopPlayback();
                        video_2.stopPlayback();
                        video_4.stopPlayback();
                        break;
                    case 4:
                        linear_none.setVisibility(View.INVISIBLE);
                        info_Linear_0.setVisibility(View.INVISIBLE);
                        info_Linear_1.setVisibility(View.INVISIBLE);
                        info_Linear_2.setVisibility(View.INVISIBLE);
                        info_Linear_3.setVisibility(View.INVISIBLE);
                        info_Linear_4.setVisibility(View.VISIBLE);
                        //4번 힙쓰러스트
                        video_4.setMediaController(new MediaController(getActivity()));
                        video_4.requestFocus();
                        video_4.setVideoURI(uri4);
                        video_4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                video_4.start();
                            }
                        });
                        video_0.stopPlayback();
                        video_1.stopPlayback();
                        video_2.stopPlayback();
                        video_3.stopPlayback();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                linear_none.setVisibility(View.VISIBLE);
                info_Linear_0.setVisibility(View.INVISIBLE);
                info_Linear_1.setVisibility(View.INVISIBLE);
                info_Linear_2.setVisibility(View.INVISIBLE);
                info_Linear_3.setVisibility(View.INVISIBLE);
                info_Linear_4.setVisibility(View.INVISIBLE);
                video_0.stopPlayback();
                video_1.stopPlayback();
                video_2.stopPlayback();
                video_3.stopPlayback();
                video_4.stopPlayback();
            }
        });

        linear_none = v.findViewById(R.id.info_Linear_none);
        info_Linear_0 = v.findViewById(R.id.info_Linear_0);
        info_Linear_1 = v.findViewById(R.id.info_Linear_1);
        info_Linear_2 = v.findViewById(R.id.info_Linear_2);
        info_Linear_3 = v.findViewById(R.id.info_Linear_3);
        info_Linear_4 = v.findViewById(R.id.info_Linear_4);
        video_0 = v.findViewById(R.id.video_0);
        video_1 = v.findViewById(R.id.video_1);
        video_2 = v.findViewById(R.id.video_2);
        video_3 = v.findViewById(R.id.video_3);
        video_4 = v.findViewById(R.id.video_4);

        return v;
    }

}