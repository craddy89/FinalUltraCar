package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.connectionPool.ConnectionPool;
import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.dbConstant.DBCommand;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.util.Closing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for HistoryDao Interface
 */

public class HistoryDaoIml implements HistoryDao {

    private static final Logger LOG = Logger.getLogger(HistoryDaoIml.class);

    /**
     * Insert history row for user
     */
    @Override
    public void insertHistory(History history) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_HISTORY);
            ps.setInt(1, history.getUserId());
            ps.setInt(2, history.getCarId());
            ps.setBoolean(3, history.isDriver());
            ps.setInt(4, history.getDays());
            ps.setInt(5, history.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT HISTORY EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Select user history
     */
    @Override
    public List<History> selectHistory(Integer userId) {
        Connection connection = null;
        List<History> stories = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        History history;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_HISTORY);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                history = new History();
                history.setId(rs.getInt(1));
                history.setUserId(rs.getInt(2));
                history.setCarId(rs.getInt(3));
                history.setDriver(rs.getBoolean(4));
                history.setDays(rs.getInt(5));
                history.setPrice(rs.getInt(6));
                history.setRentStatus(rs.getString(7));
                history.setDateStart(rs.getString(8));
                history.setDateFinish(rs.getString(9));
                history.setDescription(rs.getString(10));
                stories.add(history);
            }
        } catch (SQLException e) {
            LOG.error("SELECT HISTORY EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return stories;
    }

    /**
     * Get all active history for moderation
     */
    @Override
    public List<History> selectModeration(String status) {
        Connection connection = null;
        List<History> stories = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        History history;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_MODER_HISTORY);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                history = new History();
                history.setId(rs.getInt(1));
                history.setUserId(rs.getInt(2));
                history.setCarId(rs.getInt(3));
                history.setDriver(rs.getBoolean(4));
                history.setDays(rs.getInt(5));
                history.setPrice(rs.getInt(6));
                history.setRentStatus(rs.getString(7));
                history.setDateStart(rs.getString(8));
                history.setDateFinish(rs.getString(9));
                stories.add(history);
            }
        } catch (SQLException e) {
            LOG.error("SELECT MODERATION EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return stories;
    }

    /**
     * Active rent and write in history
     */
    @Override
    public void activeUpdate(History history) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_HISTORY_ACTIVE);
            //UPDATE
            ps.setString(1, history.getDateStart());
            ps.setString(2, history.getDateFinish());
            ps.setString(3, history.getRentStatus());
            //WHERE
            ps.setInt(4, history.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("HISTORY ACTIVE UPDATE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Return the car after use and write in history
     */
    @Override
    public void returnCar(History history) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_HISTORY_RETURN);
            //UPDATE
            ps.setString(1, history.getRentStatus());
            //WHERE
            ps.setInt(2, history.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("HISTORY RETURN CAR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Ban rent for user and write in history
     */
    @Override
    public void banUpdate(History history) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_HISTORY_BAN);
            //UPDATE
            ps.setString(1, history.getDescription());
            ps.setString(2, history.getRentStatus());
            //WHERE
            ps.setInt(3, history.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("HISTORY BAN UPDATE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    @Override
    public void deleteHistory(Integer userID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.DELETE_HISTORY_BY_USER_ID);
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DELETE HISTORY EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }
}
