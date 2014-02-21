import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Glenn on 21/02/14.
 */
public class TestJSONController {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void TestGetEmptyAchievements() {
        driver.get("http://localhost:8080/api/getachievements?username=jefke");
        String result = driver.findElement(By.cssSelector("body")).getText();
        assertEquals("expect an empty JSON String", "{\"username\":\"jefke\",\"achievements\":[]}", result);
    }

    @Test
    public void TestGetSomeAchievements() {

        driver.get("http://localhost:8080/api/getachievements?username=andy");
        String result = driver.findElement(By.cssSelector("body")).getText();
        assertEquals("expect an empty JSON String", "{\"username\":\"andy\",\"achievements\":[{\"id\":1,\"title\":\"Minesweeper\",\"description\":\"Sweep 5 mines with one miner\"},{\"id\":2,\"title\":\"Win\",\"description\":\"Win one game\"},{\"id\":3,\"title\":\"more wins\",\"description\":\"win 10 games\"},{\"id\":4,\"title\":\"im a pro\",\"description\":\"win 100 games\"},{\"id\":5,\"title\":\"do you even lift bro\",\"description\":\"beat a marshal with a spy 10 times\"}]}", result);
    }
}
