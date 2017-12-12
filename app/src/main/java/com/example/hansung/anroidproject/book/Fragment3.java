package com.example.hansung.anroidproject.book;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.model.BookModel;
import com.example.hansung.anroidproject.model.Stylist;
import com.example.hansung.anroidproject.shop.Fragment1;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
  HomeActivity.java에서 3번째) 예약현황 탭
 */
public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";

    private View rooview;
    private RecyclerView recyclerView;

    private List<BookModel> bookModels = new ArrayList<>();
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;
    private BookRecyclerViewAdapter bookRecyclerViewAdapter;

    public static final String TITLE = "예약 현황";

    public static Fragment3 newInstance() {

        return new Fragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rooview = (ViewGroup) inflater.inflate(R.layout.activity_fragment3, container, false);
        database = FirebaseDatabase.getInstance();

        recyclerView = (RecyclerView) rooview.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookRecyclerViewAdapter = new BookRecyclerViewAdapter();
        recyclerView.setAdapter(bookRecyclerViewAdapter);
        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.getReference().child("Book").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //데이터가 바뀔 때마다 날라옴.
                bookModels.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BookModel bookModel = snapshot.getValue(BookModel.class);
                    if (bookModel.getBookUser() == myUid) {
                        Log.d(TAG, "bookModel.getBookUser() == myUid 입니다.");
                        bookModels.add(bookModel);
                    }

                }
                bookRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rooview;
    }

    class BookRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);


            return new CustomerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CustomerViewHolder) holder).bookDate.setText(bookModels.get(position).getBookDate());
            ((CustomerViewHolder) holder).bookTime.setText(bookModels.get(position).getBookTime());
            ((CustomerViewHolder) holder).product.setText(bookModels.get(position).getProductName());
            ((CustomerViewHolder) holder).price.setText(bookModels.get(position).getProductPrice());
            ((CustomerViewHolder) holder).address.setText(bookModels.get(position).getStoreAddress());
            ((CustomerViewHolder) holder).storename.setText(bookModels.get(position).getShopName());
            ((CustomerViewHolder) holder).name.setText(bookModels.get(position).getStylistName());

           // Glide.with(holder.itemView.getContext()).load(bookModels.get(position).getBookStylist()).into(((CustomerViewHolder) holder).storeimage);
        }

        @Override
        public int getItemCount() {
            return bookModels.size();
        }

        private class CustomerViewHolder extends RecyclerView.ViewHolder {
            public TextView bookTime, bookDate, product, price, storename, name, address;
            public ImageView storeimage;

            public CustomerViewHolder(View view) {
                super(view);
                bookDate = (TextView) view.findViewById(R.id.bookDate);
                bookTime = (TextView) view.findViewById(R.id.bookTime);
                product = (TextView) view.findViewById(R.id.productName);
                price = (TextView) view.findViewById(R.id.productPrice);
                address = (TextView) view.findViewById(R.id.address);
                storename = (TextView) view.findViewById(R.id.storename);
                name = (TextView) view.findViewById(R.id.name);
               // storeimage = (ImageView) view.findViewById(R.id.storeimage);
            }
        }
    }
}
