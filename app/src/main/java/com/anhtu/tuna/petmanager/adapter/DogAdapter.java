package com.anhtu.tuna.petmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.EditPET;
import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.model.Dog;
import com.bumptech.glide.Glide;

import java.util.List;

public class DogAdapter extends BaseAdapter implements Filterable {
    List<Dog> dogList;
    List<Dog> listSort;
    private Filter DogFilter;
    private Activity context;
    private DogDao dogDao;

    private final LayoutInflater inflater;

    public DogAdapter(Activity context, List<Dog> dogList) {
        super();
        this.dogList = dogList;
        this.listSort = dogList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        dogDao = new DogDao(context);
    }

    @Override
    public int getCount() {
        return dogList.size();
    }

    @Override
    public Object getItem(int i) {
        return dogList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Dog dog = dogList.get(position);
        DogAdapter.ViewHolder holder;
        if (view == null) {
            holder = new DogAdapter.ViewHolder();
            view = inflater.inflate(R.layout.item_pet, viewGroup, false);
            holder.tvID = view.findViewById(R.id.tvIDPet);
            holder.tvHealth = view.findViewById(R.id.tvHealthPet);
            holder.tvWeight = view.findViewById(R.id.tvWeightPet);
            holder.tvInjected = view.findViewById(R.id.tvỊnectedPet);
            holder.tvPrice = view.findViewById(R.id.tvPricePet);
            holder.imgEdit = view.findViewById(R.id.btnEdit);
            holder.imgAvatar = view.findViewById(R.id.imgPet);
            holder.imgDelete = (ImageView) view.findViewById(R.id.btnDelete);

            Glide.with(context).load(dog.getImage()).into(holder.imgAvatar);

            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditPET.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", dogList.get(position).getmID());
                    bundle.putString("weight", dogList.get(position).getmWeight());
                    bundle.putString("health", dogList.get(position).getmHealth());
                    bundle.putString("price", dogList.get(position).getmPrice());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Message");
                    builder.setMessage("Do you want delete this item ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dogDao.deleteDogbyID(dogList.get(position).getmID());
                            dogList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
            });
            view.setTag(holder);
        } else
            holder = (DogAdapter.ViewHolder) view.getTag();
        Dog _entry = dogList.get(position);
        holder.tvID.setText(_entry.getmID());
        holder.tvHealth.setText("Health: "+_entry.getmHealth());
        holder.tvWeight.setText("Weight: "+_entry.getmWeight()+" Kg");
        holder.tvInjected.setText(_entry.getmInjected());
        holder.tvPrice.setText("Price: $"+_entry.getmPrice());
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        TextView tvID, tvWeight, tvPrice, tvHealth,tvInjected;
        ImageView imgEdit, imgDelete,imgAvatar;
    }
}
