package com.finalproject.bestcar.dao;

import com.finalproject.bestcar.entity.Car;

import java.util.List;


public interface CarDao {

    //CREATE
    void insertCar(Car car);

    //READ
    List<Car> getAllCars(String brand, String carClass, String orderBy, Integer page, Integer pageSize);
    List<Car> getAllCars();
    Car getCar(Integer carId);
    Integer getCarCount();
    List<String> getAllBrands();
    List<String> getAllClasses();
    Integer getCarID(String name);
    boolean getCarActive(Integer id);

    //UPDATE
    void updateCar(Car car);
    void updateCarActive(boolean active, Integer carId);

    //DELETE
    void deleteCar(Integer id);

}
