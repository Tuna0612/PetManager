package com.anhtu.tuna.petmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.model.Cat;
import com.anhtu.tuna.petmanager.model.Dog;

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
        CatAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CatAdapter.ViewHolder();
            view = inflater.inflate(R.layout.item_pet, viewGroup, false);
            holder.tvID = view.findViewById(R.id.tvIDPet);
            holder.tvHealth = view.findViewById(R.id.tvHealthPet);
            holder.tvWeight = view.findViewById(R.id.tvWeightPet);
            holder.tvPrice = view.findViewById(R.id.tvPricePet);

            holder.imgDelete = (ImageView) view.findViewById(R.id.btnDelete);
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
            holder = (CatAdapter.ViewHolder) view.getTag();
        Dog _entry = dogList.get(position);
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
