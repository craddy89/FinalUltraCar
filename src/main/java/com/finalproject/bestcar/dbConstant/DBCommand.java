package com.finalproject.bestcar.dbConstant;

public class DBCommand {

    //USER COMMANDS
    public static final String FIND_USER_BY_ID = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=?";
    public static final String INSERT_USER = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME +","+ Const.USER_SURNAME +","+ Const.USER_PHONE +","+ Const.USER_PASSWORD + ")" + " VALUES (?,?,?,?)";
    public static final String INSERT_MODERATOR = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME +","+ Const.USER_SURNAME +","+ Const.USER_PHONE +","+ Const.USER_PASSWORD +","+ Const.USER_ROLE + ")" + " VALUES (?,?,?,?,?)";
    public static final String PHONE_EXISTS = "SELECT COUNT(*) FROM " + Const.USER_TABLE + " WHERE " + Const.USER_PHONE + "=?";
    public static final String USER_EXISTS = "SELECT COUNT(*) FROM " + Const.USER_TABLE + " WHERE " + Const.USER_PHONE + "=? AND " + Const.USER_PASSWORD + "=?";
    public static final String FIND_USER_ROLE_BY_PHONE_AND_PASSWORD = "SELECT " + Const.USER_ROLE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_PHONE + "=? AND " + Const.USER_PASSWORD + "=?";
    public static final String UPDATE_USER_ACTIVE = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_ACTIVE + "=? WHERE " + Const.USER_PHONE + "=?";
    public static final String FIND_USER_ACTIVE_BY_PHONE = "SELECT " + Const.USER_ACTIVE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_PHONE + "=?";
    public static final String FIND_ALL_USERS = "SELECT * FROM " + Const.USER_TABLE + " WHERE role!='Admin'";
    public static final String FIND_USER_ID_BY_PHONE_AND_PASSWORD = "SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_PHONE + "=? AND " + Const.USER_PASSWORD + "=?";
    public static final String ADD_MONEY_BY_USER_ID = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_MONEY + "=? WHERE " + Const.USER_ID + "=?";
    public static final String FIND_USER_MONEY_BY_ID = "SELECT " + Const.USER_MONEY + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=?";

    //CAR COMMANDS
    public static final String INSERT_CAR = "INSERT INTO " + Const.CAR_TABLE + "(" + Const.CAR_BRAND + "," + Const.CAR_NAME + "," + Const.CAR_CLASS + "," + Const.CAR_COLOR + "," + Const.CAR_PRICE + "," + Const.CAR_PHOTO + "," + Const.CAR_DESCRIPTION + ")" + " VALUES (?,?,?,?,?,?,?)";
    public static final String FIND_ALL_CARS = "SELECT * FROM " + Const.CAR_TABLE;
    public static final String FIND_CAR_BY_ID = "SELECT * FROM " + Const.CAR_TABLE + " WHERE " + Const.CAR_ID + "=?";
    public static final String UPDATE_CAR = "UPDATE " + Const.CAR_TABLE + " SET " + Const.CAR_BRAND + "=?," + Const.CAR_NAME + "=?," + Const.CAR_CLASS + "=?," + Const.CAR_COLOR + "=?," + Const.CAR_PHOTO + "=?," + Const.CAR_DESCRIPTION + "=?," + Const.CAR_PRICE + "=? WHERE " + Const.CAR_ID + "=?";
    public static final String DELETE_CAR_BY_ID = "DELETE FROM " + Const.CAR_TABLE + " WHERE " + Const.CAR_ID + "=?";
    public static final String UPDATE_CAR_ACTIVE = "UPDATE " + Const.CAR_TABLE + " SET " + Const.CAR_ACTIVE + "=?" + " WHERE " + Const.CAR_ID + "=?";
    public static final String ACTIVE_CAR_COUNT = "SELECT COUNT(*) FROM " + Const.CAR_TABLE + " WHERE " + Const.CAR_ACTIVE + "=true";
    public static final String FIND_ALL_BRANDS = "SELECT DISTINCT " + Const.CAR_BRAND + " FROM " + Const.CAR_TABLE;
    public static final String FIND_ALL_CLASSES = "SELECT DISTINCT " + Const.CAR_CLASS + " FROM " + Const.CAR_TABLE;
    public static final String FIND_CAR_ID = "SELECT " + Const.CAR_ID + " FROM " + Const.CAR_TABLE + " WHERE " + Const.CAR_NAME + "=?";
    public static final String FIND_CAR_ACTIVE = "SELECT " + Const.CAR_ACTIVE + " FROM " + Const.CAR_TABLE + " WHERE " + Const.CAR_ID + "=?";

