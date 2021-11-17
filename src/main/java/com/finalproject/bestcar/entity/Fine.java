package com.finalproject.bestcar.entity;

public class Fine {

    private Integer id;
    private Integer historyId;
    private Integer userId;
    private Integer carId;
    private String damage;
    private String damageDesc;
    private Integer sum;

    public Fine() {
    }

    public Fine(Integer historyId, Integer userId, Integer carId, String damage, String damageDesc, Integer sum) {
        this.historyId = historyId;
        this.userId = userId;
        this.carId = carId;
        this.damage = damage;
        this.damageDesc = damageDesc;
        this.sum = sum;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getDamageDesc() {
        return damageDesc;
    }

    public void setDamageDesc(String damageDesc) {
        this.damageDesc = damageDesc;
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

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
