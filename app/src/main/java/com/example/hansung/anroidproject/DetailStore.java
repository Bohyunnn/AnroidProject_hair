package com.example.hansung.anroidproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailStore extends AppCompatActivity {
    TextView textView1, textView2, textView3;
    ImageView imageView;
    Intent intent;
    private int storeimage;

    private DetailStoreAdapter adapter;
    private List<Product> productList;
    private RecyclerView recyclerView;

    private Button locationSearch; //지도 검색
    private String storename,location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("스타일");


        imageView = (ImageView) findViewById(R.id.imageView);
//        textView1 = (TextView) findViewById(R.id.textView1);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        intent = getIntent();

        storeimage = Integer.parseInt(intent.getStringExtra("storeimage"));
//        imageView.setImageResource(storeimage);
//        textView1.setText(intent.getStringExtra("name"));
        storename=intent.getStringExtra("storename");
        textView1.setText(storename);
        location = intent.getStringExtra("address");
        textView2.setText(location);

        //
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        productList = new ArrayList<>();
        adapter = new DetailStoreAdapter(this, productList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DetailStore.GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(storeimage).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        locationSearch = (Button) findViewById(R.id.locationSearch);

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


    //가격 Items 추가하는 부분
    private void prepareAlbums() {
        Product a = new Product("커트", 5000);
        productList.add(a);

        a = new Product("파마", 15000);
        productList.add(a);

        a = new Product("염색", 20000);
        productList.add(a);

        a = new Product("탈색", 20000);
        productList.add(a);
    }

    public void onLocationSearch(View view) {
        //지도 클릭시 지도 띄어줄 부분
        Toast.makeText(this, location + " 지도를 보여줘!", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, DetailStorePOP_location.class);
        intent2.putExtra("storename",storename);
        intent2.putExtra("location", location);
        startActivity(intent2);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
