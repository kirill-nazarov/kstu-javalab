package kz.kstu.coffee.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private static final String DATASOURCE_NAME = "jdbc/coffeeDB";
    private static final String COMP_ENV = "java:/comp/env";
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(COMP_ENV);
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;

    }
}
