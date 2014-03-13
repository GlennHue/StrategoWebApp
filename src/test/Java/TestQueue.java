/**
 * Created by Thomas on 12/03/14.
 */

import be.kdg.beans.LobbyBean;
import be.kdg.beans.UserBean;
import be.kdg.model.User;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.UserDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

import static junit.framework.Assert.*;

public class TestQueue {

    private User user = new User("username", "password", "email");
    private User user2 = new User("username2", "password2", "email2");
    private UserDAOApi operations = new UserDAOImpl();
    UserBean ub = new UserBean();
    UserBean ub2 = new UserBean();
    WebDriver driver;
    LobbyBean lb = new LobbyBean();


    @Before
    public void insertBasicUser() {
        user.setVerified(true);
        operations.insertNewUser(user);
        ub.setUsername(user.getUsername());
        ub.setPassword(user.getPassword());
        ub2.setUsername(user2.getUsername());
        ub2.setPassword(user2.getPassword());
    }

    @Before
    public void setProperty(){
        File file = new File("C:\\Users\\Thomas\\Desktop\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
    }

    @After
    public void closeDriver(){
        driver.close();
        driver.quit();
        if(operations.getUserByUsername(user.getUsername()) != null){
            operations.removeUser(operations.getUserByUsername(user.getUsername()));
        }

        if(operations.getUserByUsername(user2.getUsername()) != null){
            operations.removeUser(operations.getUserByUsername(user2.getUsername()));
        }
    }

    @org.junit.Test
    public void testAdded() throws InterruptedException {
        lb.addUser(ub.getUser());

        assertEquals("De user zou moeten worden toegevoegd aan de queue", lb.getUsers().get(0), ub.getUser());
    }

    @org.junit.Test
    public void testAddSecond() throws InterruptedException {
        lb.addUser(ub.getUser());
        lb.addUser(ub2.getUser());

        assertNull("De user zou nu uit de queue moeten omdat er 2 spelers zijn", lb.getUsers().get(0));
    }

    @org.junit.Test
    public void testRemove() throws InterruptedException {
        lb.addUser(ub.getUser());
        lb.removeUser(ub.getUser());

        assertEquals("De user zou nu uit de queue moeten omdat er 2 spelers zijn", lb.getUsers().size(), 0);
    }




}