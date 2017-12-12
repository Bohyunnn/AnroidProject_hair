package com.example.hansung.anroidproject.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.auth.LoginActivity;
import com.example.hansung.anroidproject.chat.ChatFragment;
import com.example.hansung.anroidproject.navi.ServiceContact;
import com.example.hansung.anroidproject.navi.ServiceIntro;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    /* nav-header-home에 보여질 현자 사용자 이름, 이메일, 권한 */
    private ImageView mHeaderProfileImage;
    private TextView mHeaderName;
    private TextView mHeaderEmail;
    private TextView mHeaderAuthority;

    private FirebaseAuth auth; //아이디랑 비밀번호 받아옴(싱글톤 패턴으로)

    //툴바
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance(); //받아옴

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Rookie Hair Shop");
        mToolbar.setTitleTextColor(Color.WHITE);

        setViewPager();

        //오른쪽 아래에 동그라미 표시된 버튼
        // => 맨위로 올리기 버튼이나 문자보내기 기능하면 좋을것 같음!!
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //

        //DrawerLayout 설정
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //nav_header_home.xml 설정
        View view = navigationView.getHeaderView(0);
        mHeaderProfileImage = (ImageView) view.findViewById(R.id.header_profile_imageView);
        mHeaderName = (TextView) view.findViewById(R.id.header_name_textView);
        mHeaderEmail = (TextView) view.findViewById(R.id.header_email_textView);
        mHeaderAuthority = (TextView) view.findViewById(R.id.header_authority_textView);

        /* auth.getCurrentUser()로는 없는 필드가 있고
         * 직접 Firebase로부터 가져와야하는듯.. */
        final String myUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // 1. 고객인 경우
        FirebaseDatabase.getInstance().getReference().child("users").child(myUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                    String shopName = dataSnapshot.child("shopName").getValue(String.class);
                    String stylistAddress = dataSnapshot.child("stylistAddress").getValue(String.class);
                    String stylistEmail = dataSnapshot.child("stylistEmail").getValue(String.class);
                    String stylistName = dataSnapshot.child("stylistName").getValue(String.class);

                    Log.d(TAG, "myUID : " + myUID);
                    Log.d(TAG, "profileImageUrl : " + profileImageUrl);
                    Log.d(TAG, "stylistEmail : " + stylistEmail);
                    Log.d(TAG, "stylistName : " + stylistName);

                    /* ui 변경 */
//                    mHeaderProfileImage.setImageBitmap(getImageBitmap(profileImage));
                    Glide.with(HomeActivity.this).load(profileImageUrl).into((ImageView) findViewById(R.id.header_profile_imageView));
                    mHeaderName.setText(stylistName);
                    mHeaderEmail.setText(stylistEmail);
                    mHeaderAuthority.setText("스타일리스트님!");
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        // 2. 스타일리스트인 경우
        FirebaseDatabase.getInstance().getReference().child("stylist").child(myUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                String profileImage = dataSnapshot.child("profileImagePath").getValue(String.class);
                String userEmail = dataSnapshot.child("userEmail").getValue(String.class);
                String userName = dataSnapshot.child("userName").getValue(String.class);

                Log.d(TAG, "myUID : " + myUID);
                Log.d(TAG, "profileImage : " + profileImage);
                Log.d(TAG, "userEmail : " + userEmail);
                Log.d(TAG, "userName : " + userName);

                    /* ui 변경 */
//                    mHeaderProfileImage.setImageBitmap(getImageBitmap(profileImage));
                Glide.with(HomeActivity.this).load(profileImage).into((ImageView) findViewById(R.id.header_profile_imageView));
                mHeaderName.setText(userName);
                mHeaderEmail.setText(userEmail);
                mHeaderAuthority.setText("고객님!");
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    //뒤로 가기 버튼 처리시
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    //res/menu/home.xml 부분
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

//    //res/menu/home.xml 부분
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //activity_home_drawer.xml 부분
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.service_Introduction) {
           //서비스 소개(ROOKIES 앱에 대한 소개)
            Intent intent = new Intent(this,ServiceIntro.class);
            startActivity(intent);

        } else if (id == R.id.service_Contact) {
            //문의하기(고객센터에 문의해서 이메일 보내 헤어디자이너로 변경)
            Intent intent = new Intent(this,ServiceContact.class);
            startActivity(intent);
        }
        else if (id == R.id.service_chatting){
            FragmentTransaction chatFragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = ChatFragment.newInstance();
            chatFragmentTransaction.add(R.id.chatting_content, fragment);
            chatFragmentTransaction.commit();
        } else if (id == R.id.nav_logout) {
           //로그아웃
            auth.signOut();
            LoginManager.getInstance().logOut(); //facebook logout 경우
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //툴바 설정
    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /* 프사 이미지 */
    private Bitmap getImageBitmap(String strUrl){
        Bitmap bitmap = null;
        try{
            URL url = new URL(strUrl);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e){
            Log.d(TAG, "Error getting bitmap", e);
        }
        return bitmap;
    }
}