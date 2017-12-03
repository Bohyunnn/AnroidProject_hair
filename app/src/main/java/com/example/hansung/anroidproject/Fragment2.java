package com.example.hansung.anroidproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class Fragment2 extends Fragment implements OnMapReadyCallback {
    private ViewGroup rootView;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do = "";
    String choice_se = "";

    private GoogleMap map;
    private LatLng Storelocation;

    public static final String TITLE = "위치 검색";

    public static Fragment2 newInstance() {

        return new Fragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment2, container, false);

        //스피너 설정
        final MaterialSpinner spin1 = (MaterialSpinner) rootView.findViewById(R.id.spinner);
        final MaterialSpinner spin2 = (MaterialSpinner) rootView.findViewById(R.id.spinner2);

        Button btn_refresh = (Button) rootView.findViewById(R.id.btn_refresh);
        //final=> spin2가 함수안에서 사용되기 때문에 코딩전체로 선언.

        adspin1 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do, R.layout.custom_simple_dropdown_item_line1);

        spin1.setAdapter(adspin1);

        spin1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                //spin1 누르면 발생하는 이벤트
                //서울인 경우
                if (adspin1.getItem(position).equals("서울")) {
                    choice_do = "서울";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                    });
                }
                //인천인 경우
                else if (adspin1.getItem(position).equals("인천")) {
                    choice_do = "인천";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                            choice_se = adspin2.getItem(position).toString();
                        }
                    });
                }
                //경기도인 경우
                else if (adspin1.getItem(position).equals("경기도")) {
                    choice_do = "경기도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                    });
                }
            }

        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {//버튼 클릭시 이벤트입니다.
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " " + choice_do + " " + choice_se + " 검색합니다.", Snackbar.LENGTH_LONG).show();
            }
        });
        //--------------------------------------------------
        // 지도 설정
        //supportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출함,
        //Fragment에서 실행시 getFragmentManager()가 아닌 getChildFragmentManager()사용
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.


        return rootView;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        map = googleMap;

        // 서울 여의도에 대한 위치 설정
        //마커 위치에 대한 정보=> LatLng
        Storelocation = new LatLng(37.52487, 126.92723);

        //카메라를 여의도 위치로 옮긴다.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Storelocation, 15));

        onAddMarker();
    }

    //마커 , 원추가
    public void onAddMarker() {
        // 구글 맵에 표시할 마커에 대한 옵션 설정
        //.title  (마커 클릭시 정보 창에 표시되는 문자열)
        //.spinnet (제목 아래 추가 텍스트)
        //.Icon  (아이콘)
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(Storelocation)
                .icon(BitmapDescriptorFactory.defaultMarker(200f))
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .snippet("제목 아래 추가 텍스트");

        // 마커를 생성한다.
        map.addMarker(makerOptions);

        //정보창 클릭 리스너
        map.setOnInfoWindowClickListener(infoWindowClickListener);

        //마커 클릭 리스너
        map.setOnMarkerClickListener(markerClickListener);
    }

    //정보창 클릭 리스너
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();
            Toast.makeText(getContext(), "정보창 클릭 Marker ID : " + markerId, Toast.LENGTH_SHORT).show();
        }
    };

    //마커 클릭 리스너
    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            String markerId = marker.getId();
            //선택한 타겟위치
            LatLng location = marker.getPosition();
            Toast.makeText(getContext(), "마커 클릭 Marker ID : " + markerId + "(" + location.latitude + " " + location.longitude + ")", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getContext(),"선택한 위치의 Rookie를 검색합니다.",Toast.LENGTH_SHORT).show();

            return false;
        }
    };

}
