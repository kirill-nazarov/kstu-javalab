package kz.kstu.coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.kstu.coffee.entities.Ingredient;
import kz.kstu.coffee.pool.ConnectionPool;

/**
 *
 * @author Kirill
 */
public class IngredientDAO extends AbstractDAO<Ingredient> {

    public static final String SQL_SELECT_ALL_INGREDIENTS = ""
            + "SELECT * FROM ingredient";
    public static final String SQL_FIND_INGREDIENT_BY_CODE = ""
            + "SELECT * FROM ingredient "
            + " WHERE IngredientCode = ?";
    public static final String SQL_FIND_INGREDIENT_BY_ID = ""
            + "SELECT * FROM ingredient "
            + " WHERE IngredientID = ?";
    public static final String SQL_UPDATE_INGREDIENT_QUANTITY = ""
            + "UPDATE ingredient SET IngredientQuantity = (IngredientQuantity + ?)"
            + " WHERE IngredientCode = ?";

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_INGREDIENTS);

            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("IngredientID"));
                ingredient.setCode(resultSet.getString("IngredientCode"));
                ingredient.setName(resultSet.getString("IngredientName"));
                ingredient.setPrice(resultSet.getDouble("IngredientPrice"));
                ingredient.setQuantity(resultSet.getInt("IngredientQuantity"));
                ingredients.add(ingredient);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(st);
            close(cn);
        }

        return ingredients;
    }

    @Override
    public Ingredient findEntityById(int id) {
        Connection cn = null;
        PreparedStatement ps = null;
        Ingredient ingredient = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_FIND_INGREDIENT_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("IngredientID"));
                ingredient.setCode(resultSet.getString("IngredientCode"));
                ingredient.setName(resultSet.getString("IngredientName"));
                ingredient.setPrice(resultSet.getDouble("IngredientPrice"));
                ingredient.setQuantity(resultSet.getInt("IngredientQuantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }

        return ingredient;
    }

    @Override
    public int findEntityByID(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ingredient findIngredientByCode(String code) {
        Connection cn = null;
        PreparedStatement ps = null;
        Ingredient ingredient = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_FIND_INGREDIENT_BY_CODE);
            ps.setString(1, code);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("IngredientID"));
                ingredient.setCode(resultSet.getString("IngredientCode"));
                ingredient.setName(resultSet.getString("IngredientName"));
                ingredient.setPrice(resultSet.getDouble("IngredientPrice"));
                ingredient.setQuantity(resultSet.getInt("IngredientQuantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }

        return ingredient;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean create(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ingredient update(Ingredient ingredient, int quantity) {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE_INGREDIENT_QUANTITY);
            ps.setInt(1, quantity);
            ps.setString(2, ingredient.getCode());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }
        return ingredient;
    }
}
