package com.neogineer.tabesto.hotmeals;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.tabesto.hotmeals.data.Meal;

import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 27/06/2018.
 */

class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    List<Meal> mMeals;

    public MealsAdapter(List<Meal> meals) {
        this.mMeals = meals;
    }

    @NonNull
    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.ViewHolder holder, int position) {
        Meal m = mMeals.get(position);

        Log.i("adapter", "bind view: "+m.mealName);

        holder.mealId = m.mealId;
        holder.name.setText(m.mealName);
        holder.price.setText(m.price + "€");

        Glide.with(holder.image.getContext())
                .load(m.imageUrl)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        int mealId;
        ImageView image;
        TextView name;
        TextView price;

        ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.name);
            price = v.findViewById(R.id.price);
        }
    }
}