import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by Thomas on 06/02/14.
 */
public class TestLog {

    String tUser= "testUser";
    String tPass = "testPassword";
    String tEmail = "testEmail";
    WebDriver driver = new FirefoxDriver();

 /*   @org.junit.Test
    public void testFouteRegister() throws InterruptedException {
        driver.get("http://localhost:8080/register.xhtml");

        WebElement nameField = driver.findElement(By.id("j_idt6:userName"));
        nameField.sendKeys(tUser);

        WebElement emField = driver.findElement(By.id("j_idt6:email"));
        emField.sendKeys(tEmail);

        WebElement pwField = driver.findElement(By.id("j_idt6:password"));
        pwField.sendKeys(tPass);

        WebElement pwcField = driver.findElement(By.id("j_idt6:passwordConfirm"));
        pwcField.sendKeys("blablabla");

        pwField.submit();
        assertEquals("This register should fail and not switch page because the passwords do not match.", "http://localhost:8080/register.xhtml", driver.getCurrentUrl().substring(0,36));
    }*/
/* Deze werkt manueel
    @org.junit.Test
    public void testJuisteRegister() throws InterruptedException {

        driver.get("http://localhost:8080/register.xhtml");

        WebElement nameField = driver.findElement(By.id("j_idt6:userName"));
        nameField.sendKeys(tUser);

        WebElement emField = driver.findElement(By.id("j_idt6:email"));
        emField.sendKeys(tEmail);

        WebElement pwField = driver.findElement(By.id("j_idt6:password"));
        pwField.sendKeys(tPass);

        WebElement pwcField = driver.findElement(By.id("j_idt6:passwordConfirm"));
        pwcField.sendKeys(tPass);

        pwField.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("This register should succeed.", "http://localhost:8080/emailSend.xhtml", driver.getCurrentUrl().substring(0, 37));
    }
*/
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
/* werkt nog niet, manueel wel
    @org.junit.Test
    public void testJuisteLogin() throws InterruptedException {

        driver.get("http://localhost:8080/login.xhtml");

        WebElement nameLField = driver.findElement(By.id("j_idt6:userName"));
        nameLField.sendKeys("woody");

        WebElement pwLField = driver.findElement(By.id("j_idt6:password"));
        pwLField.sendKeys("wolle");

        pwLField.submit();
        assertEquals("This login should work and go to the next page.", "http://localhost:8080/index.xhtml", driver.getCurrentUrl().substring(0,33));
    }*/
}
