package com.basiq.ui.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    public ContainerSubPage containerSubPage;

    public HomePage(WebDriver driver, ContainerSubPage containerSubPage) {
        super(driver);
        this.containerSubPage = containerSubPage;
    }

    private WebElement getUsers() {
        return driver.findElement(By.xpath("//a[@href='\"/users/all\"']"));

    }


    public void selectAllUsers() {

        /*WebElement icon = driver.findElement(By.xpath("//span[@class='header-container__icon icon-User']"));
        icon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30000));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='page-container__page-title ']"), 0));
        WebElement span = driver.findElement(By.xpath("//div[@class='page-container__page-title ']"));
        waitForTextToAppear(driver, "Users", span);*/
        containerSubPage.selectAllUsers();

    }

    public void selectApplication(String applicationName) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.containerSubPage.selectApplication(applicationName);
    }

    /*public void selectApplication(String applicationName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30000));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='header-container  ']"), 1));
        //this.driver.findElement(By.className("header-container__application-circle-wrapper header-container__circle-wrapper header-container__circle-wrapper--color-cerulean")).click();
        WebElement container = this.driver.findElement(By.xpath("//nav[@class='header-container__nav ']"));
        wait.until(ExpectedConditions.elementToBeClickable(container));
        WebElement containerItem = container.findElement(By.xpath(".//div")).findElement(By.xpath(".//div"));
        wait.until(ExpectedConditions.elementToBeClickable(containerItem));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        container.findElement(By.xpath(".//div")).findElement(By.xpath(".//div")).click();

        List<WebElement> expandedContainer = driver.findElements(By.xpath("//div[@class='header-container header-container--expanded ']"));
        while(expandedContainer.size() == 0) {
            container.findElement(By.xpath(".//div")).findElement(By.xpath(".//div")).click();
            expandedContainer = driver.findElements(By.xpath("//div[@class='header-container header-container--expanded ']"));
        }

        WebElement application = this.driver.findElement(By.xpath("//li[@id='" + applicationName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(application));
        application.click();
    }*/

}
