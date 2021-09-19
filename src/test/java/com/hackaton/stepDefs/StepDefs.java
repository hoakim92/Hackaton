package com.hackaton.stepDefs;

import com.hackaton.SeleniumWrapper;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Given;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StepDefs {
    WebDriver driver;
    Properties config;

    private int currentStepDefIndex = 0;

    @BeforeStep
    public void logStep(Scenario scenario) throws Exception {
        Field f = scenario.getClass().getDeclaredField("testCase");
        f.setAccessible(true);
        TestCase r = (TestCase) f.get(scenario);
        List<PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());
        PickleStepTestStep currentStepDef = stepDefs.get(currentStepDefIndex);
        System.out.println(currentStepDef.getStepText());
    }

    @AfterStep
    public void increaseStepCounter(Scenario scenario) {
        currentStepDefIndex += 1;
    }

    @Before
    public void before(Scenario scenario) {
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
        driver.findElement(By.xpath(xpath)).sendKeys(replacePlaceholders(text));
    }

    @Given("^Set select '(.*)' text '(.*)'$")
    public void set_select_value(String xpath, String text) {
        new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(text);
    }

    static String replacePlaceholders(String str){
        return str.replace("{{randomLetters}}", RandomStringUtils.randomAlphabetic(4));
    }
}
