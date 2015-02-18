package kz.kstu.coffee.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class Invoice extends Entity implements Serializable {

    private User user;
    private List<Drink> orderedDrinks;
    private Date invoiceDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Drink> getOrderedDrinks() {
        return orderedDrinks;
    }

    public void setOrderedDrinks(List<Drink> orderedDrinks) {
        this.orderedDrinks = orderedDrinks;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceDateDefaultFormat() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String invoiceDateFormatted = dateFormat.format(invoiceDate);
        return invoiceDateFormatted;
    }

    public double getInvoiceTotal() {
        double invoiceTotal = 0.0;
        for (Drink drink : this.orderedDrinks) {
            invoiceTotal += drink.getPrice();
            List<Ingredient> orderedIngredients = drink.getIngredients();
            for (Ingredient ingredient : orderedIngredients) {
                invoiceTotal += ingredient.getPrice();
            }
        }
        return invoiceTotal;
    }

    public String getInvoiceTotalCurrencyFormat() {
        double total = this.getInvoiceTotal();
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedTotal = currency.format(total);
        return formattedTotal;
    }
}
