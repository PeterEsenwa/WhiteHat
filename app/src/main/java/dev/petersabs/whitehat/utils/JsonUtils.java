package dev.petersabs.whitehat.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import dev.petersabs.whitehat.models.Ingredient;
import dev.petersabs.whitehat.models.Recipe;
import dev.petersabs.whitehat.models.Step;

public class JsonUtils {
    //    region Recipe property constants
    private static final String ID_TAG = "id";
    private static final String NAME_TAG = "name";
    private static final String INGREDIENTS_TAG = "ingredients";
    private static final String STEPS_TAG = "steps";
    private static final String SERVINGS_TAG = "servings";
    private static final String IMAGE_TAG = "image";
    //    endregion
    private static final String DESCRIPTION_TAG = "description";
    private static final String S_DESCRIPTION_TAG = "shortDescription";
    private static final String THUMBNAIL_URL_TAG = "thumbnailURL";
    private static final String VIDEO_URL_TAG = "videoURL";
    private static final String INGREDIENT_TAG = "ingredient";
    private static final String MEASURE_TAG = "measure";
    private static final String QUANTITY_TAG = "quantity";

    public static JSONArray getResponseArray(String json) throws JSONException {
        return new JSONArray(json);
    }

    public static Step parseStep(JSONObject jsonObject) {
        Step step = new Step();
        try {
            step.setId(jsonObject.getInt(ID_TAG));
            step.setDescription(jsonObject.getString(DESCRIPTION_TAG));
            step.setShortDescription(jsonObject.getString(S_DESCRIPTION_TAG));
            step.setThumbnailURL(jsonObject.getString(THUMBNAIL_URL_TAG));
            step.setVideoURL(jsonObject.getString(VIDEO_URL_TAG));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return step.isValid() ? step : null;
    }

    public static Ingredient parseIngredient(JSONObject jsonObject) {
        Ingredient ingredient = new Ingredient();
        try {
            ingredient.setIngredient(jsonObject.getString(INGREDIENT_TAG));
            ingredient.setMeasure(jsonObject.getString(MEASURE_TAG));
            ingredient.setQuantity(jsonObject.getDouble(QUANTITY_TAG));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredient.isValid() ? ingredient : null;
    }

    public static Recipe parseRecipe(JSONObject jsonObject) {
        Recipe recipe = new Recipe();

        try {
            recipe.setId(jsonObject.getInt(ID_TAG));
            recipe.setName(jsonObject.getString(NAME_TAG));
            recipe.setImage(jsonObject.getString(IMAGE_TAG));
            recipe.setServings(jsonObject.getInt(SERVINGS_TAG));

            JSONArray stepsJsonArray = jsonObject.getJSONArray(STEPS_TAG);
            ArrayList<Step> steps = new ArrayList<>();
            for (int i = 0; i < stepsJsonArray.length(); i++) {
                Step step = parseStep(stepsJsonArray.getJSONObject(i));
                if (step != null) {
                    steps.add(step);
                }
            }
            recipe.setSteps(steps);

            JSONArray ingredientsJsonArray = jsonObject.getJSONArray(INGREDIENTS_TAG);
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                Ingredient ingredient = parseIngredient(ingredientsJsonArray.getJSONObject(i));
                if (ingredient != null) {
                    ingredients.add(ingredient);
                }
            }
            recipe.setIngredients(ingredients);
        } catch (JSONException e) {
            Log.d(JsonUtils.class.getSimpleName(), Objects.requireNonNull(e.getMessage()));
        }
        return recipe;
    }
}
