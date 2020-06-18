package dev.petersabs.whitehat.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.models.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_CELL = 1;
    private static final int EMPTY_CELL = 0;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private RecipeItemSelectedListener itemSelectedListener;
    private EmptyRecipeSelectedListener emptyRecipeSelectedListener;

    void setItemSelectedListener(RecipeItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    void setEmptyRecipeSelectedListener(EmptyRecipeSelectedListener emptyRecipeSelectedListener) {
        this.emptyRecipeSelectedListener = emptyRecipeSelectedListener;
    }

    void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == EMPTY_CELL) {
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_item_placeholder, parent, false);
            return new EmptyRecipeViewHolder(view, emptyRecipeSelectedListener);
        } else {
            ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_item, parent, false);
            return new RecipesViewHolder(view, itemSelectedListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_CELL) {
            RecipesViewHolder typedHolder = (RecipesViewHolder) holder;
            typedHolder.recipe = recipes.get(position);

            TextView recipeNameTV = typedHolder.constraintLayout.findViewById(R.id.recipe_item_name);
            TextView recipeIngredientsTV = typedHolder.constraintLayout.findViewById(R.id.recipe_item_ingredients_tv);
            TextView recipeStepsTV = typedHolder.constraintLayout.findViewById(R.id.recipe_item_instructions_tv);

            recipeNameTV.setText(typedHolder.recipe.getName());
            recipeIngredientsTV.setText(typedHolder.recipe.getIngredientCountText());
            recipeStepsTV.setText(typedHolder.recipe.getStepCountText());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.recipes.size() == 0 ? EMPTY_CELL : NORMAL_CELL;
    }

    @Override
    public int getItemCount() {
        return recipes == null || recipes.size() == 0 ? 3 : recipes.size();
    }

    public interface RecipeItemSelectedListener {
        void selectedRecipeItem(Recipe recipe);
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
            itemSelectedListener.selectedRecipeItem(recipe);
        }
    }

    static class EmptyRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EmptyRecipeSelectedListener itemSelectedListener;

        EmptyRecipeViewHolder(@NonNull ConstraintLayout itemView,
                              EmptyRecipeSelectedListener itemSelectedListener) {
            super(itemView);
            this.itemSelectedListener = itemSelectedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemSelectedListener.showLoadingSnackMessage();
        }
    }
}
