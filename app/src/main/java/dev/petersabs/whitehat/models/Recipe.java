package dev.petersabs.whitehat.models;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Recipe implements Serializable {

    private int id;
    private String name;
    private List<Ingredient> ingredients = null;
    private List<Step> steps = null;
    private int servings;
    private String image;
    private final static long serialVersionUID = -1165047972509849545L;

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
    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String image) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredientsCountText() {
        return String.format(Locale.getDefault(), "%d ingredients", getIngredientsCount());
    }

    public int getIngredientsCount() {
        return ingredients.size();
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getStepsCountText() {
        return String.format(Locale.getDefault(), "%d instructions", getStepsCount());
    }

    public int getStepsCount() {
        return steps.size();
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