package com.basiq.ui.tests.client;

import com.basiq.ui.tests.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.params.CoreConnectionPNames;

public class UserClient {

    public Response createUser(String token, User user) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://au-api.basiq.io").
                build();
        requestSpecification.header("Authorization", "Bearer " + token).contentType("application/json").body(user);

        RequestSpecification requestSpecificationTestImpl = RestAssured.given(requestSpecification);

        return requestSpecificationTestImpl.post("/users");
    }

    public Response delete(String token, String userId) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://au-api.basiq.io").
                build();
        requestSpecification.header("Authorization", "Bearer " + token);

        return RestAssured.given(requestSpecification).when().delete("/users/"+ userId);
    }

}
