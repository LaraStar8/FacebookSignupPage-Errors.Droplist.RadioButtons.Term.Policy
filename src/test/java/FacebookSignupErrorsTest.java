import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacebookSignupErrorsTest {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/signup";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void errorFirstNameTest() {
        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains (text(), 'your name')]"));
        assertNotNull(error);
    }

    @Test
    public void errorLastNameTest() {
        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='lastname']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains (text(), 'your name')]"));
        assertNotNull(error);
    }

    @Test
    public void errorMobileEmailTest() {
        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains (text(), 'need to reset')]"));
        assertNotNull(error);
    }
    @Test
    public void errorVerifyMobileEmailTest() {
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("mkj@gmail.com");
        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains (text(), 'emails do not match')]"));
        assertNotNull(error);
    }

    @Test
    public void errorPasswordTest() {
        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@data-type='password']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(),'a combination of at least')]"));
        assertNotNull(error);
    }
    @Test
    public void errorWrongPasswordTest() {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@data-type='password']")).sendKeys("123");
        driver.findElement(By.xpath("//*[@name='sex'][@value=2]")).click();

        driver.findElement(By.xpath("//*[@name='birthday_year']")).click();
        driver.findElement(By.xpath("//*[text()='2000']")).click();

        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();

        WebElement error = driver.findElement(By.xpath("//*//div[@id='reg_error_inner']"));
        assertNotNull(error);
    }
    @Test
    public void errorBirthdayTest() {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@data-type='password']")).sendKeys("123");

        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'your real birthday')]"));
        assertNotNull(error);
    }
    @Test
    public void errorGenderTest() {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@data-type='password']")).sendKeys("123");
        driver.findElement(By.xpath("//*[@name='birthday_year']")).click();
        driver.findElement(By.xpath("//*[text()='2000']")).click();

        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'choose a gender')]"));
        assertNotNull(error);
    }

    @Test
    public void errorGenderPronounTest() {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("mkjl@gmail.com");
        driver.findElement(By.xpath("//input[@data-type='password']")).sendKeys("123");
        driver.findElement(By.xpath("//*[@name='birthday_year']")).click();
        driver.findElement(By.xpath("//*[text()='2000']")).click();
        driver.findElement(By.xpath("//*[@name='sex'][@value=-1]")).click();

        assertNotNull(driver.findElement(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'select your pronoun')]"));
        assertNotNull(error);
    }

}


