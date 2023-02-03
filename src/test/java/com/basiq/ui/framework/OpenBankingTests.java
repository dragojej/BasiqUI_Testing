package com.basiq.ui.framework;

import com.basiq.ui.tests.pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenBankingTests {

    @Test
    public void verifyOB() {
        System.setProperty("webdriver.chrome.driver", "/Users/Dragoje/Documents/Selenium/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open("https://dashboard.basiq.io/login");
        //mainPage.selectAllUsers();
        loginPage.login("milorad.mijalkovic+automationprod@basiq.io", "ZBasiq123123");

        ContainerSubPage containerSubPage = new ContainerSubPage(driver);
        HomePage homePage = new HomePage(driver, containerSubPage);
        //homePage.selectApplication("Regular App");
        //homePage.selectAllUsers();
        homePage.selectApplication("Regular App");
        homePage.selectAllUsers();

        UsersPage usersPage = new UsersPage(driver);
        usersPage.createUser("draskoBasiq@gmai.com", "+381 62 472 138");
        //usersPage.selectUser("drBasiq@gmai.com");

        ConsentPage consentPage = new ConsentPage(driver);
        String consentLink = consentPage.generateLink();
        consentPage.openConsent(consentLink);

        VerifyAccountPage verifyAccountPage = new VerifyAccountPage(driver);
        verifyAccountPage.continueVerifyAccount();

        ConectPage conectPage = new ConectPage(driver);
        conectPage.loginToBank("jared", "django");
        Assert.assertEquals(conectPage.isConnected(), true, "Not connected");

        consentPage.deleteUser("drBasiq@gmai.com");

        loginPage.close();

    }

}
