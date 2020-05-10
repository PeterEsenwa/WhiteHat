package dev.petersabs.whitehat.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.models.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public interface RecipeItemSelectedListener {
        void selectedRecipeItem(Recipe recipe);
    }

    private RecipeItemSelectedListener itemSelectedListener;

    void setItemSelectedListener(RecipeItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipesViewHolder(view, itemSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        holder.recipe = recipes.get(position);

        TextView recipeNameTV = holder.constraintLayout.findViewById(R.id.recipe_item_name);
        TextView recipeIngredientsTV = holder.constraintLayout.findViewById(R.id.recipe_item_ingredients_tv);
        TextView recipeStepsTV = holder.constraintLayout.findViewById(R.id.recipe_item_instructions_tv);

        recipeNameTV.setText(holder.recipe.getName());
        recipeIngredientsTV.setText(holder.recipe.getIngredientsCountText());
        recipeStepsTV.setText(holder.recipe.getStepsCountText());
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    static class RecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ConstraintLayout constraintLayout;
        Recipe recipe;
        private RecipeItemSelectedListener itemSelectedListener;

        RecipesViewHolder(@NonNull ConstraintLayout itemView, RecipeItemSelectedListener itemSelectedListener) {
            super(itemView);
            this.itemSelectedListener = itemSelectedListener;
            itemView.setOnClickListener(this);
            constraintLayout = itemView;
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(constraintLayout.getContext(), recipe.getName(), Toast.LENGTH_LONG).show();
            itemSelectedListener.selectedRecipeItem(recipe);
        }
    }
}
