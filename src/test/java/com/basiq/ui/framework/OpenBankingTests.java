package com.basiq.ui.framework;

import com.basiq.ui.tests.client.TokenClient;
import com.basiq.ui.tests.client.UserClient;
import com.basiq.ui.tests.enums.Institution;
import com.basiq.ui.tests.models.Token;
import com.basiq.ui.tests.models.TokenScope;
import com.basiq.ui.tests.models.User;
import com.basiq.ui.tests.pages.*;
import com.basiq.ui.tests.resources.PageUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenBankingTests extends BaseTest {

    //@Test
    public void verifyOB() {
        /*System.setProperty("webdriver.chrome.driver", "/Users/Dragoje/Documents/Selenium/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);*/

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

        DashboardConsentPage dashboardConsentPage = new DashboardConsentPage(driver);
        String consentLink = dashboardConsentPage.generateLink();
        dashboardConsentPage.openConsent(consentLink);

        //VerifyAccountPage verifyAccountPage = new VerifyAccountPage(driver);
        //verifyAccountPage.continueVerifyAccount("Hooli");
        ConectPage conectPage = new ConectPage(driver);
        conectPage.clickContinue();
        conectPage.setOtp();
        conectPage.clickContinue();

        InstitutionSelectionPage institutionSelectionPage = new InstitutionSelectionPage(driver);
        institutionSelectionPage.selectInstitution(Institution.HOOLI);

        ConsentConnectPage consentConnectPage = new ConsentConnectPage(driver);
        consentConnectPage.loginToBank("jared", "django");

        //conectPage.loginToBank("jared", "django");
        //Assert.assertEquals(conectPage.isConnected(), true, "Not connected");
        Assert.assertEquals(consentConnectPage.isConnected(), true, "Not connected");

        dashboardConsentPage.deleteUser("drBasiq@gmai.com");

        loginPage.close();

    }

    //@Test
    public void verifyWebConnectionWithOTP() {
        /*System.setProperty("webdriver.chrome.driver", "/Users/Dragoje/Documents/Selenium/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);*/

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open("https://dashboard.basiq.io/login");
        //mainPage.selectAllUsers();
        loginPage.login("milorad.mijalkovic+automationprod@basiq.io", "ZBasiq123123");

        ContainerSubPage containerSubPage = new ContainerSubPage(driver);
        HomePage homePage = new HomePage(driver, containerSubPage);
        //homePage.selectApplication("Regular App");
        //homePage.selectAllUsers();
        homePage.selectApplication("Test Rail");
        homePage.selectAllUsers();

        UsersPage usersPage = new UsersPage(driver);
        usersPage.createUser("draskoBasiq@gmai.com", "+381 62 472 138");
        //usersPage.selectUser("drBasiq@gmai.com");

        DashboardConsentPage dashboardConsentPage = new DashboardConsentPage(driver);
        String consentLink = dashboardConsentPage.generateLink();
        dashboardConsentPage.openConsent(consentLink);

        ConectPage conectPage = new ConectPage(driver);
        conectPage.clickContinue();
        conectPage.setOtp();
        conectPage.clickContinue();

        ConsentPage consentPage = new ConsentPage(driver);
        consentPage.approve();

        InstitutionSelectionPage institutionSelectionPage = new InstitutionSelectionPage(driver);
        institutionSelectionPage.selectInstitution(Institution.HOOLI);

        ConsentConnectPage consentConnectPage = new ConsentConnectPage(driver);
        consentConnectPage.loginToBank("jared", "django");

        //conectPage.loginToBank("jared", "django");
        //Assert.assertEquals(conectPage.isConnected(), true, "Not connected");
        Assert.assertEquals(consentConnectPage.isConnected(), true, "Not connected");

        dashboardConsentPage.deleteUser("drBasiq@gmai.com");

        loginPage.close();

        /*VerifyAccountPage verifyAccountPage = new VerifyAccountPage(driver);
        verifyAccountPage.continueVerifyAccount("Pied Piper");

        ConectPage conectPage = new ConectPage(driver);
        conectPage.loginToBank("jared", "django");
        Assert.assertEquals(conectPage.isConnected(), true, "Not connected");

        dashboardConsentPage.deleteUser("drBasiq@gmai.com");

        loginPage.close();*/

    }

    //@Test
    public void verifyWebConnectionWithOTPByAPI() {
        TokenClient tokenClient = new TokenClient();
        String token = tokenClient.createServerAccessToken(new Token(TokenScope.SERVER_ACCESS.toString()));
        System.out.println(token);

        UserClient userClient = new UserClient();
        Response response = userClient.createUser(token, new User("kim.wexler@mesaverde.com", "", "Kim", "Wexler"));
        System.out.println(response.getBody().asString());

        JsonPath jsonPathEvaluator = response.jsonPath();
        String userId = jsonPathEvaluator.get("id");

        String token2 = tokenClient.createServerAccessToken(new Token(TokenScope.CLIENT_ACCESS.toString(), userId));
        System.out.println(token2);
        String url = "http://consent.basiq.io/home?token=" + token2;

        //System.setProperty("webdriver.chrome.driver", "/Users/Dragoje/Documents/Selenium/chromedriver");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //ChromeDriver driver = new ChromeDriver(options);
        DashboardConsentPage dashboardConsentPage = new DashboardConsentPage(driver, url);
        dashboardConsentPage.open();

        VerifyAccountPage verifyAccountPage = new VerifyAccountPage(driver);
        verifyAccountPage.continueVerifyAccount("Pied Piper");

        ConectPage conectPage = new ConectPage(driver);
        conectPage.loginToBank("jared", "django");
        Assert.assertEquals(conectPage.isConnected(), true, "Not connected");
        conectPage.close();

        userClient.delete(token2, userId);

    }

    //@Test
    public void verifyConnectMultipleInstitutions() {
        TokenClient tokenClient = new TokenClient();
        String token = tokenClient.createServerAccessToken(new Token(TokenScope.SERVER_ACCESS.toString()));
        System.out.println(token);

        UserClient userClient = new UserClient();
        Response response = userClient.createUser(token, new User("kim.wexler@mesaverde.com", "", "Kim", "Wexler"));
        System.out.println(response.getBody().asString());

        JsonPath jsonPathEvaluator = response.jsonPath();
        String userId = jsonPathEvaluator.get("id");

        String tokenClientAcces = tokenClient.createServerAccessToken(new Token(TokenScope.CLIENT_ACCESS.toString(), userId));
        System.out.println(tokenClientAcces);
        //PageUrl.getConsentUrl(tokenClientAcces);
        String url = PageUrl.getConsentUrl(tokenClientAcces);

        ConsentPage consentPage = new ConsentPage(driver, url);
        consentPage.open();
        consentPage.approve();

        InstitutionSelectionPage institutionSelectionPage =  new InstitutionSelectionPage(driver);
        institutionSelectionPage.selectInstitution(Institution.PIED_PIPER);

        ConectPage conectPage = new ConectPage(driver);
        conectPage.loginToBank("jared", "django");
        Assert.assertEquals(conectPage.isConnected(), true, "Not connected");

        String connectUrl = url + "&action=connect";
        institutionSelectionPage =  new InstitutionSelectionPage(driver, connectUrl);
        institutionSelectionPage.open();
        institutionSelectionPage.selectInstitution(Institution.PIED_PIPER);

        conectPage.loginToBank("jared", "django");
        Assert.assertEquals(conectPage.isConnected(), true, "Not connected");


        userClient.delete(tokenClientAcces, userId);

    }

}
