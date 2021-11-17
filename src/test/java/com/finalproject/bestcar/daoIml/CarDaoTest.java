package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.entity.Car;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import static org.junit.Assert.*;

public class CarDaoTest {

    static Context context;
    Car car;
    int id;

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
        car = new Car("brand", "name", "C", "Blue", 1000, "photo", "description");
    }

    @Test
    public void carTest() throws Exception {
        new CarDaoIml().insertCar(car);
        id = new CarDaoIml().getCarID(car.getName());
        car.setId(id);
        Assert.assertEquals(new CarDaoIml().getCar(id).toString().trim(), car.toString().trim());
        new CarDaoIml().updateCarActive(false, id);
        Assert.assertFalse(new CarDaoIml().getCarActive(id));
        Assert.assertTrue(new CarDaoIml().getCarCount() > 0);
        car.setColor("Blue");
        new CarDaoIml().updateCar(car);
        car = new CarDaoIml().getCar(id);
        Assert.assertEquals(new CarDaoIml().getCar(id).toString().trim(), car.toString().trim());
    }

    @Test
    public void selectAllTest() throws Exception{
        assertNotNull(new CarDaoIml().getAllCars());
        assertNotNull(new CarDaoIml().getAllBrands());
        assertNotNull(new CarDaoIml().getAllClasses());
        assertNotNull(new CarDaoIml().getAllCars("id", "", "", 1, 9));
    }

    @After
    public void deleteCar() throws Exception {
        new CarDaoIml().deleteCar(id);
    }


}
