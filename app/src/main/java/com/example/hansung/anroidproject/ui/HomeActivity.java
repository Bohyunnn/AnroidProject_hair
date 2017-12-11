package com.example.hansung.anroidproject.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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

import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.auth.LoginActivity;
import com.example.hansung.anroidproject.navi.ServiceContact;
import com.example.hansung.anroidproject.navi.ServiceIntro;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        //auth에서 name과 email 받아옴.
        mHeaderProfileImage.setImageURI(auth.getCurrentUser().getPhotoUrl());
        mHeaderName.setText(auth.getCurrentUser().getDisplayName());
        mHeaderEmail.setText(auth.getCurrentUser().getEmail());
        mHeaderAuthority.setText(auth.getCurrentUser().getProviderId());
        //


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
        else if (id == R.id.nav_logout) {
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
}