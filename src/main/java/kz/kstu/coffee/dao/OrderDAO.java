package kz.kstu.coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.entities.Ingredient;
import kz.kstu.coffee.entities.Invoice;
import kz.kstu.coffee.entities.Order;
import kz.kstu.coffee.pool.ConnectionPool;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Kirill
 */
public class OrderDAO extends AbstractDAO<Order> {

    private Logger log;
    public static final String SQL_INSERT_NEW_ORDERED_DRINK = ""
            + "INSERT INTO OrderedDrink (DrinkID, InvoiceID) "
            + " VALUES (?,?)";
    public static final String SQL_UPDATE_DRINK_QUANTITY = ""
            + "UPDATE drink SET DrinkQuantity = (DrinkQuantity - 1)"
            + " WHERE DrinkCode = ?";
    public static final String SQL_INSERT_NEW_ORDERED_INGREDIENT = ""
            + "INSERT INTO OrderedIngredient (IngredientID, OrderedDrinkID) "
            + " VALUES (?,?)";
    public static final String SQL_UPDATE_INGREDIENT_QUANTITY = ""
            + "UPDATE ingredient SET IngredientQuantity = (IngredientQuantity - 1) "
            + " WHERE IngredientCode = ?";
    public static final String SQL_SELECT_LAST_ORDERED_DRINK_ID = ""
            + "SELECT @@IDENTITY AS IDENTITY";
    //Выборка заказанных напитков по id счета (invoice)
    public static final String SQL_SELECT_DRINKS = ""
            + "SELECT d.DrinkID, d.DrinkCode, d.DrinkName, d.DrinkPrice, d.DrinkQuantity, o.OrderedDrinkID "
            + " FROM drink d JOIN OrderedDrink o "
            + " ON d.DrinkID  = o.DrinkID "
            + "WHERE o.InvoiceID = ?";
    //Выборка заказанных ингредиентов входящих в напитки по номеру заказанного напитка (OrderedDrinkID)
    public static final String SQL_SELECT_INGREDIENTS = ""
            + "SELECT i.IngredientID,  i.IngredientCode, i.IngredientName, i.IngredientPrice "
            + " FROM  Ingredient i JOIN OrderedIngredient o "
            + " ON i.IngredientID = o.IngredientID "
            + " WHERE o.OrderedDrinkID = ?";

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order findEntityById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int findEntityByID(Order entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Drink> findDrinksByInvoice(Invoice invoice) {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        Connection cn = null;
        PreparedStatement ps = null;
        List<Drink> drinks = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_DRINKS);
            ps.setInt(1, invoice.getId());
            ResultSet resultSet = ps.executeQuery();

            //Инициализируем коллекцию напитков
            drinks = new ArrayList<Drink>();

            while (resultSet.next()) {
                Drink drink = new Drink();
                drink.setId(resultSet.getInt("DrinkID"));
                drink.setCode(resultSet.getString("DrinkCode"));
                drink.setName(resultSet.getString("DrinkName"));
                drink.setPrice(resultSet.getDouble("DrinkPrice"));
                drink.setQuantity(resultSet.getInt("DrinkQuantity"));
                //Добавить вложенные ингредиенты
                drink.setIngredients(this.findIngredients(resultSet.getInt("OrderedDrinkID")));
                drinks.add(drink);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
        } finally {
            close(ps);
            close(cn);
        }

        return drinks;
    }

    public List<Ingredient> findIngredients(int orderedDrinkId) {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        Connection cn = null;
        PreparedStatement ps = null;
        List<Ingredient> ingredients = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_INGREDIENTS);
            ps.setInt(1, orderedDrinkId);
            ResultSet resultSet = ps.executeQuery();

            //Инициализируем коллекцию ингредиентов
            ingredients = new ArrayList<Ingredient>();

            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("IngredientID"));
                ingredient.setCode(resultSet.getString("IngredientCode"));
                ingredient.setName(resultSet.getString("IngredientName"));
                ingredient.setPrice(resultSet.getDouble("IngredientPrice"));
                ingredients.add(ingredient);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
        } finally {
            close(ps);
            close(cn);
        }

        return ingredients;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Order entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean create(Order entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean create(int invoiceID, Drink drink) {

        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        Connection cn = null;
        PreparedStatement ps = null;
        Statement st = null;

        try {
            cn = ConnectionPool.getConnection();
            //Отключаем авто коммит
            cn.setAutoCommit(false);
            //Сохраняем заказанный напиток Ordered Drink
            ps = cn.prepareStatement(SQL_INSERT_NEW_ORDERED_DRINK);
            ps.setInt(1, drink.getId());
            ps.setInt(2, invoiceID);
            ps.executeUpdate();

            //Уменьшаем значение купленного  напитка 1
            ps = cn.prepareStatement(SQL_UPDATE_DRINK_QUANTITY);
            ps.setString(1, drink.getCode());
            ps.executeUpdate();


            //Получаем последний Ordered Drink ID
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LAST_ORDERED_DRINK_ID);
            resultSet.next();
            int orderedDrinkId = resultSet.getInt("IDENTITY");
            //Получаем список ингредиентов напитка
            List<Ingredient> orderedIngredients = drink.getIngredients();
            //Сохраняем список заказанных ингредиентов в базе данных
            for (Ingredient ingredient : orderedIngredients) {
                //Сохраняем заказанный инредиент Ordered Ingredient
                ps = cn.prepareStatement(SQL_INSERT_NEW_ORDERED_INGREDIENT);
                ps.setInt(1, ingredient.getId());
                ps.setInt(2, orderedDrinkId);
                ps.executeUpdate();

                //Уменьшаем значение купленного ингредиента на 1
                ps = cn.prepareStatement(SQL_UPDATE_INGREDIENT_QUANTITY);
                ps.setString(1, ingredient.getCode());
                ps.executeUpdate();

            }

            //Комитим изменения в базу
            cn.commit();
            return true;

        } catch (SQLException ex) {
            try {
                ex.printStackTrace();
                log.error("SQL error: " + ex.toString());
                cn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                log.error("SQL error: " + ex.toString());
            }
            return false;
        } finally {
            close(ps);
            close(st);
            close(cn);
        }

    }

    @Override
    public Order update(Order entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
