package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ConsentConnectPage extends BasePage {
    public ConsentConnectPage(WebDriver driver) {
        super(driver);
    }

    public void loginToBank(String userName, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        List<WebElement> loginFields = driver.findElements(By.xpath("//input"));

        loginFields.get(0).sendKeys(userName);
        loginFields.get(1).sendKeys(password);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> buttons = driver.findElements(By.xpath("//button"));
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(1)));
        buttons.get(1).click();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("sc-fKVqWL"), "A SMS code has been sent to your device, please enter valid number."));

        /*loginFields = driver.findElements(By.xpath("//input"));
        wait.until(ExpectedConditions.elementToBeClickable(loginFields.get(0)));
        loginFields.get(0).sendKeys("1234");
        System.out.println("'");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button")).get(0)));
        driver.findElements(By.xpath("//button")).get(0).click();*/
    }

    public boolean isConnected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30000));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@type='success']"), 0));
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
