package dev.petersabs.whitehat.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.RecipesViewModel;
import dev.petersabs.whitehat.models.Recipe;
import dev.petersabs.whitehat.ui.main.ingredients.IngredientsFragment;
import dev.petersabs.whitehat.ui.main.instructions.InstructionsFragment;

public class RecipeDetailsFragment extends Fragment {
    private static final String ARG_RECIPE = "recipe";
    private static final String INGREDIENTS_TAB_HEADER = "INGREDIENTS";
    private static final String INSTRUCTIONS_TAB_HEADER = "INSTRUCTIONS";
    private ViewPager2 recipeDetailsViewPager;
    private RecipeDetailsAdapter recipeDetailsAdapter;
    private Recipe selectedRecipe;

    public RecipeDetailsFragment() {
    }

    /**
     * @param recipe Currently selected recipe.
     * @return A new instance of fragment RecipeDetails.
     */
    public static RecipeDetailsFragment newInstance(Recipe recipe) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedRecipe = (Recipe) getArguments().getSerializable(ARG_RECIPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = view.findViewById(R.id.recipe_toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        RecipesViewModel recipesViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        recipesViewModel.getSelectedRecipe().observe(getViewLifecycleOwner(), recipe -> {
            if (recipe == null) return;
            selectedRecipe = recipe;
            ((TextView) view.findViewById(R.id.recipe_name_tv)).setText(recipe.getName());
            ((TextView) view.findViewById(R.id.servings_count_tv)).setText(String.valueOf(recipe.getServings()));

            recipeDetailsAdapter = new RecipeDetailsAdapter(this);
            recipeDetailsViewPager = view.findViewById(R.id.recipe_details_view_pager);
            recipeDetailsViewPager.setAdapter(recipeDetailsAdapter);

            TabLayout tabLayout = view.findViewById(R.id.ingredients_instructions_tabs);
            new TabLayoutMediator(tabLayout, recipeDetailsViewPager,
                    (tab, position) -> {
                        if (position == 0) {
                            tab.setText(INGREDIENTS_TAB_HEADER);
                        } else if (position == 1) {
                            tab.setText(INSTRUCTIONS_TAB_HEADER);
                        }
                    }
            ).attach();
        });
    }

    @Override
    public void onDestroy() {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE, selectedRecipe);
        onSaveInstanceState(args);
        super.onDestroy();
    }

    public class RecipeDetailsAdapter extends FragmentStateAdapter {

        RecipeDetailsAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0
                    ? IngredientsFragment.newInstance(selectedRecipe.getIngredients())
                    : InstructionsFragment.newInstance(selectedRecipe.getSteps(), selectedRecipe.getImage());
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
