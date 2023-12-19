package com.basiq.ui.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Selenium {

    @Test
    public void openChrome() {
        WebDriverManager.chromedriver().setup();
        //String driverPath = System.getProperty("user.dir") + "/src/main/java/com/basiq/ui/tests/drivers/chromedriver";
        //System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");

        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://www.guru99.com/");
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.guru99.com/");
        driver.quit();
    }

}
