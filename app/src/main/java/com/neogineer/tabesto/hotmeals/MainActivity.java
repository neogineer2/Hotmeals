package com.neogineer.tabesto.hotmeals;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neogineer.tabesto.hotmeals.data.Meal;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    MealsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setHasFixedSize(true);

        Meal m = new Meal();
        m.mealName = "first meal";
        m.mealId = 1;

        List<Meal> list = new LinkedList<Meal>();
        list.add(m);
        mAdapter = new MealsAdapter(list);
        mRecycler.setAdapter(mAdapter);

    }



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

            // TODO implement Glide here

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
}
