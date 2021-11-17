package com.finalproject.bestcar.entity;

public class Passport {

    private Integer id;
    private Integer userId;
    private String series;
    private Integer number;
    private String by;
    private String createDate;

    public Passport() {

    }

    public Passport(String series, Integer number, String by, String createDate) {
        this.series = series;
        this.number = number;
        this.by = by;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Passport{" +
               "series='" + series + '\'' +
               ", number=" + number +
               ", by='" + by + '\'' +
               ", createDate='" + createDate + '\'' +
               '}';
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


}
