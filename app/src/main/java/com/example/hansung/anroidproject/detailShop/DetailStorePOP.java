package com.example.hansung.anroidproject.detailShop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hansung.anroidproject.R;

/*
    예약 버튼 예, 아니오
 */
public class DetailStorePOP extends Activity {

    private TextView ProductName;
    private String destinationUid, profileImageUrl, ShopName, StylistName, StoreAddress;
    private String name;
    private String price;

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

        name = intent.getStringExtra("ProductName");
        price = intent.getStringExtra("ProductPrice");
        profileImageUrl = getIntent().getStringExtra("StylistImageUrl");
        ShopName = getIntent().getStringExtra("StoreName");
        StylistName = getIntent().getStringExtra("StylistName");
        StoreAddress = getIntent().getStringExtra("StoreAddress");
        destinationUid = intent.getStringExtra("destinationUid");

        ProductName.setText(ShopName+"의 [ " + name + " ] " + price + " 입니다.");
    }

    //예약하기 버튼 클릭
    public void reservationButton(View v) {
        //데이터 전달하기
//        Intent intent = new Intent();
//        intent.putExtra("result", "Close Popup");
//        setResult(RESULT_OK, intent);
//
        Intent intent = new Intent(this, DetailStorePOP_Book.class);
        intent.putExtra("StylistImageUrl", profileImageUrl);
        intent.putExtra("StoreName", ShopName);
        intent.putExtra("StylistName", StylistName);
        intent.putExtra("destinationUid", destinationUid);
        intent.putExtra("StoreAddress", StoreAddress);
        intent.putExtra("ProductName", name);
        intent.putExtra("ProductPrice", price);

        startActivity(intent);
        //Toast.makeText(this, destinationUid + " 예약하기 누름", Toast.LENGTH_SHORT).show();
    }

    //취소버튼
    public void cancelButton(View v) {
        //액티비티(팝업) 닫기
        finish();
        //Toast.makeText(this, "취소 누름", Toast.LENGTH_SHORT).show();
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