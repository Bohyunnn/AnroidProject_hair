package com.example.hansung.anroidproject.book;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hansung.anroidproject.R;

import java.util.List;

/**
 * Created by kimsungmin on 2017-12-06.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context mContext;
    private List<Book> bookList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bookTime, storename, name, address;
        public ImageView storeimage;

        public MyViewHolder(View view) {
            super(view);
            bookTime=(TextView)view.findViewById(R.id.bookTime);
            address = (TextView) view.findViewById(R.id.address);
            storename = (TextView) view.findViewById(R.id.storename);
            name = (TextView) view.findViewById(R.id.name);

            storeimage = (ImageView) view.findViewById(R.id.storeimage);
        }
    }


    public BookAdapter(Context mContext, List<Book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Book book = bookList.get(position);

//        holder.address.setText(book.getShopcode().getAddress());
//        holder.storename.setText(book.getShopcode().getStoreName());
//        holder.name.setText(book.getShopcode().getName());
       holder.bookTime.setText(book.getBook_date());
//        // loading album cover using Glide library
        //Glide.with(mContext).load(book.getShopcode().getStoreImage()).into(holder.storeimage);
//

    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}