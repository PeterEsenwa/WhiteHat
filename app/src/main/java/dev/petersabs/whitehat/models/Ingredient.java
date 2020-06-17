package dev.petersabs.whitehat.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ingredient implements Serializable, Parcelable {

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    private final static long serialVersionUID = 949822802213658146L;
    private Double quantity = (double) -1;
    private String measure;
    private String ingredient;

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
    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    private Ingredient(Parcel parcel) {
        this.quantity = parcel.readDouble();
        this.measure = parcel.readString();
        this.ingredient = parcel.readString();
    }

    public boolean canPluralize() {
        return !measure.equals("G");
    }

    public String getFormattedQuantity() {
        return quantity % 1 == 0
                ? Math.round(quantity) + measure
                : quantity + measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(measure);
        dest.writeString(ingredient);
        dest.writeDouble(quantity);
    }
}
