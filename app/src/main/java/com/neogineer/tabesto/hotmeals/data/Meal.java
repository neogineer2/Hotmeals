package com.neogineer.tabesto.hotmeals.data;

import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 27/06/2018.
 *
 *
 * just a POJO
 */
public class Meal {

    public int mealId;
    public String mealName;
    public String category;
    public String area;
    public String instructions;
    public List<String> ingredients;
    public String imageUrl;
    public int price = 10; // in euros

    public Meal(int mealId, String mealName, String category, String area, String instructions, List<String> ingredients, String imageUrl) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }

    // to be deleted later
    public Meal(){}

}
