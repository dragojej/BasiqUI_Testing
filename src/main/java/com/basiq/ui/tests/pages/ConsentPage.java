package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ConsentPage extends BasePage {

    public ConsentPage(WebDriver driver) {
        super(driver);
    }

    public String generateLink() {
        WebElement btnGenerateLink = driver.findElement(By.xpath("//button[@id='create-connection-button']"));
        btnGenerateLink.click();

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
