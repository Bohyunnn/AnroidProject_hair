package com.example.hansung.anroidproject.detailShop;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hansung.anroidproject.auth.stylelistSignupActivity;
import com.example.hansung.anroidproject.model.BookModel;
import com.example.hansung.anroidproject.model.Customer;
import com.example.hansung.anroidproject.model.Stylist;
import com.example.hansung.anroidproject.ui.HomeActivity;
import com.example.hansung.anroidproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

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

    private Stylist stylist;

    private String name, price, stylistuid, profileImageUrl, ShopName, StylistName, userid,StoreAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_store_pop__book);

        //데이터 가져옴

        /*
         제품 이름, 제품 가격, 미용사 아이디
         */

        Intent intent = getIntent();
        name = intent.getStringExtra("ProductName");
        price = intent.getStringExtra("ProductPrice");
        profileImageUrl = getIntent().getStringExtra("StylistImageUrl");
        ShopName = getIntent().getStringExtra("StoreName");
        StylistName = getIntent().getStringExtra("StylistName");
        StoreAddress = intent.getStringExtra("StoreAddress");
        stylistuid = intent.getStringExtra("destinationUid");     //미용사 아이디
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();  //현재 로그인된 사용자 아이디

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
        Date = String.format("%d년 %d월 %d일", mYear, mMonth + 1, mDay);
        mTxtDate.setText(Date);
        Time = String.format("%d시 %d분", mHour, mMinute);
        mTxtTime.setText(Time);
    }

    //예약하기 버튼 클릭
    public void reservationButton(View v) {
        //데이터 전달하기
//        Intent intent = new Intent();
//        intent.putExtra("result", "Close Popup");
//        setResult(RESULT_OK, intent);
//
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        //데이터 저장
//        intent.putExtra("ProductName", name);
//        intent.putExtra("ProductPrice", price);
//        intent.putExtra("destinationUid", stylistuid);

        Toast.makeText(this, "name=" + name + ", price=" + price + ", uid=" + stylistuid + " 예약하기", Toast.LENGTH_SHORT).show();
//파이어베이스에 예약 데이터 넣기
        BookModel bookModel = new BookModel();

        String myUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        stylist = new Stylist();
//        FirebaseDatabase.getInstance().getReference().child("stylist").child(stylistuid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    stylist.setUid(stylistuid);
//                    stylist.setShopName(snapshot.child("stylistName").getValue(String.class));
//                    stylist.setStylistEmail(snapshot.child("stylistEmail").getValue(String.class));
//                    stylist.setProfileImageUrl(snapshot.child("profileImageUrl").getValue(String.class));
//                    stylist.setStylistName(snapshot.child("stylistName").getValue(String.class));
//                    stylist.setStylistAddress(snapshot.child("stylistAddress").getValue(String.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        bookModel.setBookUser(myUID);
        bookModel.setBookStylist(stylistuid);

        bookModel.setProductName(name);
        bookModel.setProductPrice(price);

        bookModel.setBookDate(Date);
        bookModel.setBookTime(Time);

        bookModel.setShopName(ShopName);
        bookModel.setStylistName(StylistName);
        bookModel.setStoreAddress(StoreAddress);

        FirebaseDatabase.getInstance().getReference().child("Book").push().setValue(bookModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

// ------------------------------
//        startActivity(intent);
        //Toast.makeText(this, "예약하기 누름", Toast.LENGTH_SHORT).show();
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
