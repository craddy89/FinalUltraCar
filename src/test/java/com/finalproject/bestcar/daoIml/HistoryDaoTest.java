package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.entity.User;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;

import static org.junit.Assert.assertNotNull;


public class HistoryDaoTest {

    static Context context;
    History history;
    User user;
    Car car;
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
    }

    @Test
    public void HistoryTest() throws Exception {
        new HistoryDaoIml().insertHistory(history);
        List<History> h = new HistoryDaoIml().selectHistory(userID);
        History history = h.get(0);
        assertNotNull(new HistoryDaoIml().selectHistory(userID));
        assertNotNull(new HistoryDaoIml().selectModeration("Moderation"));
        new HistoryDaoIml().activeUpdate(history);
        new HistoryDaoIml().banUpdate(history);
        new HistoryDaoIml().returnCar(history);
    }

    @After
    public void deleteHistory() throws Exception {
        new HistoryDaoIml().deleteHistory(userID);
        new UserDaoIml().deleteUser(userID);
        new CarDaoIml().deleteCar(carID);
    }


}

