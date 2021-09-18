
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

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
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }


    @Given("^Open Store$")
    public void open_Store() throws Throwable {
        driver.get("https://apparel-uk.local:9002/ucstorefront/en");
    }
}
