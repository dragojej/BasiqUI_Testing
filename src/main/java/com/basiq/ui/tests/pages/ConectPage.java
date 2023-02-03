package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConectPage extends BasePage {

    public ConectPage(WebDriver driver) {
        super(driver);
    }

    public void loginToBank(String userName, String password) {
        List<WebElement> loginFields = driver.findElements(By.xpath("//input"));

        loginFields.get(0).sendKeys(userName);
        loginFields.get(1).sendKeys(password);

        List<WebElement> buttons = driver.findElements(By.xpath("//button"));
        buttons.get(1).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isConnected() {
        WebElement type = driver.findElement(By.xpath("//div[@type='success']"));
        WebElement parent = type.findElement(By.xpath(".."));

        if(parent.findElements(By.xpath(".//span")).get(1).getText().contains("Successfully connected to")) {
            return true;
        }
        else {
            return false;
        }
    }
}
