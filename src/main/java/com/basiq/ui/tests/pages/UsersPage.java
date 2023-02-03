package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UsersPage extends BasePage {
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public void createUser(String userName, String phoneNumber) {
        //WebElement btnCreateUser = driver.findElement(By.xpath("//button[@id='new-user-btn-first']"));
        WebElement span = driver.findElement(By.xpath("//span[text()='Create user']"));
        WebElement btnCreateUser = span.findElement(By.xpath(".."));

        //WebElement btnCreateUser = driver.findElement(By.xpath("//button[@id='new-user-btn-secondary']"));
        btnCreateUser.click();

        WebElement inpUserName = driver.findElement(By.xpath("//input[@id='input-email']"));
        inpUserName.sendKeys(userName);

        WebElement inpPhoneNumber = driver.findElement(By.xpath("//input[@id='input-phoneNumber']"));
        inpPhoneNumber.sendKeys(phoneNumber);

        WebElement btnCreateUserFinal = driver.findElement(By.xpath("//button[@id='create-user-btn']"));
        btnCreateUserFinal.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectUser(String email) {
        WebElement table = driver.findElement(By.xpath("//table"));

        WebElement tbody = table.findElement(By.xpath(".//tbody"));

        List<WebElement> tRows = tbody.findElements(By.xpath(".//tr"));

        for(int i = 0; i < tRows.size(); i++) {
           WebElement tblEmail = tRows.get(i).findElements(By.xpath(".//td")).get(1);

           if(tblEmail.findElement(By.xpath(".//span")).getText().equals(email)) {
               tblEmail.click();
               break;
           }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
