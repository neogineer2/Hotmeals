package com.neogineer.tabesto.hotmeals.meals;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.neogineer.tabesto.hotmeals.R;
import com.neogineer.tabesto.hotmeals.Utils;
import com.neogineer.tabesto.hotmeals.data.Meal;
import com.neogineer.tabesto.hotmeals.meal_details.MealDetailsActivity;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class MealsActivity extends AppCompatActivity {

    public static final String EXTRA_MEAL = "extra_meal";

    RecyclerView mRecycler;
    MealsAdapter mAdapter;
    List<Meal> mMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setHasFixedSize(true);

        new DownloadTask().execute();
    }

    private void showMealDetails(int mealId) {
        Meal meal = getMeal(mealId);

        Intent intent = new Intent(this, MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL, meal);
        startActivity(intent);
    }

    private Meal getMeal(int mealId){
        for(Meal m : mMeals){
            if(m.mealId==mealId)
                return m;
        }
        return null;
    }

    private class DownloadTask extends AsyncTask<String, Integer, List<Meal> >{

        @Override
        protected List<Meal> doInBackground(String... params) {

            List<Meal> meals = new LinkedList<>();
            JSONParser parser = new JSONParser();

            try {
                String response = IOUtils.toString(new URL("https://www.themealdb.com/api/json/v1/1/latest.php").openStream(), Charset.defaultCharset());
                JSONObject obj = (JSONObject) parser.parse(response);
                Log.i("json", "json from server: "+obj);

                meals = Utils.buildMeals(obj);

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            return meals;
        }

        @Override
        protected void onPostExecute(List<Meal> meals) {
            super.onPostExecute(meals);
            showMeals(meals);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values[0]==1)
                Toast.makeText(getBaseContext(), "Maybe network is bad...", Toast.LENGTH_LONG).show();


        }
    }

    private void showMeals(List<Meal> meals) {
        mMeals = meals;
        mAdapter = new MealsAdapter(mMeals, this::showMealDetails);
        mRecycler.setAdapter(mAdapter);
    }

}
