package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerifyAccountPage extends BasePage {
    public VerifyAccountPage(WebDriver driver) {
        super(driver);
    }

    public void continueVerifyAccount() {
        WebElement btnContinue = driver.findElement(By.xpath("//button"));
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
        btnContinue.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement spanApprove = driver.findElement(By.xpath("//span[text()='Approve']"));
        btnContinue = spanApprove.findElement(By.xpath(".."));
        btnContinue.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement bank = driver.findElement(By.xpath("//button[@id='AU00000']"));
        bank.click();

        WebElement modal = driver.findElement(By.xpath("//div[@id='ui-modal']"));
        WebElement spanApproveCRD = modal.findElement(By.xpath(".//span[text()='Approve']"));
        WebElement btnContinueCRD = spanApproveCRD.findElement(By.xpath(".."));
        btnContinueCRD.click();
    }
}
