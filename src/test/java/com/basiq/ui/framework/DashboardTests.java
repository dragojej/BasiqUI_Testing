package com.basiq.ui.framework;

import com.basiq.ui.tests.client.TokenClient;
import com.basiq.ui.tests.client.UserClient;
import com.basiq.ui.tests.enums.Institution;
import com.basiq.ui.tests.models.Token;
import com.basiq.ui.tests.models.TokenScope;
import com.basiq.ui.tests.models.User;
import com.basiq.ui.tests.pages.ConectPage;
import com.basiq.ui.tests.pages.ConsentPage;
import com.basiq.ui.tests.pages.InstitutionSelectionPage;
import com.basiq.ui.tests.resources.PageUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTests extends BaseTest {

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
