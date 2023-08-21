package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ConsentPage extends BasePage {

    private String url;

    public ConsentPage(WebDriver driver) {
        super(driver);
    }

    public ConsentPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public void open() {
        this.driver.get(this.url);
        this.driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='ui-modal']"), 0));
    }

    private WebElement getBtnCancel() {
        return null;
    }

    private WebElement getBtnApprove() {
        try {
            WebElement spanApprove = driver.findElement(By.xpath("//span[text()='Approve']"));
            return spanApprove.findElement(By.xpath(".."));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            throw (new NoSuchElementException("Element not found"));
        }
    }

    public void approve() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement btnApprove = this.getBtnApprove();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        //wait.until(ExpectedConditions.elementToBeClickable(btnApprove));
        btnApprove.click();
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


        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("sc-fKVqWL"), "A SMS code has been sent to your device, please enter valid number."));

        loginFields = driver.findElements(By.xpath("//input"));
        wait.until(ExpectedConditions.elementToBeClickable(loginFields.get(0)));
        loginFields.get(0).sendKeys("1234");
        System.out.println("'");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//button")).get(0)));
        driver.findElements(By.xpath("//button")).get(0).click();
    }

}
