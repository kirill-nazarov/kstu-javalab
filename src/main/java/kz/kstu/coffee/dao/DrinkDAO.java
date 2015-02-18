package kz.kstu.coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.pool.ConnectionPool;

public class DrinkDAO extends AbstractDAO<Drink> {

    public static final String SQL_SELECT_ALL_DRINKS = ""
            + "SELECT * FROM drink";
    public static final String SQL_FIND_DRINK_BY_CODE = ""
            + "SELECT * FROM drink "
            + " WHERE DrinkCode = ?";
    public static final String SQL_FIND_DRINK_BY_ID = ""
            + "SELECT * FROM drink "
            + " WHERE DrinkID = ?";
    public static final String SQL_UPDATE_DRINK_QUANTITY = ""
            + "UPDATE drink SET DrinkQuantity = (DrinkQuantity + ?)"
            + " WHERE DrinkCode = ?";

    @Override
    public List<Drink> findAll() {
        List<Drink> drinks = new ArrayList<Drink>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_DRINKS);

            while (resultSet.next()) {
                Drink drink = new Drink();
                drink.setId(resultSet.getInt("DrinkID"));
                drink.setCode(resultSet.getString("DrinkCode"));
                drink.setName(resultSet.getString("DrinkName"));
                drink.setPrice(resultSet.getDouble("DrinkPrice"));
                drink.setQuantity(resultSet.getInt("DrinkQuantity"));
                drinks.add(drink);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(st);
            close(cn);
        }

        return drinks;
    }

    @Override
    public Drink findEntityById(int id) {
        Connection cn = null;
        PreparedStatement ps = null;
        Drink drink = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_FIND_DRINK_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                drink = new Drink();
                drink.setId(resultSet.getInt("DrinkID"));
                drink.setCode(resultSet.getString("DrinkCode"));
                drink.setName(resultSet.getString("DrinkName"));
                drink.setPrice(resultSet.getDouble("DrinkPrice"));
                drink.setQuantity(resultSet.getInt("DrinkQuantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }

        return drink;
    }

    @Override
    public int findEntityByID(Drink drink) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Drink findDrinkByCode(String code) {
        Connection cn = null;
        PreparedStatement ps = null;
        Drink drink = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_FIND_DRINK_BY_CODE);
            ps.setString(1, code);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                drink = new Drink();
                drink.setId(resultSet.getInt("DrinkID"));
                drink.setCode(resultSet.getString("DrinkCode"));
                drink.setName(resultSet.getString("DrinkName"));
                drink.setPrice(resultSet.getDouble("DrinkPrice"));
                drink.setQuantity(resultSet.getInt("DrinkQuantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }

        return drink;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Drink drink) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean create(Drink drink) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Drink update(Drink drink, int quantity) {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE_DRINK_QUANTITY);
            ps.setInt(1, quantity);
            ps.setString(2, drink.getCode());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(ps);
            close(cn);
        }
        return drink;
    }

    @Override
    public Drink update(Drink entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
