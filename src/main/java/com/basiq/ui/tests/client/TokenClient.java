package com.basiq.ui.tests.client;

import com.basiq.ui.tests.models.Token;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class TokenClient {

    public String createToken() {
        Map<String, String> env  = System.getenv();

        for (String envName : env.keySet()) {
            System.out.format("%s = %s%n", envName, env.get(envName));
        }
        //String apiKey = System.getenv("API_KEY_REST_ASSURED");
        String apiKey = System.getenv("API_KEY");
        RequestSpecification httpRequest = RestAssured.given();
        Response res = httpRequest.header("Basiq-Version", "3.0")
                .header(
                        "Authorization",
                        "Basic " + apiKey)
                .post("https://au-api.basiq.io/token");
        ResponseBody body = res.body();
        JsonPath jsonPathEvaluator = res.jsonPath();
        String token = jsonPathEvaluator.get("access_token");
        return token;
    }

    public String createServerAccessToken(Token token) {
        Map<String, String> env  = System.getenv();

        for (String envName : env.keySet()) {
            System.out.format("%s = %s%n", envName, env.get(envName));
        }
        //String apiKey = System.getenv("API_KEY_REST_ASSURED");
        String apiKey = System.getenv("API_KEY");
        RequestSpecification httpRequest = RestAssured.given();
        Response res = httpRequest.header("Basiq-Version", "3.0")
                .header(
                        "Authorization",
                        "Basic " + apiKey).body(token)
                .post("https://au-api.basiq.io/token");
        ResponseBody body = res.body();
        JsonPath jsonPathEvaluator = res.jsonPath();
        String resToken = jsonPathEvaluator.get("access_token");
        return resToken;
    }

}
