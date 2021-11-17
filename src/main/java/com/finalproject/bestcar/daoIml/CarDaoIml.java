package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.connectionPool.ConnectionPool;
import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.dbConstant.DBCommand;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.util.Closing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * DAO implementation for CarDao Interface
 */

public class CarDaoIml implements CarDao{

    private static final Logger LOG = Logger.getLogger(CarDaoIml.class);

    /**
     * Insert car for admin menu
     */
    @Override
    public void insertCar(Car car) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.INSERT_CAR);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getName());
            ps.setString(3, car.getCarClass());
            ps.setString(4, car.getColor());
            ps.setInt(5, car.getPrice());
            ps.setString(6, car.getPhoto());
            ps.setString(7, car.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("INSERT CAR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Get car count for pagination
     */
    @Override
    public Integer getCarCount() {
        Connection connection = null;
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.ACTIVE_CAR_COUNT);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("CAR COUNT EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return count;
    }

    /**
     * Get all cars for admin menu
     */
    @Override
    public List<Car> getAllCars() {
        Connection connection = null;
        List<Car> cars = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Car car;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_ALL_CARS);
            rs = ps.executeQuery();
            while (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(1));
                car.setBrand(rs.getString(2));
                car.setName(rs.getString(3));
                car.setCarClass(rs.getString(4));
                car.setColor(rs.getString(5));
                car.setPrice(rs.getInt(6));
                car.setPhoto(rs.getString(7));
                car.setDescription(rs.getString(8));
                car.setActive(rs.getBoolean(9));
                cars.add(car);
            }
        } catch (SQLException e) {
            LOG.error("GET ALL CARS EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return cars;
    }

    /**
     * Get all active cars for user menu + navigation + sort
     */
    @Override
    public List<Car> getAllCars(String brand, String carClass, String orderBy, Integer page, Integer pageSize) {
        Connection connection = null;
        List<Car> cars = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Car car;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if(brand.equals("") && carClass.equals(""))
                ps = connection.prepareStatement("SELECT * FROM car WHERE active=true ORDER BY " + orderBy + " LIMIT " + pageSize + " OFFSET " + pageSize * (page-1));
            if(brand.equals("") && !carClass.equals(""))
                ps = connection.prepareStatement("SELECT * FROM car WHERE active=true AND car_class='" + carClass + "' ORDER BY " + orderBy + " LIMIT " + pageSize + " OFFSET " + pageSize * (page-1));
            if(!brand.equals("") && carClass.equals(""))
                ps = connection.prepareStatement("SELECT * FROM car WHERE active=true AND brand='"+brand+"' ORDER BY " + orderBy + " LIMIT " + pageSize + " OFFSET " + pageSize * (page-1));
            if(!brand.equals("") && !carClass.equals(""))
                ps = connection.prepareStatement("SELECT * FROM car WHERE active=true AND brand='"+brand+"' AND car_class='"+carClass+"' ORDER BY " + orderBy + " LIMIT " + pageSize + " OFFSET " + pageSize * (page-1));
            rs = ps.executeQuery();
            while (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(1));
                car.setBrand(rs.getString(2));
                car.setName(rs.getString(3));
                car.setCarClass(rs.getString(4));
                car.setColor(rs.getString(5));
                car.setPrice(rs.getInt(6));
                car.setPhoto(rs.getString(7));
                car.setDescription(rs.getString(8));
                car.setActive(rs.getBoolean(9));
                cars.add(car);
            }
        } catch (SQLException e) {
            LOG.error("GET ALL CARS EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return cars;
    }

    /**
     * Get car for the functionality of all site data
     */
    @Override
    public Car getCar(Integer carId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Car car = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_CAR_BY_ID);
            ps.setInt(1, carId);
            rs = ps.executeQuery();
            if (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(1));
                car.setBrand(rs.getString(2));
                car.setName(rs.getString(3));
                car.setCarClass(rs.getString(4));
                car.setColor(rs.getString(5));
                car.setPrice(rs.getInt(6));
                car.setPhoto(rs.getString(7));
                car.setDescription(rs.getString(8));
                car.setActive(rs.getBoolean(9));
            }
        } catch (SQLException e) {
            LOG.error("GET CAR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return car;
    }

    /**
     * Update car for admin menu
     */
    @Override
    public void updateCar(Car car) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_CAR);
            //UPDATE
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getName());
            ps.setString(3, car.getCarClass());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getPhoto());
            ps.setString(6, car.getDescription());
            ps.setInt(7, car.getPrice());
            //WHERE
            ps.setInt(8, car.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("UPDATE CAR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Delete car for admin menu
     */
    @Override
    public void deleteCar(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.DELETE_CAR_BY_ID);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("DELETE CAR EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Update vehicle activity to indicate whether it is in use or not
     */
    @Override
    public void updateCarActive(boolean active, Integer carId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.UPDATE_CAR_ACTIVE);
            ps.setBoolean(1, active);
            ps.setInt(2, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error("UPDATE CAR ACTIVE: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
    }

    /**
     * Get all brands for navigation by brand
     */
    @Override
    public List<String> getAllBrands() {
        Connection connection = null;
        List<String> brands = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_ALL_BRANDS);
            rs = ps.executeQuery();
            while (rs.next()) {
                brands.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOG.error("GET ALL BRANDS EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return brands;
    }

    /**
     * Get all car classes for navigation by car class
     */
    @Override
    public List<String> getAllClasses() {
        Connection connection = null;
        List<String> classes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_ALL_CLASSES);
            rs = ps.executeQuery();
            while (rs.next()) {
                classes.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOG.error("GET ALL CLASSES EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return classes;
    }

    @Override
    public Integer getCarID(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_CAR_ID);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("GET CAR ID EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return id;
    }

    @Override
    public boolean getCarActive(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean active = true;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(DBCommand.FIND_CAR_ACTIVE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                active = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            LOG.error("CAR GET ACTIVE EXCEPTION: " + e.getMessage());
        } finally {
            Closing.close(rs, ps, connection);
        }
        return active;
    }
}
