package com.finalproject.bestcar.dao;

import com.finalproject.bestcar.entity.Passport;

public interface PassportDao {

    //CREATE
    void insertPassport(Passport passport, Integer userId);

    //SELECT
    Passport getPassport(Integer userId);
    boolean passportExist(Integer userId);

    //DELETE
    void deletePassport(Integer userId);

}
