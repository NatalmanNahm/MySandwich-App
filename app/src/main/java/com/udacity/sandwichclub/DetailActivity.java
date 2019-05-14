package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //Initializing all the textview needed to populate our ui
    private TextView mKnownAs;
    private TextView mIngredients;
    private TextView mPlaceOfOrigin;
    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);

            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            } else {
                //populating the also know as data
                mKnownAs = (TextView) findViewById(R.id.also_known_tv);
                String alsoKnownAs = sandwich.getAlsoKnownAs().toString();

                if (alsoKnownAs.equals("[]")){
                    mKnownAs.setText("Just Known as " + sandwich.getMainName());
                }else {
                    mKnownAs.setText(alsoKnownAs);
                }

                //populating the ingredients data
                mIngredients = (TextView) findViewById(R.id.ingredients_tv);
                mIngredients.setText(sandwich.getIngredients().toString());

                //populating the place of origin data
                mPlaceOfOrigin = (TextView) findViewById(R.id.origin_tv);
                mPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());

                //Populating the description data
                mDescription = (TextView) findViewById(R.id.description_tv);
                mDescription.setText(sandwich.getDescription());
            }

            populateUI();
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
