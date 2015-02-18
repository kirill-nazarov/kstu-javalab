package kz.kstu.coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.entities.Invoice;
import kz.kstu.coffee.entities.User;
import kz.kstu.coffee.pool.ConnectionPool;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Kirill
 */
public class InvoiceDAO extends AbstractDAO<Invoice> {

    private Logger log;
    public static final String SQL_INSERT_NEW_INVOICE = ""
            + " INSERT INTO Invoice (UserID, InvoiceDate, TotalAmount) "
            + " VALUES (?, NOW(), ?)";
    public static final String SQL_SELECT_LAST_INVOICE_ID = ""
            + " SELECT @@IDENTITY AS IDENTITY";
    public static final String SQL_SELECT_INVOICE_BY_USER_ID = ""
            + " SELECT * FROM Invoice"
            + " WHERE UserID = ?";

    @Override
    public List<Invoice> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Invoice findEntityById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int findEntityByID(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Invoice> findInvoicesByUser(User user) {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        Connection cn = null;
        PreparedStatement ps = null;
        List<Invoice> invoiceList = null;
        Invoice invoice = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_INVOICE_BY_USER_ID);
            ps.setInt(1, user.getId());
            ResultSet resultSet = ps.executeQuery();

            invoiceList = new ArrayList<Invoice>();

            OrderDAO orderDAO = new OrderDAO();
            while (resultSet.next()) {
                invoice = new Invoice();
                invoice.setId(resultSet.getInt("InvoiceID"));
                invoice.setUser(user);
                invoice.setInvoiceDate(resultSet.getDate("InvoiceDate"));
                invoice.setOrderedDrinks(orderDAO.findDrinksByInvoice(invoice));
                invoiceList.add(invoice);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
        } finally {
            close(ps);
            close(cn);
        }

        return invoiceList;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean create(Invoice invoice, User user) {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();

        Connection cn = null;
        PreparedStatement ps = null;
        Statement st = null;

        try {
            cn = ConnectionPool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_INVOICE);
            ps.setInt(1, user.getId());
            ps.setDouble(2, invoice.getInvoiceTotal());
            int executeUpdate = ps.executeUpdate();

            //Получаем последний Invoice ID
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LAST_INVOICE_ID);
            resultSet.next();
            int invoiceId = resultSet.getInt("IDENTITY");

            //Получаем список заказанных напитков
            List<Drink> orderedDrinks = invoice.getOrderedDrinks();
            //Сохраняем список заказанных напитков в базе данных
            OrderDAO orderDAO = new OrderDAO();
            for (Drink drink : orderedDrinks) {
                orderDAO.create(invoiceId, drink);
            }
            log.info( this.getClass() + ". Invoice has been created. User id: " + user.getId());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("SQL error: " + ex.toString());
            return false;
        } finally {
            close(ps);
            close(st);
            close(cn);
        }
    }

    @Override
    public Invoice update(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean create(Invoice entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
