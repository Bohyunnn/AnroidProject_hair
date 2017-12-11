package com.example.hansung.anroidproject.detailShop;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hansung.anroidproject.ui.HomeActivity;
import com.example.hansung.anroidproject.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
    예약 하기 선택했을 때 시간 , 날짜 고르기
 */
public class DetailStorePOP_Book extends Activity {

    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextView mTxtDate;
    private TextView mTxtTime;
    String Date;
    String Time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_store_pop__book);

        //텍스트뷰 2개 연결
        mTxtDate = (TextView) findViewById(R.id.mTxtDate);
        mTxtTime = (TextView) findViewById(R.id.mTxtTime);


        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        UpdateNow();//화면에 텍스트뷰에 업데이트 해줌.
    }


    public void mOnClick(View v) {
        switch (v.getId()) {
            //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.dateButton:
                new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay).show();
                break;

            //시간 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.timeButton:
                new TimePickerDialog(this, mTimeSetListener, mHour,
                        mMinute, false).show();
                break;
        }
    }


    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;

                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
            };


    //시간 대화상자 리스너 부분
    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mHour = hourOfDay;
                    mMinute = minute;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
            };

    //텍스트뷰의 값을 업데이트 하는 메소드
    void UpdateNow() {
        Date=String.format("%d년 %d월 %d일", mYear, mMonth + 1, mDay);
        mTxtDate.setText(Date);
        Time=String.format("%d시 %d분", mHour, mMinute);
        mTxtTime.setText(Time);
    }

    //예약하기 버튼 클릭
    public void reservationButton(View v) {
        //데이터 전달하기
//        Intent intent = new Intent();
//        intent.putExtra("result", "Close Popup");
//        setResult(RESULT_OK, intent);
//
        Intent intent=new Intent(this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(this, "예약하기 누름", Toast.LENGTH_SHORT).show();
    }

    //취소버튼
    public void cancelButton(View v) {
        //액티비티(팝업) 닫기
        finish();
        Toast.makeText(this, "취소 누름", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }


}
