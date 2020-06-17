package dev.petersabs.whitehat.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import dev.petersabs.whitehat.R;
import dev.petersabs.whitehat.RecipesViewModel;
import dev.petersabs.whitehat.models.Recipe;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeFragment extends Fragment implements RecipesAdapter.RecipeItemSelectedListener {

    private static final int RC_INTERNET = 15243;
    private RecipesViewModel recipesViewModel;
    private RecipesAdapter recipesAdapter;
    private RecyclerView recipesRecyclerView;
    private View mainView;
    private NavController navController;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainView = view;
        navController = Navigation.findNavController(view);
        Toolbar toolbar = view.findViewById(R.id.mobile_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        recipesAdapter = new RecipesAdapter();
        recipesAdapter.setItemSelectedListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());

        recipesRecyclerView = view.findViewById(R.id.recipes_rv);
        recipesRecyclerView.setLayoutManager(layoutManager);
        recipesRecyclerView.setAdapter(recipesAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recipesRecyclerView.getContext(),
                layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(Objects.requireNonNull(requireContext().getDrawable(R.drawable.recipe_items_divider)));
        recipesRecyclerView.addItemDecoration(dividerItemDecoration);

        checkPermissions();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions,
                                           @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_INTERNET)
    private void checkPermissions() {
        String[] perms = {Manifest.permission.INTERNET};
        if (EasyPermissions.hasPermissions(requireActivity(), perms)) {
            recipesViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
            recipesViewModel.getRecipes().observe(getViewLifecycleOwner(), newRecipes -> {
                recipesAdapter.setRecipes(newRecipes);
//                recipesRecyclerView.scheduleLayoutAnimation();
            });
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.internet_perm_rationale),
                    RC_INTERNET, perms);
        }
    }

    @Override
    public void selectedRecipeItem(Recipe recipe) {
        recipesViewModel.setSelectedRecipe(recipe);
        if (mainView.getId() == R.id.default_home_layout) {
            navController.navigate(R.id.action_homeFragment_to_recipeDetails);
        }
    }
}
