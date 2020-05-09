package dev.petersabs.whitehat;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;

import java.util.ArrayList;

import dev.petersabs.whitehat.models.Recipe;
import dev.petersabs.whitehat.utils.JsonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RecipesViewModel extends AndroidViewModel {

    private static MutableLiveData<ArrayList<Recipe>> recipes;
    private static String recipeListingURL;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        recipeListingURL = application.getString(R.string.recipe_listing_url);
    }

    public LiveData<ArrayList<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        new RetrieveRecipes().execute();
    }

    static class RetrieveRecipes extends AsyncTask<Void, Void, ArrayList<Recipe>> {

        private final ArrayList<Recipe> tempRecipes = new ArrayList<>();

        @Override
        protected ArrayList<Recipe> doInBackground(Void... strings) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(recipeListingURL).build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return tempRecipes;
                }
                ResponseBody body = response.body();
                String responseBody = body != null ? body.string() : null;
                if (responseBody == null) {
                    return tempRecipes;
                }
                JSONArray respRecipes = JsonUtils.getResponseArray(responseBody);

                for (int i = 0; i < respRecipes.length(); i++) {
                    tempRecipes.add(JsonUtils.parseRecipe(respRecipes.getJSONObject(i)));
                }

                return tempRecipes;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tempRecipes;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> tempRecipes) {
            super.onPostExecute(tempRecipes);
            recipes.setValue(tempRecipes);
        }
    }
}
