package com.finalproject.bestcar.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Method for closing a connection, ResultSet and Statement in DAO
 *
 */

public class Closing {

    private Closing() {
    }

    private static final Logger LOG = Logger.getLogger(Closing.class);

    public static void close(ResultSet rs, Statement st, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("ResultSet.close SQLException: " + ex.getMessage());
            }
        }
        if(st != null){
            try {
                st.close();
            }catch (SQLException ex){
                LOG.error("Statement.close SQLException: " + ex.getMessage());
            }
        }
        if(con != null){
            try {
                con.close();
            }catch (SQLException ex){
                LOG.error("Connection.close SQLException: " + ex.getMessage());
            }
        }
    }

}
