package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.connectionPool.ConnectionPool;
import com.finalproject.bestcar.dao.PassportDao;
import com.finalproject.bestcar.dbConstant.DBCommand;
import com.finalproject.bestcar.entity.Passport;
import com.finalproject.bestcar.util.Closing;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * DAO implementation for PassportDao Interface
 */

public class PassportDaoIml implements PassportDao {

    private static final Logger LOG = Logger.getLogger(PassportDaoIml.class);

    /**
     * Insert passport
     * @param passport
     * @param userId
     */
    @Override
    public void insertPassport(Passport passport, Integer userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_PASSPORT);
            ps.setInt(1, userId);
            ps.setString(2, passport.getSeries());
            ps.setInt(3, passport.getNumber());
            ps.setString(4, passport.getBy());
            ps.setString(5, passport.getCreateDate());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT PASSPORT EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Get passport for profile
     * @param userId
     * @return
     */
    @Override
    public Passport getPassport(Integer userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Passport passport = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_PASSPORT_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                passport = new Passport();
                passport.setId(rs.getInt(1));
                passport.setSeries(rs.getString(3));
                passport.setNumber(rs.getInt(4));
                passport.setBy(rs.getString(5));
                passport.setCreateDate(rs.getString(6));
            }
        } catch (SQLException e) {
            LOG.error("GET PASSPORT EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return passport;
    }

    /**
     * Passport exist for rent
     * @param userId
     * @return
     */
    @Override
    public boolean passportExist(Integer userId) {
        Connection connection = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.PASSPORT_EXISTS);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("PASSPORT EXIST EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return count > 0;
    }

    /**
     * Delete passport for new insert
     * @param userId
     */
    @Override
    public void deletePassport(Integer userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.DELETE_PASSPORT_BY_USER_ID);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DELETE PASSPORT EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }
}
