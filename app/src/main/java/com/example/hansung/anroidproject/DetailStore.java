package com.example.hansung.anroidproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailStore extends AppCompatActivity {
    TextView textView1, textView2, textView3;
    ImageView imageView;
    Intent intent;
    private int storeimage;
    private String name, storeName, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("스타일");


        imageView = (ImageView) findViewById(R.id.imageView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        intent = getIntent();

        storeimage = Integer.parseInt(intent.getStringExtra("storeimage"));
        imageView.setImageResource(storeimage);
        textView1.setText(intent.getStringExtra("name"));
        textView2.setText(intent.getStringExtra("storename"));
        textView3.setText("주소 " + intent.getStringExtra("address"));
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
