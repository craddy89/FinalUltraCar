package com.finalproject.bestcar.dao;

import com.finalproject.bestcar.entity.History;

import java.util.List;


public interface HistoryDao {

    //CREATE
    void insertHistory(History history);

    //SELECT
    List<History> selectHistory(Integer userId);
    List<History> selectModeration(String status);

    //UPDATE
    void activeUpdate(History history);
    void banUpdate(History history);
    void returnCar(History history);

    //DELETE
    void deleteHistory(Integer userID);

}
