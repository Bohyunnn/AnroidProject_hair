package com.example.hansung.anroidproject;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hansung.anroidproject.model.Store;

import java.util.List;

/**
 * Created by kimsungmin on 2017-11-03.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private Context mContext;
    private List<Store> storeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView address, storename, name; //주소, 가게이름, 이름
        public ImageView storeimage;
        //, overflow; //가게 이미지, 버튼

        public MyViewHolder(View view) {
            super(view);
            address = (TextView) view.findViewById(R.id.address);
            storename = (TextView) view.findViewById(R.id.storename);
            name = (TextView) view.findViewById(R.id.name);

            storeimage = (ImageView) view.findViewById(R.id.storeimage);
          //  overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public StoreAdapter(Context mContext, List<Store> storeList) {
        this.mContext = mContext;
        this.storeList = storeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Store store = storeList.get(position);
        holder.address.setText(store.getAddress());
        holder.storename.setText(store.getStoreName());
        holder.name.setText(store.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(store.getStoreImage()).into(holder.storeimage);

        holder.storeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,DetailStore.class);
                intent.putExtra("storeimage",Integer.toString(storeList.get(position).getStoreImage()));
                //intent.putExtra("storeimage",storeList.get(position).getStoreImage());
                intent.putExtra("address",storeList.get(position).getAddress());
                intent.putExtra("storename",storeList.get(position).getStoreName());
                intent.putExtra("name",storeList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_store, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}