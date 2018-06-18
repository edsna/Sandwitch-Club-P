package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    String emptyAka = "";   //Empty str to store alsoKnownAs
   // private TextView mOriginLabel;
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
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
    /*
    Populates the UI with data from the JSON file
     */
    private void populateUI(Sandwich sandwich) {
        //gets the image view
        ImageView imageTextView = findViewById(R.id.image_iv);  //stores image view
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageTextView);   //retrieves img view
            //Retrieves alsoKnownAs
            TextView akaTextView = findViewById(R.id.also_known_tv);    //Stores alsoknownAs text view
           if(sandwich.getAlsoKnownAs() != null) {  //Being safe
               for (String alsoKnownAs : sandwich.getAlsoKnownAs()) {
                   if(sandwich.getAlsoKnownAs() != null) {
                       emptyAka = emptyAka.concat(alsoKnownAs.concat("\n"));
                   }
               }
               if (emptyAka != "") {
                   akaTextView.setText(emptyAka.substring(0, emptyAka.lastIndexOf('\n')));
               }
           }
        //Retrieves Place of origin
        TextView originTextView =findViewById(R.id.origin_tv);
        originTextView.setText(sandwich.getPlaceOfOrigin());

        //Retrieving ingredients
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);   //Store Ingredient's Text View
        String emptyIngredients = "";
        if(sandwich.getIngredients() != null) {  //Being safe
            for (String ingredient : sandwich.getIngredients()) {
                if(sandwich.getIngredients() != null) {
                    emptyIngredients = emptyIngredients.concat(ingredient.concat("\n"));
                }
            }
            if (emptyIngredients != "") {
                ingredientsTextView.setText(emptyIngredients.substring(0, emptyIngredients.lastIndexOf('\n')));
            }
        }
        //Retrieves Descriptuion
        TextView descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());
/*

        //Origin
        TextView originTextView = findViewById(R.id.origin_tv); //Get's origin's TextView
        String origin = sandwich.getPlaceOfOrigin();
        if(origin == null || origin.equals("")){    //if place of origin is empty
            origin = getString(R.string.notKnown_origin);
        }
        originTextView.setText(origin);
        //Also Known As
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv); //Get's oalso known as TextView
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        if(alsoKnownAsList.isEmpty()){
            alsoKnownAsTextView.setSystemUiVisibility(View.GONE);
            alsoKnownAsTextView.setVisibility(View.GONE);
        }
        else{   //OTHERWISE
            alsoKnownAsTextView.setSystemUiVisibility(View.VISIBLE);
            alsoKnownAsTextView.setVisibility(View.VISIBLE);
        }
        StringBuilder myBuilder = new StringBuilder();  //new string builder to keep values
        for(int i=0; i<alsoKnownAsList.size(); i++){
            myBuilder.append(myBuilder).append(","); //add each consequently with commer after
            alsoKnownAsTextView.setText(myBuilder); //bring it up
        }
        //Description
        TextView descriptionTextView = findViewById(R.id.description_tv);   //Get description's textView
        String description = sandwich.getDescription(); //set it to description String
        if(description == null || description.equals("")){  //If description is empty
            description = getString(R.string.no_description);   //set it to function
        }
        descriptionTextView.setText(description);   //Bring it u
        //Ingredients
        TextView ingredientsTexView = findViewById(R.id.ingredients_tv);
        String ingredients;
        List<String> ingredientsList = sandwich.getIngredients();
        if(ingredientsList.isEmpty()){
            ingredients = getString(R.string.notKnown_ingredients);
        }
        else{
            StringBuilder myingredients = new StringBuilder();
            for(int i = 0; i < ingredientsList.size(); i++){
                myingredients.append(i).append(",");
            }
            ingredients = myingredients.toString();
        }
        ingredientsTexView.setText(ingredients);
        */
    }
}
