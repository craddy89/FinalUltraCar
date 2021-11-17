package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.entity.Passport;
import com.finalproject.bestcar.entity.User;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import static org.junit.Assert.*;


public class PassportDaoTest {

    static Context context;
    Passport passport;
    User user;

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
        passport = new Passport("MT", 333333, "Kiev", "1996.01.29");
    }

    @Test
    public void PassportTest() throws Exception {
        int userID = new UserDaoIml().getUserId("765765765", "pass");
        new PassportDaoIml().insertPassport(passport, userID);
        Assert.assertEquals(new PassportDaoIml().getPassport(userID).toString().trim(), passport.toString().trim());
        Assert.assertTrue(new PassportDaoIml().passportExist(userID));
        Passport passportTest = new Passport();
        passportTest.setUserId(1);
        assertNotNull(passportTest.getUserId());
        passportTest.setId(2);
        assertNotNull(passportTest.getId());
    }

    @After
    public void deletePassport() throws Exception {
        int userID = new UserDaoIml().getUserId("765765765", "pass");
        new PassportDaoIml().deletePassport(userID);
        new UserDaoIml().deleteUser(userID);
    }


}
