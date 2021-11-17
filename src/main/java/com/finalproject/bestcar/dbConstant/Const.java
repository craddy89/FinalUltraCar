package com.finalproject.bestcar.dbConstant;

public class Const {

    protected static final String USER_TABLE = "user";

    protected static final String USER_ID = "id";
    protected static final String USER_NAME = "name";
    protected static final String USER_SURNAME = "surname";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_PHONE = "phone";
    protected static final String USER_MONEY = "money";
    protected static final String USER_ROLE = "role";
    protected static final String USER_ACTIVE = "active";

    protected static final String CAR_TABLE = "car";

    protected static final String CAR_ID = "id";
    protected static final String CAR_BRAND = "brand";
    protected static final String CAR_NAME = "name";
    protected static final String CAR_CLASS = "car_class";
    protected static final String CAR_COLOR = "color";
    protected static final String CAR_PRICE = "price";
    protected static final String CAR_PHOTO = "photo";
    protected static final String CAR_DESCRIPTION = "description";
    protected static final String CAR_ACTIVE = "active";

    protected static final String PASSPORT_TABLE = "passport";

    protected static final String PASSPORT_ID = "id";
    protected static final String PASSPORT_USER_ID = "userID";
    protected static final String PASSPORT_SERIES = "series";
    protected static final String PASSPORT_NUMBER = "num";
    protected static final String PASSPORT_WHO_MADE = "who_made";
    protected static final String PASSPORT_CREATE_DATE = "createDate";

    protected static final String HISTORY_TABLE = "history";

    protected static final String HISTORY_ID = "id";
    protected static final String HISTORY_USER_ID = "userID";
    protected static final String HISTORY_CAR_ID = "carID";
    protected static final String HISTORY_DRIVER = "driver";
    protected static final String HISTORY_DAYS = "days";
    protected static final String HISTORY_PRICE = "price";
    protected static final String HISTORY_RENT_STATUS = "rent_status";
    protected static final String HISTORY_DATE_START = "date_start";
    protected static final String HISTORY_DATE_FINISH = "date_finish";
    protected static final String HISTORY_DESCRIPTION = "ban_description";

    protected static final String FINE_TABLE = "fine";

    protected static final String FINE_ID = "id";
    protected static final String FINE_HISTORY_ID = "historyID";
    protected static final String FINE_USER_ID = "userID";
    protected static final String FINE_CAR_ID = "carID";
    protected static final String FINE_DAMAGE = "damage";
    protected static final String FINE_DAMAGE_DESC = "damage_desc";
    protected static final String FINE_SUM = "sum";


}
