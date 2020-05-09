package dev.petersabs.whitehat;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import dev.petersabs.whitehat.models.Recipe;
import dev.petersabs.whitehat.ui.main.HomeFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void loadData() {
        ArrayList<Recipe> tempRecipes;

    }
}
