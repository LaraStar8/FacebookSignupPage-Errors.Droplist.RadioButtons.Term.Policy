import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignupAdditionalElementsTest {
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

    @ParameterizedTest
    @ValueSource(strings = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"})
    public void monthsTest(String month) {
        WebElement monthDropList = driver.findElement(By.xpath("//*[@name='birthday_month']"));
        monthDropList.click();
        Select selectedMonth = new Select(monthDropList);
        selectedMonth.selectByVisibleText(month);
        String monthValue = selectedMonth.getFirstSelectedOption().getText();
        assertEquals(month, monthValue);
    }
    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearsTest(String year) {
        WebElement yearsDropList = driver.findElement(By.xpath("//*[@name='birthday_year']"));
        yearsDropList.click();
        Select selectedYear = new Select(yearsDropList);
        selectedYear.selectByVisibleText(year);
        String yearValue = selectedYear.getFirstSelectedOption().getText();
        assertEquals(year, yearValue);
    }

    @Test
    public void gendersTest() {
        String femaleXpath = "//*[text() = 'Female']//following-sibling::*[@type='radio']";
        String maleXpath = "//*[text() = 'Male']//following-sibling::*[@type='radio']";
        String customXpath = "//*[text() = 'Custom']//following-sibling::*[@type='radio']";

        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleButtonChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleButtonChecked);
        assertEquals("true", isFemaleButtonChecked);

        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleButtonChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleButtonChecked);
        assertEquals("true", isMaleButtonChecked);

        WebElement customButton = driver.findElement(By.xpath(customXpath));
        customButton.click();
        String isCustomButtonChecked = driver.findElement(By.xpath(customXpath)).getAttribute("checked");
        assertNotNull(isCustomButtonChecked);
        assertEquals("true", isCustomButtonChecked);
    }

    @Test
    public void termsTest() {
        WebElement terms = driver.findElement(By.xpath("//*[@id='terms-link']"));
        assertNotNull(terms);
        terms.click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String expectedUrl = "https://www.facebook.com/legal/terms/update";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void privacyTest() {
        WebElement privacy = driver.findElement(By.xpath("//*[@id='privacy-link']"));
        assertNotNull(privacy);
        privacy.click();
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String expectedUrl = "https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl,actualUrl);
    }
}



