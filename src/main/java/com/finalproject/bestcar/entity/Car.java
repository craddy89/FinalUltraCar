package com.finalproject.bestcar.entity;

public class Car {

    private Integer id;
    private String brand;
    private String carClass;
    private String name;
    private int price;
    private String photo;
    private String color;
    private String description;
    private boolean active;

    @Override
    public String toString() {
        return "Car{" +
               "brand='" + brand + '\'' +
               ", carClass='" + carClass + '\'' +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", photo='" + photo + '\'' +
               ", color='" + color + '\'' +
               ", description='" + description + '\'' +
               '}';
    }

    public Car() {
    }

    public Car(String brand, String name, String carClass, String color, int price, String photo, String description) {
        this.brand = brand;
        this.carClass = carClass;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.color = color;
        this.description = description;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
