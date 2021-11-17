package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.entity.*;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class FineDaoTest {

    static Context context;
    History history;
    User user;
    Car car;
    Fine fine;
    int fineID;
    int historyID;
    int userID;
    int carID;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        PropertyConfigurator.configure("src/main/webapp/WEB-INF/log4j.properties");
        ds.setURL("jdbc:mysql://localhost:3306/bestcar?autoReconnect=true&amp;serverTimezone=UTC");
        ds.setUser("root");
        ds.setPassword("FO096msu!");
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        context = new InitialContext();
        Context ctx = context.createSubcontext("java");
        ctx.createSubcontext("comp").createSubcontext("env").createSubcontext("jdbc")
                .bind("UserDB", ds);
        context.bind("java:", ctx);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.destroySubcontext("java");
        context.unbind("java:");
        context.close();
    }

    @Before
    public void before(){
        user = new User("Vladik", "Test", "765765765", "pass");
        new UserDaoIml().insertUser(user);
        userID = new UserDaoIml().getUserId("765765765", "pass");
        car = new Car("brand", "name", "C", "color", 1000, "photo", "description");
        new CarDaoIml().insertCar(car);
        carID = new CarDaoIml().getCarID(car.getName());
        history = new History(userID, carID, true, 2, 500);
        new HistoryDaoIml().insertHistory(history);
        List<History> h = new HistoryDaoIml().selectHistory(userID);
        History history = h.get(0);
        historyID = history.getId();
        fine = new Fine(historyID, userID, carID, "Low", "123", 1000);
    }

    @Test
    public void FineTest() throws Exception {
        new FineDaoIml().insertFine(fine);
        Assert.assertTrue(new FineDaoIml().finesExist(userID));
        assertNotNull(new FineDaoIml().selectFines(userID));
    }

    @After
    public void deleteFine() throws Exception {
        new FineDaoIml().deleteFine(fineID);
        new HistoryDaoIml().deleteHistory(userID);
        new UserDaoIml().deleteUser(userID);
        new CarDaoIml().deleteCar(carID);
    }

}
