package com.hackaton.stepDefs;

import com.hackaton.SeleniumWrapper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class StepDefs {
    WebDriver driver;
    Properties config;

    @Before
    public void before() {
        if (config == null) {
            String appConfigPath = Thread.currentThread().getContextClassLoader().getResource("application.properties").getPath();
            try {
                config = new Properties();
                config.load(new FileInputStream(appConfigPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (driver == null) {
            driver = SeleniumWrapper.getDriver(config);
        }
    }

    @After
    public void After() {
        driver.close();
    }

    @Given("^Open Store$")
    public void open_Store() throws Throwable {
        driver.get(config.getProperty("baseUrl"));
    }

    @Given("^Click on '(.*)'$")
    public void click_on(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    @Given("^Click on text '(.*)'$")
    public void click_on_text(String text) {
        driver.findElement(By.partialLinkText(text)).click();
    }

    @Given("^Validate title '(.*)'$")
    public void validate_title(String title) {
        assertEquals(title, driver.getTitle());
    }

    @Given("^Type to '(.*)' text '(.*)'$")
    public void validate_title(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    @Given("^Set select '(.*)' text '(.*)'$")
    public void set_select_value(String xpath, String text) {
        new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(text);
    }
}
