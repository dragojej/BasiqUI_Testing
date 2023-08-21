package com.basiq.ui.tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class DashboardConsentPage extends BasePage {

    private String url;

    public DashboardConsentPage(WebDriver driver) {
        super(driver);
    }

    public DashboardConsentPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public void open() {
        this.driver.get(this.url);
        this.driver.manage().window().maximize();
    }

    private WebElement btnCreateConnection() {
        WebElement toReturn = null;
        try {
            toReturn = driver.findElement(By.xpath("//button[@id='create-connection-button']"));
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getStackTrace());
        }
        return toReturn;
    }

    public String generateLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30000));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//button[@id='create-connection-button']"), 0));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='create-connection-button']"))).click();
        WebElement webElementBtnCreateConnection = btnCreateConnection();
        webElementBtnCreateConnection.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement link = driver.findElement(By.xpath("//input[@id='input-consentLinkInput']"));
        return  link.getAttribute("value");

    }

    public void openConsent(String consentLink) {
        //driver.get("http://yahoo.com");
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(consentLink);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String email) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        WebElement edit = driver.findElement(By.xpath("//span[text()='Edit user']"));
        WebElement parent = edit.findElement(By.xpath(".."));
        edit.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement btnDelete = driver.findElement(By.xpath("//button[@id='remove-user-btn']"));
        btnDelete.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement deleteModal = driver.findElement(By.xpath("//div[@class='modal-dialog']"));
        //WebElement btnDeleteModal = deleteModal.findElements(By.xpath(".//button")).get(1);
        WebElement btnDeleteModal = deleteModal.findElement(By.xpath(".//button[@id='remove-user-modal-submit-btn']"));
        btnDeleteModal.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
