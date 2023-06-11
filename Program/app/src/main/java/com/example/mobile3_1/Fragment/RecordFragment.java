package com.example.mobile3_1.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile3_1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //기본 화면 위젯들
    DatePicker datePicker;
    EditText edDiary;
    Button btnSave;
    //TextView test;

    //선택된 날짜 이름의 파일명
    String fileName;

    //타이머 프래그먼트에서 전달받는 시간 저장
    String time;

    //선택된 날짜에 파일이 없으면 toast 메세지 표시
    private void checkedDay(int year, int month, int day){
        fileName = year + "" + month + "" + day + ".txt";

        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput(fileName);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            String str = new String(fileData, "UTF-8");
            // 읽어서 토스트 메시지로 보여줌
            Toast.makeText(getActivity().getApplicationContext(), "불러오기", Toast.LENGTH_SHORT).show();
            edDiary.setText(str);
            btnSave.setText("수정");
        } catch (Exception e) { // UnsupportedEncodingException , FileNotFoundException , IOException
            // 없어서 오류가 나면 기록이 없는 것
            Toast.makeText(getActivity().getApplicationContext(), "기록이 없는 날입니다", Toast.LENGTH_SHORT).show();
            edDiary.setText("");
            btnSave.setText("저장");
            e.printStackTrace();
        }

    }

    //EditText에 입력된 내용을 파일로 저장
    private void saveRecord(String readDay){
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_PRIVATE);
            String content = edDiary.getText().toString();

            // String.getBytes() = 스트링을 배열형으로 변환
            fos.write(content.getBytes());
            fos.close();

            Toast.makeText(getActivity().getApplicationContext(), "기록 저장됨", Toast.LENGTH_SHORT).show();

        } catch (Exception e) { // Exception - 에러 종류 제일 상위 // FileNotFoundException , IOException
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), "오류 발생", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record, container, false);

        //위젯 리턴
        datePicker = v.findViewById(R.id.datePicker);
        edDiary = v.findViewById(R.id.edDiary);
        btnSave = v.findViewById(R.id.btnSave);
        //test = v.findViewById(R.id.test);

        //오늘 날짜 반환
        Calendar c = Calendar.getInstance();
        int cYear = c.get(Calendar.YEAR);
        int cMonth = c.get(Calendar.MONTH);
        int cDay = c.get(Calendar.DAY_OF_MONTH);
        checkedDay(cYear,cMonth,cDay);

        //타이머 프래그먼트에서 전달받은 값이 존재하면 EditText에 설정
        if(getArguments() != null) {
            time = getArguments().getString("time");
            edDiary.setText(time + " 동안 운동함");
            //test.setText(time + " 동안 운동함");
        }
        /*else if (getArguments() == null){
            edDiary.setText(time + "null");
            test.setText("null");
        }*/

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                checkedDay(year, month, day);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecord(fileName);
            }
        });


        return v;
    }
}