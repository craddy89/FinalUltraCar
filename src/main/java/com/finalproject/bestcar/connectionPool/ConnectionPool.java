package com.finalproject.bestcar.connectionPool;

import com.finalproject.bestcar.daoIml.CarDaoIml;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(CarDaoIml.class);

    private ConnectionPool(){
        //private constructor
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public synchronized Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            Context envContext = (Context) context.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UserDB");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOG.error("Trouble with getConnection: " + e.getMessage());
        }
        return connection;
    }

}
