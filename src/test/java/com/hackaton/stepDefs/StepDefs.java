package com.hackaton.stepDefs;

import com.hackaton.SeleniumWrapper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


import static org.junit.Assert.assertEquals;

public class StepDefs {
    WebDriver driver;

    @Before
    public void before() {
        driver = SeleniumWrapper.getDriver();
    }

    @After
    public void After() {
//        driver.close();
    }

    @Given("^Open Store$")
    public void open_Store() throws Throwable {
        waitForStep();
        driver.get("https://apparel-uk.local:9002/ucstorefront/en");
    }

    @Given("^Click on '(.*)'$")
    public void click_on(String xpath) {
        waitForStep();
        driver.findElement(By.xpath(xpath)).click();
    }

    @Given("^Click on text '(.*)'$")
    public void click_on_text(String text) throws InterruptedException {
        waitForStep();
        driver.findElement(By.partialLinkText(text)).click();
    }

    @Given("^Validate title '(.*)'$")
    public void validate_title(String title) {
        waitForStep();
        assertEquals(title, driver.getTitle());
    }

    @Given("^Type to '(.*)' text '(.*)'$")
    public void validate_title(String xpath, String text) {
        waitForStep();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    @Given("^Set select '(.*)' text '(.*)'$")
    public void set_select_value(String xpath, String text) {
        waitForStep();
        new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(text);
    }

    static public void waitForStep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
