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

import com.jaredrummler.materialspinner.MaterialSpinner;

public class Fragment2 extends Fragment {
    private ViewGroup rootView;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do = "";
    String choice_se = "";

    public static final String TITLE = "위치 검색";

    public static Fragment2 newInstance() {

        return new Fragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment2, container, false);

        final Spinner spin1 = (Spinner) rootView.findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner) rootView.findViewById(R.id.spinner2);
        Button btn_refresh = (Button) rootView.findViewById(R.id.btn_refresh);
        //final=> spin2가 함수안에서 사용되기 때문에 코딩전체로 선언.

        adspin1 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //spin1 누르면 발생하는 이벤트
                //서울인 경우
                if (adspin1.getItem(position).equals("서울")) {
                    choice_do = "서울";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //아무것도 선택되지 않은 경우
                        }
                    });
                }
                //인천인 경우
                else if(adspin1.getItem(position).equals("인천")){
                    choice_do = "인천";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //아무것도 선택되지 않은 경우
                        }
                    });
                }
                //경기도인 경우
                else if(adspin1.getItem(position).equals("경기도")){
                    choice_do = "경기도";
                    adspin2 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_do_gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            choice_se = adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //아무것도 선택되지 않은 경우
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //아무것도 선택되지 않은 경우
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {//버튼 클릭시 이벤트입니다.
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), choice_do + "=" + choice_se, Toast.LENGTH_SHORT).show();
        }});

        return rootView;

    }
}
