package com.neogineer.tabesto.hotmeals.meal_details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.tabesto.hotmeals.R;
import com.neogineer.tabesto.hotmeals.data.Meal;
import com.neogineer.tabesto.hotmeals.meals.MealsActivity;

public class MealDetailsActivity extends AppCompatActivity {

    Meal mMeal;
    ImageView mImage;
    TextView mCategory;
    TextView mArea;
    TextView mPrice;
    TextView mMainIngredients;
    TextView mInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Meal ordered! It's coming, wait for it (but don't rely)", Snackbar.LENGTH_LONG)
                .setAction("Cancel", (v)->{}).show());

        mMeal = (Meal) getIntent().getSerializableExtra(MealsActivity.EXTRA_MEAL);

        mImage = findViewById(R.id.meal_image);
        mCategory = findViewById(R.id.category);
        mArea = findViewById(R.id.area);
        mPrice = findViewById(R.id.meal_price);
        mMainIngredients = findViewById(R.id.main_ingredients);
        mInstructions = findViewById(R.id.instructions);

        mCategory.setText(mMeal.category);
        mArea.setText(mMeal.area);
        mPrice.setText(mMeal.price + "€");
        mMainIngredients.setText(mMeal.getStringMainIngredients());
        mInstructions.setText(mMeal.instructions);

        Glide.with(this)
                .load(mMeal.imageUrl)
                .into(mImage);

        getSupportActionBar().setTitle(mMeal.mealName);

    }
}