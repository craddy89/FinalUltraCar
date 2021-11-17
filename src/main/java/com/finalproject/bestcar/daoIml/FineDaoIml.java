package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.connectionPool.ConnectionPool;
import com.finalproject.bestcar.dao.FineDao;
import com.finalproject.bestcar.dbConstant.DBCommand;
import com.finalproject.bestcar.entity.Fine;
import com.finalproject.bestcar.util.Closing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for FineDao Interface
 */

public class FineDaoIml implements FineDao {

    private static final Logger LOG = Logger.getLogger(FineDaoIml.class);

    /**
     * Insert fine for moderator menu
     */
    @Override
    public void insertFine(Fine fine) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_FINE);
            ps.setInt(1, fine.getHistoryId());
            ps.setInt(2, fine.getUserId());
            ps.setInt(3, fine.getCarId());
            ps.setString(4, fine.getDamage());
            ps.setString(5, fine.getDamageDesc());
            ps.setInt(6, fine.getSum());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT FINE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Select all user fines
     */
    @Override
    public List<Fine> selectFines(Integer userId) {
        Connection connection = null;
        List<Fine> fines = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Fine fine;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.SELECT_FINES);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                fine = new Fine();
                fine.setId(rs.getInt(1));
                fine.setHistoryId(rs.getInt(2));
                fine.setUserId(rs.getInt(3));
                fine.setCarId(rs.getInt(4));
                fine.setDamage(rs.getString(5));
                fine.setDamageDesc(rs.getString(6));
                fine.setSum(rs.getInt(7));
                fines.add(fine);
            }
        } catch (SQLException e) {
            LOG.error("SELECT FINES EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return fines;
    }

    /**
     * Remove fine after payment
     */
    @Override
    public void deleteFine(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.DELETE_FINE);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DELETE FINE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Check for fines
     */
    @Override
    public Boolean finesExist(Integer userId) {
        Connection connection = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FINES_EXISTS);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("FINES EXIST EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return count > 0;
    }

}
