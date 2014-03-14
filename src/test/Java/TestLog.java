/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.beans.RegisterBean;
import be.kdg.beans.UserBean;
import be.kdg.model.User;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.UserDAOImpl;
import be.kdg.service.api.UserServiceApi;
import be.kdg.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static junit.framework.Assert.*;

public class TestLog {

    private User user = new User("testUser", "testPassword", "testEmail");
    private UserDAOApi operations = new UserDAOImpl();

    WebDriver driver;

    @Before
    public void setProperty(){


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



   @org.junit.Test
    public void testFouteRegister() throws InterruptedException {

        driver.get("http://localhost:8080/register.xhtml");

       WebElement nameField = driver.findElement(By.id("regsFrm:userName"));
       nameField.sendKeys(user.getUsername());

       WebElement emField = driver.findElement(By.id("regsFrm:email"));
       emField.sendKeys(user.geteMail());

       WebElement pwField = driver.findElement(By.id("regsFrm:password"));
       pwField.sendKeys(user.getPassword());

       WebElement pwcField = driver.findElement(By.id("regsFrm:passwordConfirm"));
       pwcField.sendKeys("blablabla");

        pwField.submit();

       User u = operations.getUserByUsername(user.getUsername());

        assertNull("This register attempt should fail.", u);

    }

    @org.junit.Test
    public void testAanmakenUser() throws InterruptedException {
        user.setVerified(true);
        operations.insertNewUser(user);

        assertNotNull("This register attempt should succeed.", operations.getUserByUsername(user.getUsername()));
    }

    @org.junit.Test
    public void testUsEquals() throws InterruptedException {
        User u = user;
        assertEquals("Test of de user overeenkomt via de methode.",u.equals(user), true);
    }

        @org.junit.Test
    public void testRegBeanFail() throws InterruptedException {
        RegisterBean rb = new RegisterBean();
        rb.setPassword(user.getPassword());
        rb.setUsername(user.getUsername());
        rb.setConfirmPassword("blablabla");
        rb.setEmail(user.geteMail());

        assertNull("This register attempt should fail and return null.", rb.addNewUser());
    }

    @org.junit.Test
    public void testJuisteLogin() throws InterruptedException {

        user.setVerified(true);
        operations.insertNewUser(user);

        driver.get("http://localhost:8080/login.xhtml");

        WebElement link = driver.findElement(By.id("lgFrm:lgBtn"));

        WebElement nameField = driver.findElement(By.id("lgFrm:userName"));
        nameField.sendKeys(user.getUsername());

        WebElement pwField = driver.findElement(By.id("lgFrm:password"));
        pwField.sendKeys(user.getPassword());

        link.click();
        new WebDriverWait(driver, 10);

        driver.get("http://localhost:8080");
        WebElement resultField = driver.findElement(By.id("menuFrm:logName"));

        assertEquals("This login should succeed.", user.getUsername(), resultField.getText());
    }


    @org.junit.Test
    public void testFouteLogin() throws InterruptedException {
        UserBean ub = new UserBean();

        user.setVerified(true);
        operations.insertNewUser(user);

        ub.setUsername(user.getUsername());
        ub.setPassword("blablabla");

        assertFalse("This login should fail.", operations.checkLogin(ub.getUsername(), ub.getPassword()));
    }

    @org.junit.Test
    public void testFouteLogin2() throws InterruptedException {
        UserBean ub = new UserBean();

        user.setVerified(true);
        operations.insertNewUser(user);

        ub.setUsername("blablabla");
        ub.setPassword(user.getPassword());

        assertFalse("This login should fail.", operations.checkLogin(ub.getUsername(), ub.getPassword()));
    }
/*
    @org.junit.Test
    public void testFouteLogin3() throws InterruptedException {

        user.setVerified(true);
        operations.insertNewUser(user);

        driver.get("http://localhost:8080/login.xhtml");

        WebElement link = driver.findElement(By.id("lgFrm:lgBtn"));


        WebElement nameField = driver.findElement(By.id("lgFrm:userName"));
        nameField.sendKeys(user.getUsername());

        WebElement pwField = driver.findElement(By.id("lgFrm:password"));
        pwField.sendKeys("blablabla");

        link.click();

        assertNotSame("This login should fail.", "http://localhost:8080/index.xhtml", driver.getCurrentUrl().substring(0,33));
    }*/


   /*    Dit is veroudert doordat er een confirmatie mail is en wordt niet meer gebruikt.
    @org.junit.Test
    public void testJuisteRegister() throws InterruptedException {

        driver.get("http://localhost:8080/index.xhtml");

        WebElement link = driver.findElement(By.id("regFrm:reg"));
        link.click();
        new WebDriverWait(driver, 10) ;

        WebElement nameField = driver.findElement(By.id("regsFrm:userName"));
        nameField.sendKeys(user.getUsername());

        WebElement emField = driver.findElement(By.id("regsFrm:email"));
        emField.sendKeys(user.geteMail());

        WebElement pwField = driver.findElement(By.id("regsFrm:password"));
        pwField.sendKeys(user.getPassword());

        WebElement pwcField = driver.findElement(By.id("regsFrm:passwordConfirm"));
        pwcField.sendKeys(user.getPassword());

        pwField.submit();

        User u = operations.getUserByUsername(user.getUsername());
        assertNotNull("This register should succeed and make a new user in the db.", u);
    }

     //Deze testen zijn veroudert, werken niet meer via een apart scherm.
    @org.junit.Test
    public void testJuisteLogin() throws InterruptedException {

        driver.get("http://localhost:8080/login.xhtml");


        WebElement link = driver.findElement(By.id("regFrm:reg"));    //Linken naar login button
        link.click();
        new WebDriverWait(driver, 10) ;

        WebElement nameField = driver.findElement(By.id("j_idt6:userName"));
        nameField.sendKeys(user.getUsername());

        WebElement pwField = driver.findElement(By.id("j_idt6:password"));
        pwField.sendKeys(user.getPassword());

        pwField.submit();

        assertSame("This login should succeed.", "http://localhost:8080/index.xhtml", driver.getCurrentUrl().substring(0,33));
    }

/*
    @org.junit.Test
    public void testFouteLogin() throws InterruptedException {
        driver.get("http://localhost:8080/login.xhtml");

        WebElement nameField = driver.findElement(By.id("j_idt6:userName"));
        nameField.sendKeys("blablabla");

        WebElement pwField = driver.findElement(By.id("j_idt6:password"));
        pwField.sendKeys("blablabla");

        pwField.submit();
        assertEquals("This login should fail and redirect to login.", "http://localhost:8080/login.xhtml", driver.getCurrentUrl().substring(0,33));
    }*/

}
