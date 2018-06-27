package com.neogineer.tabesto.hotmeals;

import com.neogineer.tabesto.hotmeals.data.Meal;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 27/06/2018.
 */
public class Utils {


    /**
     * builds Meals from root JSON object
     * @param obj
     * @return
     */
    public static List<Meal> buildMeals(JSONObject obj) throws JSONException {
        LinkedList<Meal> meals = new LinkedList<>();

        JSONArray mealsArray = (JSONArray) obj.get("meals");
        for(int i=0; i<mealsArray.size(); i++){
            org.json.simple.JSONObject jMeal = (org.json.simple.JSONObject) mealsArray.get(i);
            Meal meal = new Meal();

            meal.mealId = Integer.parseInt((String) jMeal.get("idMeal"));
            meal.mealName = (String) jMeal.get("strMeal");
            meal.area = (String) jMeal.get("strArea");
            meal.category = (String) jMeal.get("strCategory");
            meal.imageUrl = (String) jMeal.get("strMealThumb");
            meal.instructions = (String) jMeal.get("strInstructions");

            List<String> ings = new LinkedList<>();
            ings.add((String) jMeal.get("strIngredient1"));
            ings.add((String) jMeal.get("strIngredient2"));
            ings.add((String) jMeal.get("strIngredient3"));
            meal.ingredients = ings;

            meals.add(meal);
        }

        return meals;
    }
}
