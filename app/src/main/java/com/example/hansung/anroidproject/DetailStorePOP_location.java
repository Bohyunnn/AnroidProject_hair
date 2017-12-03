package com.example.hansung.anroidproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DetailStorePOP_location extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private static double lat, lon; //'서울역'의 위도, 경도
    private String storename, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store_pop_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleText = (TextView) findViewById(R.id.titleText);

        Intent intent = getIntent();
        storename = intent.getStringExtra("storename");
        location = intent.getStringExtra("location");
        titleText.setText(location);

        //지도
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.
    }


    //예약하기 버튼 클릭
    public void cancelButton(View v) {
        //액티비티(팝업) 닫기
        finish();
        Toast.makeText(this, "취소 누름", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        map = googleMap;

        //미용실 위치 경도, 위도로 변경
        findGeoPoint(this, location);

        //미용실 위치
        LatLng SerchLocation = new LatLng(lat, lon);

        // 구글 맵에 표시할 마커에 대한 옵션 설정
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(SerchLocation)
                .title("[ "+storename+" ]")
                .snippet(location);

        // 마커를 생성한다.
        map.addMarker(makerOptions);

        //카메라를 위치를 미용실 위치로 옮김.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SerchLocation, 18));
    }

    /**
     * 주소로부터 위치정보 취득
     *
     * @param address 주소
     */
    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                lat = lating.getLatitude(); // 위도가져오기
                lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;
    }

    //ActionBar's Back Button
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
