/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.User;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.UserDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestJSONController {
    WebDriver driver;

    private User user = new User("testUser", "testPassword", "testEmail");
    private UserDAOApi operations = new UserDAOImpl();

    @Before
    public void setProperty(){
        operations.insertNewUser(user);
        File file = new File("C:\\Users\\Stijn\\Downloads\\Selenium-Google\\chromedriver.exe");
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
    }

    @Test
    public void testGameHistory() {
        driver.get("http://localhost:8080/api/user/getGameHistory?username="+user.getUsername());
        String result = driver.findElement(By.cssSelector("body")).getText();
        String expected = "{\"games\":[],\"username\":\""+user.getUsername()+"\"}";
        assertEquals("Expect a reply of verification", expected, result);
    }

    @Test
    public void testGetEmptyAchievements() {
        driver.get("http://localhost:8080/api/user/getachievements?username=jefke");
        String result = driver.findElement(By.cssSelector("body")).getText();
        assertEquals("expect an empty JSON String", "{\"username\":\"jefke\",\"achievements\":[]}", result);
    }

    @Test
    public void testGetSomeAchievements() {
        driver.get("http://localhost:8080/api/user/getachievements?username=andy");
        String result = driver.findElement(By.cssSelector("body")).getText();
        assertEquals("expect an empty JSON String", "{\"username\":\"andy\",\"achievements\":[{\"id\":1,\"title\":\"Minesweeper\",\"description\":\"Sweep 5 mines with one miner\"},{\"id\":2,\"title\":\"Win\",\"description\":\"Win one game\"},{\"id\":3,\"title\":\"more wins\",\"description\":\"win 10 games\"},{\"id\":4,\"title\":\"im a pro\",\"description\":\"win 100 games\"},{\"id\":5,\"title\":\"do you even lift bro\",\"description\":\"beat a marshal with a spy 10 times\"}]}", result);
    }

    @Test
    public void testGetStats() {
        driver.get("http://localhost:8080/api/user/getStats?username="+user.getUsername());
        String result = driver.findElement(By.cssSelector("body")).getText();
        String expected = "{\"myRank\":"+operations.getRank(user)+",\"losses\":0,\"maxRank\":"+operations.getRank(user)+",\"gamesPlayer\":0,\"wins\":0}";
        assertEquals("Expect a reply of verification", expected, result);
    }

    @Test
    public void TestGetFriends() {
        driver.get("http://localhost:8080/api/getFriends?username="+user.getUsername());
        String result = driver.findElement(By.cssSelector("body")).getText();
        String expected = "{\"username\":\""+user.getUsername()+"\",\"friends\":"+user.getFriends()+"}";

        assertEquals("Checkt of de string vrienden wordt weergeven", expected, result);
    }
}
