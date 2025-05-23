package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class GetRequestTest {

    @Test
    public void testGetObjectById() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        long sure = 3000;

        long objectId = 7;

        Response response = RestAssured
                .given()
                .when()
                .get("/objects/" + objectId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Pro 16"))
                .body("data.year", equalTo(2019))
                .body("data.price", lessThan(2000.00f))
                .time(lessThan(sure))
                .extract()
                .response();

        long duration = response.time();
        assertTrue("API yanıt süresi çok uzun: " + duration + "ms", duration < sure);

        System.out.println("Yanıt JSON:");
        response.prettyPrint();
        System.out.println("Yanıt süresi: " + duration + "ms");
    }
}
