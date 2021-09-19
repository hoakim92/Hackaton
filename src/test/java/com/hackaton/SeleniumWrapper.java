package com.hackaton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SeleniumWrapper {
    public static WebDriver getDriver(Properties config) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        } else if (os.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "driver/linux-chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        for (String s : Arrays.asList(config.getProperty("selenium.arguments").split("--"))){
            if (!s.isEmpty()) {
                options.addArguments("--" + s);
            }
        }
        WebDriver driver = new ChromeDriver(options);
        long implicitlyWait = Long.parseLong(config.getProperty("selenium.waitSeconds"));
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
