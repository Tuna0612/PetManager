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
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.model.PET;
import com.anhtu.tuna.petmanager.model.PetDao;

import java.util.List;

public class PetAdapter extends BaseAdapter {

    List<PET> list;
    public Activity context;
    public LayoutInflater inflater;
    PetDao petDAO;

    public PetAdapter(Activity context, List<PET> list) {
        super();
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        petDAO = new PetDao(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvIDPET;
        TextView tvPrice;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_pet,null);
            viewHolder.tvIDPET = (TextView) convertView.findViewById(R.id.tvIDPet);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPricePet);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.btnDelete);
            viewHolder.imgEdit = convertView.findViewById(R.id.btnEdit);

            viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, EditUser.class);
//                    Bundle b = new Bundle();
//                    b.putString("USERNAME", list.get(position).getmUsername());
//                    b.putString("PHONE", list.get(position).getmPhone());
//                    b.putString("NAME", list.get(position).getmName());
//                    intent.putExtras(b);
//                    context.startActivity(intent);
                }
            });
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Message");
                    builder.setMessage("Do you want delete this item ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            petDAO.deletePET(list.get(position).getmID());
                            list.remove(position);
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

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        PET _entry = list.get(position);
//        if (position % 3 == 0) {
//            viewHolder.img.setImageResource(R.drawable.android);
//        } else if (position % 3 == 1) {
//            viewHolder.img.setImageResource(R.drawable.android);
//        } else {
//            viewHolder.img.setImageResource(R.drawable.android);
//        }
        viewHolder.tvIDPET.setText(_entry.getmID());
        viewHolder.tvPrice.setText(_entry.getmPrice());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<PET> items){
        this.list = items;
        notifyDataSetChanged();
    }
}
