package com.finalproject.bestcar.daoIml;

import com.finalproject.bestcar.entity.User;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import static org.junit.Assert.*;

public class UserDaoTest {

    static Context context;
    User user;
    int userID;
    int moderID;

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


        User userTest = new User();
        userTest.setId(1);
        userTest.setMoney(10);
        userTest.setPassword("123");
        userTest.setRegDate("1996");
        userTest.setActive(true);
        assertNotNull(userTest.getId());
        Assert.assertTrue(userTest.getMoney() > 0);
        assertNotNull(userTest.getPassword());
        assertNotNull(userTest.getRegDate());
        Assert.assertTrue(userTest.isActive());

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.destroySubcontext("java");
        context.unbind("java:");
        context.close();
    }

    @Before
    public void before(){
        user = new User("Ivan", "Test", "964765765", "pass");
    }

    @Test
    public void userTest() throws Exception {
        user.setActive(true);
        new UserDaoIml().insertUser(user);
        assertEquals(new UserDaoIml().getUserRole("964765765", "pass"), "User");
        userID = new UserDaoIml().getUserId("964765765", "pass");
        User user2 = new UserDaoIml().getUser(userID);
        assertEquals(user2.toString().trim(), user.toString().trim());

        new UserDaoIml().addMoney(userID, 1000);
        int money = new UserDaoIml().getUserMoney(userID);
        assertEquals(money, 1000);

        assertTrue(new UserDaoIml().getActive("964765765"));
        assertTrue(new UserDaoIml().userExists("964765765", "pass"));
        assertTrue(new UserDaoIml().phoneExists("964765765"));
        new UserDaoIml().updateActive(false, "964765765");
        assertFalse(new UserDaoIml().getActive("964765765"));
    }

    @Test
    public void moderatorTest() throws Exception {
        User moderator = new User("Moderator", "Ivan", "033001111", "test", "Moderator");
        new UserDaoIml().insertModerator(moderator);
        moderID = new UserDaoIml().getUserId("033001111", "test");
        assertEquals(new UserDaoIml().getUser(moderID).toString().trim(), moderator.toString().trim());
    }

    @Test
    public void selectAllUsersTest() throws Exception{
        assertNotNull(new UserDaoIml().getAllUsers());
    }

    @After
    public void deleteUser() throws Exception {
        new UserDaoIml().deleteUser(userID);
        new UserDaoIml().deleteUser(moderID);
    }



}
