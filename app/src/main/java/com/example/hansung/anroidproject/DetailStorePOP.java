package com.example.hansung.anroidproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class DetailStorePOP extends Activity {

    private TextView ProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_store_pop);

        //UI 객체생성
        ProductName = (TextView) findViewById(R.id.ProductName);

        //데이터 가져오기
        Intent intent = getIntent();
        String name = intent.getStringExtra("ProductName");
        String price = intent.getStringExtra("ProductPrice");
        ProductName.setText("[ "+name +" ] "+price+" 입니다.");
    }

    //예약하기 버튼 클릭
    public void reservationButton(View v) {
        //데이터 전달하기
//        Intent intent = new Intent();
//        intent.putExtra("result", "Close Popup");
//        setResult(RESULT_OK, intent);
//
//        //액티비티(팝업) 닫기
//        finish();
        Toast.makeText(this,"예약하기 누름",Toast.LENGTH_SHORT).show();
    }

    //예약하기 버튼 클릭
    public void cancelButton(View v) {
        //액티비티(팝업) 닫기
        finish();
        Toast.makeText(this,"취소 누름",Toast.LENGTH_SHORT).show();
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
