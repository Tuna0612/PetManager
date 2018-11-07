package com.anhtu.tuna.petmanager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.model.Dog;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<Dog> dog;
    public DogAdapter(List<Dog> dog){
        this.dog = dog;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_pet,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dog dogg = dog.get(position);
        holder.tvID.setText(dogg.getTvID());
        holder.tvWeight.setText(dogg.getTvWeight());
        holder.tvPrice.setText(dogg.getTvPrice());
    }

    @Override
    public int getItemCount() {
        return dog.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvID,tvWeight,tvPrice;

        public ViewHolder(View view){
            super(view);
            tvID = itemView.findViewById(R.id.tvIDPet);
            tvWeight = itemView.findViewById(R.id.tvWeightPet);
            tvPrice = itemView.findViewById(R.id.tvPricePet);

        }
    }
}
