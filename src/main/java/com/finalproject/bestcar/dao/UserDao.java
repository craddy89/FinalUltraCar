package com.finalproject.bestcar.dao;

import com.finalproject.bestcar.entity.User;

import java.util.List;


public interface UserDao {

    //CREATE
    void insertUser(User user);
    void insertModerator(User user);

    //READ
    List<User> getAllUsers();
    Integer getUserId(String name, String password);
    User getUser(Integer id);
    boolean phoneExists(String phone);
    boolean userExists(String phone, String password);
    String getUserRole(String phone, String password);
    boolean getActive(String phone);
    Integer getUserMoney(Integer id);

    //UPDATE
    void updateActive(boolean active, String phone);
    void addMoney(Integer id, Integer money);

    //DELETE
    void deleteUser(Integer userId);

}
