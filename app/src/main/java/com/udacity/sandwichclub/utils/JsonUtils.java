package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * This method accepts a string of json data then returns a string array
     * data about sandwich that will be used to populate data on the UI.
     * @param json
     * @return Sandwich data to be displayed
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        //Get the json data to position with the elements in the activity_detail.xml

        //Initializing Arraylist of also know as
        List<String> alsoKnownAs = new ArrayList<String>();

        //Initializing Arraylist of Ingredients
        List<String> ingredients = new ArrayList<String>();

        //Initializing the Json object to get our data from
        JSONObject sandwichJson = new JSONObject(json);

        //Getting down to the name child of sandwichJson
        JSONObject names = sandwichJson.getJSONObject("name");

        //Getting the main name of the sandwich
        String mainName = names.getString("mainName");

        //Getting the also known as names
        JSONArray alsoKnownAsJson = names.getJSONArray("alsoKnownAs");

        //Iterate through JsonArray to get the data and put it in a String array of known as data
        for (int i = 0; i < alsoKnownAsJson.length(); i++){
            alsoKnownAs.add(alsoKnownAsJson.getString(i));
        }

        //Getting the place of origin
        String placeOfOrigin = sandwichJson.getString("placeOfOrigin");

        //Getting the description
        String description = sandwichJson.getString("description");

        //Getting Image of the sandwich
        String image = sandwichJson.getString("image");

        //Getting Ingredients
        JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");

        //Iterate through ingredientsJson to get each ingredients in the list
        for (int i = 0; i<ingredientsJson.length(); i ++){
            ingredients.add(ingredientsJson.getString(i));
        }

        Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        return sandwich;
    }
}
