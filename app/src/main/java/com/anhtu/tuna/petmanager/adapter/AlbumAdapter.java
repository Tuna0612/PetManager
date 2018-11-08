package com.anhtu.tuna.petmanager.adapter;

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

import com.anhtu.tuna.petmanager.AddCatActivity;
import com.anhtu.tuna.petmanager.AddDogActivity;
import com.anhtu.tuna.petmanager.ListCatActivity;
import com.anhtu.tuna.petmanager.ListDogActivity;
import com.anhtu.tuna.petmanager.MainActivity;
import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.model.Albums;
import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mContext;
    private List<Albums> albumList;
    private MainActivity mainActivity = new MainActivity();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumAdapter(Context mContext, List<Albums> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Albums album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText("Amount: " + album.getNumOfPet());


        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, album.getType());
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, String type) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(type));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private String type;

        public MyMenuItemClickListener(String type) {
            this.type = type;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.addPet:
                    Intent intent;
                    if (type.equals(Albums.DOG)){
                        intent = new Intent(mContext, AddDogActivity.class);
                    }else {
                        intent = new Intent(mContext, AddCatActivity.class);
                    }
                    mContext.startActivity(intent);
                    return true;
                case R.id.showListPet:
                    if (type.equals(Albums.DOG)){
                        mContext.startActivity(new Intent(mContext,ListDogActivity.class));
                    }else {
                        mContext.startActivity(new Intent(mContext,ListCatActivity.class));
                    }
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
