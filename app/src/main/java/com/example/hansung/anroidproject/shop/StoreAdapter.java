package com.example.hansung.anroidproject.shop;


import android.app.ActivityOptions;
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
import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.deprecated.model.Store;
import com.example.hansung.anroidproject.detailShop.DetailStore;
import com.example.hansung.anroidproject.model.Stylist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimsungmin on 2017-11-03.
 */

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    List<Stylist> StylistModels;

    public StoreAdapter() {
        StylistModels = new ArrayList<>();
        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("stylist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StylistModels.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    Stylist StylistModel = snapshot.getValue(Stylist.class);

                    if (StylistModel.getUid().equals(myUid)) {
                        continue;
                    }
                    StylistModels.add(StylistModel);
                }
                notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_card, parent, false);


        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        Glide.with
                (holder.itemView.getContext())
                .load(StylistModels.get(position).getProfileImageUrl())
                .apply(new RequestOptions().circleCrop())
                .into(((CustomViewHolder) holder).imageView);
        ((CustomViewHolder) holder).textView.setText(StylistModels.get(position).getStylistName());
        ((CustomViewHolder) holder).address.setText(StylistModels.get(position).getStylistAddress());
        ((CustomViewHolder) holder).storename.setText(StylistModels.get(position).getShopName());
        ((CustomViewHolder) holder).name.setText(StylistModels.get(position).getStylistName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), MessageActivity.class);
//                intent.putExtra("destinationUid", userModels.get(position).uid);
//                ActivityOptions activityOptions = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.fromright, R.anim.toleft);
//                    startActivity(intent, activityOptions.toBundle());
//                }

                    Toast.makeText(view.getContext(),"눌러짐",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return StylistModels.size();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView, address, storename, name;

        public CustomViewHolder(View view) {
            super(view);
            address = (TextView) view.findViewById(R.id.address); //주소
            imageView = (ImageView) view.findViewById(R.id.storeimage); //이미지
            textView = (TextView) view.findViewById(R.id.stylistId); //아이디
            storename = (TextView) view.findViewById(R.id.storename);
            name = (TextView) view.findViewById(R.id.name);
        }
    }
}
