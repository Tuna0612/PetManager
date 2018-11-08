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
import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.model.Cat;

import java.util.List;

public class CatAdapter extends BaseAdapter implements Filterable {
    List<Cat> catList;
    List<Cat> listSort;
    private Filter CatFilter;
    private Activity context;
    private CatDao catDao;

    private final LayoutInflater inflater;

    public CatAdapter(Activity context, List<Cat> catList) {
        super();
        this.catList = catList;
        this.listSort = catList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        catDao = new CatDao(context);
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int i) {
        return catList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_pet, viewGroup, false);
            holder.tvID = view.findViewById(R.id.tvIDPet);
            holder.tvHealth = view.findViewById(R.id.tvHealthPet);
            holder.tvWeight = view.findViewById(R.id.tvWeightPet);
            holder.tvPrice = view.findViewById(R.id.tvPricePet);
            holder.imgEdit = view.findViewById(R.id.btnEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.btnDelete);

            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditPET.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", catList.get(position).getmID());
                    bundle.putString("weight", catList.get(position).getmWeight());
                    bundle.putString("health", catList.get(position).getmHealth());
                    bundle.putString("price", catList.get(position).getmPrice());
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
                            catDao.deleteCatbyID(catList.get(position).getmID());
                            catList.remove(position);
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
            holder = (ViewHolder) view.getTag();
        Cat _entry = catList.get(position);
        holder.tvID.setText(_entry.getmID());
        holder.tvHealth.setText("Health: "+_entry.getmHealth());
        holder.tvWeight.setText("Weight: "+_entry.getmWeight()+" Kg");
        holder.tvPrice.setText("Price: $"+_entry.getmPrice());
        return view;

    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        TextView tvID, tvWeight, tvPrice, tvHealth;
        ImageView imgEdit, imgDelete;
    }
}
