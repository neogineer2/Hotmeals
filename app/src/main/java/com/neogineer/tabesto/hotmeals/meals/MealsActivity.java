package com.neogineer.tabesto.hotmeals.meals;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.neogineer.tabesto.hotmeals.R;
import com.neogineer.tabesto.hotmeals.data.Meal;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class MealsActivity extends AppCompatActivity {

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

        new DownloadTask().execute();

    }


    private class DownloadTask extends AsyncTask<String, Integer, List<Meal> >{


        /******** GET FULL SORTED IMAGED ARTICLE OBJECTS ************/

        @Override
        protected List<Meal> doInBackground(String... params) {

            List<Meal> meals = new LinkedList<>();
            JSONParser parser = new JSONParser();

            try {
                String response = IOUtils.toString(new URL("https://www.themealdb.com/api/json/v1/1/latest.php").openStream(), Charset.defaultCharset());
                JSONObject obj = (JSONObject) parser.parse(response);
                Log.i("json", "json from server: "+obj);



                Log.i("","");



            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return meals;
        }

        @Override
        protected void onPostExecute(List<Meal> meals) {
            super.onPostExecute(meals);
            mAdapter = new MealsAdapter(meals);
            mRecycler.setAdapter(mAdapter);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values[0]==1)
                Toast.makeText(getBaseContext(), "Problème réseau...", Toast.LENGTH_LONG).show();


        }
    }

}
