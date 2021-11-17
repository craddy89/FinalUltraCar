package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.connectionPool.ConnectionPool;
import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.dbConstant.DBCommand;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.util.Closing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for UserDao Interface
 */

public class UserDaoIml implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoIml.class);

    /**
     * Registration
     */
    @Override
    public void insertUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_USER);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT USER EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Create new moderator
     */
    @Override
    public void insertModerator(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_MODERATOR);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT MODERATOR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Phone exist for register validation
     */
    @Override
    public boolean phoneExists(String phone) {
        Connection connection = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.PHONE_EXISTS);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("PHONE EXIST EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return count > 0;
    }

    /**
     * User exist for login validation
     */
    @Override
    public boolean userExists(String phone, String password) {
        Connection connection = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.USER_EXISTS);
            ps.setString(1, phone);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("USER EXIST EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return count > 0;
    }

    /**
     * Get user role for redirect
     */
    @Override
    public String getUserRole(String phone, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String role = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_USER_ROLE_BY_PHONE_AND_PASSWORD);
            ps.setString(1, phone);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error("GET USER ROLE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return role;
    }

    /**
     * Get user id for login, moderator menu, history and fines
     */
    @Override
    public Integer getUserId(String phone, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_USER_ID_BY_PHONE_AND_PASSWORD);
            ps.setString(1, phone);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("GET USER ID EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return id;
    }

    /**
     * Get user id for admin menu, moderator menu, history and fines
     */
    @Override
    public User getUser(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setMoney(rs.getInt(9));
                user.setRegDate(rs.getString(7));
            }
        } catch (SQLException e) {
            LOG.error("GET USER EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return user;
    }

    /**
     * Get user active for login
     */
    @Override
    public boolean getActive(String phone) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean active = true;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_USER_ACTIVE_BY_PHONE);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                active = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            LOG.error("USER GET ACTIVE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return active;
    }

    /**
     * Update user active for admin menu
     */
    @Override
    public void updateActive(boolean active, String phone) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_USER_ACTIVE);
            ps.setBoolean(1, active);
            ps.setString(2, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("USER UPDATE ACTIVE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Get all users for admin menu
     */
    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_ALL_USERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setRole(rs.getString(8));
                user.setRegDate(rs.getString(7));
                user.setActive(rs.getBoolean(6));
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("GET ALL USERS EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return users;
    }

    /**
     * Add user money
     */
    @Override
    public void addMoney(Integer id, Integer money) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.ADD_MONEY_BY_USER_ID);
            ps.setInt(1, money);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ADD MONEY EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Get user money for profile and validations
     */
    @Override
    public Integer getUserMoney(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int money = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_USER_MONEY_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                money = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            LOG.error("GET USER MONEY EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return money;
    }

    @Override
    public void deleteUser(Integer userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.DELETE_USER_BY_ID);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DELETE USER EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }
}
