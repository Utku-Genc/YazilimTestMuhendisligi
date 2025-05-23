package com.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class PostRequestTest {

    @Test
    public void testCreateObject() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        long sure = 3000;

        String requestBody = """
        {
          "name": "laptop",
          "data": {
            "brand": "Dell",
            "cpu": "i7",
            "ram": "16GB"
          }
        }
        """;

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("laptop"))
                .body("data.brand", equalTo("Dell"))
                .body("data.cpu", equalTo("i7"))
                .body("data.ram", equalTo("16GB"))
                .time(lessThan(sure))
                .extract()
                .response();

        long cevap = response.time();
        assertTrue("API yanıt süresi çok uzun: " + cevap + "ms", cevap < sure);

        System.out.println("Yanıt JSON:");
        response.prettyPrint();
        System.out.println("Yanıt süresi: " + cevap + "ms");
    }
}
