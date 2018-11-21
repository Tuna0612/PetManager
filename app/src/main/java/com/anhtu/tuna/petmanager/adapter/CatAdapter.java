package com.anhtu.tuna.petmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anhtu.tuna.petmanager.EditPET;
import com.anhtu.tuna.petmanager.ListCatActivity;
import com.anhtu.tuna.petmanager.ListDogActivity;
import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.model.Cat;
import com.anhtu.tuna.petmanager.model.Dog;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class CatAdapter extends BaseAdapter implements Filterable {
    private List<Cat> catList;
    private List<Cat> listSort;
    private Activity context;
    private CatDao catDao;

    private ImageView imgAnh;
    private TextView tvID;
    private EditText edWeight;
    private EditText edHealth;
    private RadioGroup radioGroup1;
    private RadioButton rboYes;
    private RadioButton rboNo;
    private EditText edPrice;
    private String inject;

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
        final Cat cat = catList.get(position);
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_pet, viewGroup, false);
            holder.tvID = view.findViewById(R.id.tvIDPet);
            holder.tvHealth = view.findViewById(R.id.tvHealthPet);
            holder.tvWeight = view.findViewById(R.id.tvWeightPet);
            holder.tvInjected = view.findViewById(R.id.tvỊnectedPet);
            holder.tvPrice = view.findViewById(R.id.tvPricePet);
            holder.imgEdit = view.findViewById(R.id.btnEdit);
            holder.imgAvatar = view.findViewById(R.id.imgPet);
            holder.imgDelete = (ImageView) view.findViewById(R.id.btnDelete);

            Glide.with(context).load(cat.getImage()).into(holder.imgAvatar);

            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e("injection", catList.get(position).getmInjected()+"");

                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Edit Pet");
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View viewDialog = inflater.inflate(R.layout.activity_edit_pet, null);

                    imgAnh = (ImageView) viewDialog.findViewById(R.id.imgAnh);
                    tvID = (TextView) viewDialog.findViewById(R.id.tvID);
                    edWeight = (EditText) viewDialog.findViewById(R.id.edWeight);
                    edHealth = (EditText) viewDialog.findViewById(R.id.edHealth);
                    radioGroup1 = viewDialog.findViewById(R.id.radioGroup1);
                    rboYes = (RadioButton) viewDialog.findViewById(R.id.rboYes);
                    rboNo = (RadioButton) viewDialog.findViewById(R.id.rboNo);
                    edPrice = (EditText) viewDialog.findViewById(R.id.edPrice);

                    byte[] img = catList.get(position).getImage();
                    Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                    imgAnh.setImageBitmap(imgBitmap);
                    tvID.setText(catList.get(position).getmID());
                    edWeight.setText(catList.get(position).getmWeight());
                    edHealth.setText(catList.get(position).getmHealth());
                    final String injected = catList.get(position).getmInjected();
                    if (injected.equals("Injected")) {
                        rboYes.setChecked(true);
                    } else {
                        rboNo.setChecked(true);
                    }
                    edPrice.setText(catList.get(position).getmPrice());

                    builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id = tvID.getText().toString();
                            String weight = edWeight.getText().toString();
                            String health = edHealth.getText().toString();
                            String price = edPrice.getText().toString();
                            radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    if (rboYes.isChecked()) {
                                        inject = "Injected";
                                    } else if (rboNo.isChecked()) {
                                        inject = "Uninjected";
                                    }
                                }
                            });
                            if (rboYes.isChecked()) {
                                inject = "Injected";
                            } else if (rboNo.isChecked()) {
                                inject = "Uninjected";
                            }
                            Cat cat = null;
                            try {
                                cat = new Cat(id,weight,health,inject,price,ImageViewChange(imgAnh));
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            if (catDao.updateCat(cat) > 0) {
                                Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show();

                                context.finish();
                                context.startActivity(new Intent(context, ListCatActivity.class));
                            }
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setView(viewDialog);
                    builder.show();


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

    private byte[] ImageViewChange(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
