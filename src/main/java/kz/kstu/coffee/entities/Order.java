package kz.kstu.coffee.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kirill
 */
public class Order extends Entity implements Serializable {

    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void removeDrink(int index) {
        drinks.remove(index);
    }
}
