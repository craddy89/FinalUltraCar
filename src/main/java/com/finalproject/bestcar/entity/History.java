package com.finalproject.bestcar.entity;


public class History {

    private Integer id;
    private Integer userId;
    private Integer carId;
    private boolean isDriver;
    private Integer days;
    private Integer price;
    private String rentStatus;
    private String dateStart;
    private String dateFinish;
    private String description;

    public History() {
    }

    public History(Integer userId, Integer carId, boolean isDriver, Integer days, Integer price) {
        this.userId = userId;
        this.carId = carId;
        this.isDriver = isDriver;
        this.days = days;
        this.price = price;
    }

    public History(Integer id, String dateStart, String dateFinish, String rentStatus) {
        this.id = id;
        this.rentStatus = rentStatus;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public History(Integer id, String description, String rentStatus) {
        this.id = id;
        this.rentStatus = rentStatus;
        this.description = description;
    }

    public History(Integer id, String rentStatus) {
        this.id = id;
        this.rentStatus = rentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

}
