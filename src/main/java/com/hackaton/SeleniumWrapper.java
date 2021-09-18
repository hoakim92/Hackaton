package com.hackaton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeleniumWrapper {
    public static WebDriver getDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", new File("./driver/chromedriver.exe").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
//        options.setBinary("./chrome-win/chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
