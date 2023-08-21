package com.basiq.ui.tests.pages;

import com.basiq.ui.tests.enums.Institution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InstitutionSelectionPage extends BasePage {

    private String url;

    public InstitutionSelectionPage(WebDriver driver) {
        super(driver);
    }

    public InstitutionSelectionPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void open() {
        this.driver.get(this.url);
        this.driver.manage().window().maximize();
    }

    public void selectInstitution(Institution institution) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//input"), 1));
        driver.findElement(By.xpath("//input")).sendKeys(institution.getName());

        //WebElement bank = driver.findElement(By.xpath("//button[@id='AU00000']"));
        WebElement bank = driver.findElement(By.xpath("//button[@id='" + institution.getId() + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(bank));
        bank.click();

        WebElement modal = driver.findElement(By.xpath("//div[@id='ui-modal']"));
        WebElement spanApproveCRD = modal.findElement(By.xpath(".//span[text()='Approve']"));
        WebElement btnContinueCRD = spanApproveCRD.findElement(By.xpath(".."));
        btnContinueCRD.click();
    }
}
