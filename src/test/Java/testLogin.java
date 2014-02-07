import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Thomas on 06/02/14.
 */
public class testLogin {
  /*  @org.junit.Test
    public void testFouteLogin() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/login.jsp");

        WebElement nameField = driver.findElement(By.name("userName"));
        nameField.sendKeys("blablabla");

        WebElement pwField = driver.findElement(By.name("password"));
        pwField.sendKeys("blablabla");

        pwField.submit();
        assertEquals("This login should fail and redirect to login.", driver.getCurrentUrl(), "http://localhost:8080/login.jsp");
    }      */
}
