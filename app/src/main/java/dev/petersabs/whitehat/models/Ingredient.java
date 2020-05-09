package dev.petersabs.whitehat.models;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private int quantity = -1;
    private String measure;
    private String ingredient;
    private final static long serialVersionUID = 949822802213658146L;

    /**
     * No args constructor for use in serialization
     */
    public Ingredient() {
    }

    /**
     * An ingredient needed in a Recipe
     *
     * @param quantity   quantity of ingredient to use
     * @param measure    measurement unit for the quantity
     * @param ingredient ingredient name
     */
    public Ingredient(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isValid() {
        return quantity != -1 && measure != null && !measure.isEmpty() && ingredient != null && !ingredient.isEmpty();
    }
}
