package dev.petersabs.whitehat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dev.petersabs.whitehat.models.Recipe;

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
