package com.example.hansung.anroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ServiceContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
