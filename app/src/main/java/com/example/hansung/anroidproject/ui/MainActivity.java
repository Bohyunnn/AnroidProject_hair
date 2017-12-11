package com.example.hansung.anroidproject.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.auth.LoginActivity;

/*
    로딩화면
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*타이틀 바 제거*/
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Intent intent=new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);

            }
        }).start();
    }
}
