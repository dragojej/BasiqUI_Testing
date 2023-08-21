package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyAccountPage extends BasePage {

    private String url;
    public VerifyAccountPage(WebDriver driver) {
        super(driver);
    }

    public VerifyAccountPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public void open() {
        this.driver.get(this.url);
        this.driver.manage().window().maximize();
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

    public void continueVerifyAccount(String institution) {
        /*WebElement btnContinue = driver.findElement(By.xpath("//button"));
        btnContinue.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //setovanje koda sa telefona
        WebElement inpPhoneNumber = driver.findElement(By.xpath("//input[@inputmode='numeric']"));
        inpPhoneNumber.sendKeys("1234");

        WebElement span = driver.findElement(By.xpath("//span[text()='Continue']"));
        btnContinue = span.findElement(By.xpath(".."));
        btnContinue.click();*/

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='ui-modal']"), 0));
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //WebElement spanApprove = driver.findElement(By.xpath("//span[text()='Approve']"));
        //WebElement btnContinue = spanApprove.findElement(By.xpath(".."));
        WebElement btnContinue = this.getBtnApprove();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
        btnContinue.click();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//input"), 1));
        driver.findElement(By.xpath("//input")).sendKeys(institution);

        //WebElement bank = driver.findElement(By.xpath("//button[@id='AU00000']"));
        WebElement bank = driver.findElement(By.xpath("//button[@id='AU00002']"));
        wait.until(ExpectedConditions.elementToBeClickable(bank));
        bank.click();

        WebElement modal = driver.findElement(By.xpath("//div[@id='ui-modal']"));
        WebElement spanApproveCRD = modal.findElement(By.xpath(".//span[text()='Approve']"));
        WebElement btnContinueCRD = spanApproveCRD.findElement(By.xpath(".."));
        btnContinueCRD.click();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
