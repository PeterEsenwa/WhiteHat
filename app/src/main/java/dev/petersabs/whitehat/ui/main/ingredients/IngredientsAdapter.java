package dev.petersabs.whitehat.ui.main.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.models.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final ArrayList<Ingredient> mIngredients;

    IngredientsAdapter(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIngredient = mIngredients.get(position);
        holder.mIngredientName.setText(holder.mIngredient.getIngredient().toLowerCase());

        Context context = holder.mView.getContext();
        String formattedQuantity = mIngredients.get(position).getFormattedQuantity().toLowerCase();
        if (holder.mIngredient.getQuantity() > 1 && holder.mIngredient.canPluralize()) {
            holder.mIngredientQuantity.setText(String.format(context.getString(R.string.ingredient_quantity_template),
                    formattedQuantity));
        } else {
            holder.mIngredientQuantity.setText(formattedQuantity);
        }
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIngredientName;
        final TextView mIngredientQuantity;
        Ingredient mIngredient;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIngredientName = view.findViewById(R.id.ingredient_name);
            mIngredientQuantity = view.findViewById(R.id.ingredient_quantity);
        }
    }
}
