package com.basiq.ui.tests.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }

    public void close() {
        this.driver.close();
        this.driver.quit();
    }
}
