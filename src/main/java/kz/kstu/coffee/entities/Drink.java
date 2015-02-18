package kz.kstu.coffee.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Drink extends Entity implements Serializable {

    public String code;
    private String name;
    private List<Ingredient> ingredients;
    private double price;
    private int quantity;

    public Drink() {
        ingredients = new ArrayList<Ingredient>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String drinkCode) {
        this.code = drinkCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String drinkName) {
        this.name = drinkName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    
    public void removeIngredients(){
        this.ingredients = new ArrayList<Ingredient>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double drinkPrice) {
        this.price = drinkPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