    //PASSPORT COMMANDS
    public static final String INSERT_PASSPORT = "INSERT INTO " + Const.PASSPORT_TABLE + "(" + Const.PASSPORT_USER_ID +","+ Const.PASSPORT_SERIES +","+ Const.PASSPORT_NUMBER +","+ Const.PASSPORT_WHO_MADE +","+ Const.PASSPORT_CREATE_DATE  + ")" + " VALUES (?,?,?,?,?)";
    public static final String FIND_PASSPORT_BY_USER_ID = "SELECT * FROM " + Const.PASSPORT_TABLE + " WHERE " + Const.PASSPORT_USER_ID + "=?";
    public static final String PASSPORT_EXISTS = "SELECT COUNT(*) FROM " + Const.PASSPORT_TABLE + " WHERE " + Const.PASSPORT_USER_ID + "=?";
    public static final String DELETE_PASSPORT_BY_USER_ID = "DELETE FROM " + Const.PASSPORT_TABLE + " WHERE " + Const.PASSPORT_USER_ID + "=?";

    //HISTORY COMMANDS
    public static final String INSERT_HISTORY = "INSERT INTO " + Const.HISTORY_TABLE + "(" + Const.HISTORY_USER_ID +","+ Const.HISTORY_CAR_ID +","+ Const.HISTORY_DRIVER +","+ Const.HISTORY_DAYS +","+ Const.HISTORY_PRICE + ") VALUES (?,?,?,?,?)";
    public static final String FIND_HISTORY = "SELECT * FROM " + Const.HISTORY_TABLE + " WHERE " + Const.HISTORY_USER_ID + "=?";
    public static final String FIND_MODER_HISTORY = "SELECT * FROM " + Const.HISTORY_TABLE + " WHERE " + Const.HISTORY_RENT_STATUS + "=?";
    public static final String UPDATE_HISTORY_ACTIVE = "UPDATE " + Const.HISTORY_TABLE + " SET " + Const.HISTORY_DATE_START + "=?, " + Const.HISTORY_DATE_FINISH + "=?, " + Const.HISTORY_RENT_STATUS + "=? WHERE " + Const.HISTORY_ID + "=?";
    public static final String UPDATE_HISTORY_BAN = "UPDATE " + Const.HISTORY_TABLE + " SET " + Const.HISTORY_DESCRIPTION + "=?, " + Const.HISTORY_RENT_STATUS + "=? WHERE " + Const.HISTORY_ID + "=?";
    public static final String UPDATE_HISTORY_RETURN = "UPDATE " + Const.HISTORY_TABLE + " SET " + Const.HISTORY_RENT_STATUS + "=? WHERE " + Const.HISTORY_ID + "=?";
    public static final String DELETE_HISTORY_BY_USER_ID = "DELETE FROM " + Const.HISTORY_TABLE + " WHERE " + Const.HISTORY_USER_ID + "=?";

    //FINE COMMANDS
    public static final String INSERT_FINE = "INSERT INTO " + Const.FINE_TABLE + "(" + Const.FINE_HISTORY_ID + ", " + Const.FINE_USER_ID + ", " + Const.FINE_CAR_ID + ", " + Const.FINE_DAMAGE + ", " + Const.FINE_DAMAGE_DESC + ", " + Const.FINE_SUM + ") VALUES (?,?,?,?,?,?)";
    public static final String SELECT_FINES = "SELECT * FROM " + Const.FINE_TABLE + " WHERE " + Const.FINE_USER_ID + "=?";
    public static final String DELETE_FINE = "DELETE FROM " + Const.FINE_TABLE + " WHERE " + Const.FINE_ID + "=?";
    public static final String FINES_EXISTS = "SELECT COUNT(*) FROM " + Const.FINE_TABLE + " WHERE " + Const.FINE_USER_ID + "=?";

}

