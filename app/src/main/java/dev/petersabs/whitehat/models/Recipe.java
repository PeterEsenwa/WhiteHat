package dev.petersabs.whitehat.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    private final static long serialVersionUID = -1165047972509849545L;
    private int id;
    private String name;
    private ArrayList<Ingredient> ingredients = null;
    private ArrayList<Step> steps = null;
    private String stepCountText;
    private String ingredientCountText;
    private int servings;
    private String image;

    /**
     * No args constructor for use in serialization
     */
    public Recipe() {
    }

    /**
     * @param image       recipe display image
     * @param servings    numbers of servings from this recipe
     * @param name        recipe name
     * @param ingredients ingredients needed
     * @param id          recipe ID
     * @param steps       steps in recipe
     */
    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings,
                  String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredientCountText() {
        return ingredientCountText;
    }

    public void setIngredientCountText(String ingredientCountText) {
        this.ingredientCountText = ingredientCountText;
    }

    public int getIngredientCount() {
        return ingredients.size();
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public int getStepCount() {
        return steps.size();
    }

    public String getStepCountText() {
        return stepCountText;
    }

    public void setStepCountText(String stepCountText) {
        this.stepCountText = stepCountText;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
