package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /*public WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }*/

   /* private WebElement getUsers() {
        try {
            return driver.findElement(By.xpath("//a[@href='\"/users/all\"']"));
        }
        catch (NoSuchElementException e) {
            System.out.println("Cannot find element : " + e.getMessage());
        }
    }
*/

    private WebElement getInpUserName() {
        return driver.findElement(By.xpath("//input[@id='input-email']"));
    }

    private WebElement getInpPassword() {
        return driver.findElement(By.xpath("//input[@id='input-password']"));
    }

    private WebElement getBtnLogin() {
        return driver.findElement(By.xpath("//button[@id='login-btn']"));
    }
    public void login(String userName, String password) {
        this.getInpUserName().sendKeys(userName);
        this.getInpPassword().sendKeys(password);
        this.getBtnLogin().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(20000));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//button[@id='login-btn']"), 0));
    }

}
