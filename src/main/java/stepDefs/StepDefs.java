package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StepDefs {
    WebDriver driver;

    @Before
    public void before() {
        if (driver == null) {
            try {
                System.setProperty("webdriver.chrome.driver", new File("./driver/chromedriver.exe").getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    @After
    public void After() {
        driver.close();
    }

    @Given("^Open Store$")
    public void open_Store() throws Throwable {
        driver.get("https://apparel-uk.local:9002/ucstorefront/en");
    }

    @Given("^Click on '(.*)'$")
    public void click_on(String xpath){
        driver.findElement(By.xpath(xpath)).click();
        System.out.println(driver.getTitle());
    }

    @Given("^Validate title '(.*)'$")
    public void validate_title(String title){
        assertEquals(title, driver.getTitle());
    }
}
