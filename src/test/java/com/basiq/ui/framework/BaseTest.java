package com.basiq.ui.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {

    public ChromeDriver driver;

    @BeforeSuite
    public void createSuite() {
        System.setProperty("webdriver.chrome.driver", "/Users/Dragoje/Documents/Selenium/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterSuite
    public void closeSuite() {
        driver.close();
        driver.quit();
    }
}
