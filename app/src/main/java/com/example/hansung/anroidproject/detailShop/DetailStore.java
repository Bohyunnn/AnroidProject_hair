package com.example.hansung.anroidproject.detailShop;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.deprecated.model.Product;
import com.example.hansung.anroidproject.model.Stylist;
import com.example.hansung.anroidproject.shop.Fragment1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailStore extends AppCompatActivity {
    TextView textView1, textView2, textView3;
    ImageView imageView;
    Intent intent;
    private String storeimage;

    private DetailStoreAdapter adapter;
    private List<Product> productList;
    private RecyclerView recyclerView;

    private Button locationSearch; //지도 검색
    private String storename,location;
    private  Stylist stylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("스타일");
/*
   intent.putExtra("StylistImageUrl", userModels.get(position).getProfileImageUrl());
        intent.putExtra("StoreName", userModels.get(position).getShopName());
        intent.putExtra("StylistName", userModels.get(position).getStylistName());
        intent.putExtra("destinationUid", userModels.get(position).getUid());

 */
        storeimage=getIntent().getStringExtra("StylistImageUrl");
        String ShopName=getIntent().getStringExtra("StoreName");
        String StylistName=getIntent().getStringExtra("StylistName");
        String uid=getIntent().getStringExtra("destinationUid");
        String StoreAddress=getIntent().getStringExtra("StoreAddress");

        FirebaseDatabase.getInstance().getReference().child("stylist").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stylist=dataSnapshot.getValue(Stylist.class);

                imageView = (ImageView) findViewById(R.id.imageView);
              //  Glide.with(DetailStore.this).load(stylist.getProfileImageUrl()).into(imageView);
                Glide.with(DetailStore.this).load(stylist.getProfileImageUrl()).into((ImageView) findViewById(R.id.imageView));
               // imageView.setImageResource(stylist.getProfileImageUrl());

                textView1 = (TextView) findViewById(R.id.textView1);
                textView1.setText(stylist.getShopName());
                textView2 = (TextView) findViewById(R.id.textView2);
                textView2.setText(stylist.getStylistAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        /*
         String profileImageUrl=getIntent().getStringExtra("StylistImageUrl");
        String ShopName=getIntent().getStringExtra("StoreName");
        String StylistName=getIntent().getStringExtra("StylistName");
        String uid=getIntent().getStringExtra("destinationUid");

         */

        productList = new ArrayList<>();
        adapter = new DetailStoreAdapter(this, productList,storeimage,ShopName,StoreAddress,StylistName, uid);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DetailStore.GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(storeimage).into((ImageView) findViewById(R.id.imageView));
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
        Toast.makeText(this, stylist.getStylistAddress() + " 지도를 보여줘!", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, DetailStorePOP_location.class);
        intent2.putExtra("storename",stylist.getShopName());
        intent2.putExtra("location", stylist.getStylistAddress());
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
