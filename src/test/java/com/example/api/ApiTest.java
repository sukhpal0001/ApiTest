package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    // Temporary Solution: Testing a GET request to JSONPlaceholder API
    @Test
    public void testGetRequest() {
        // Send GET request to JSONPlaceholder API
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

        // Temporary assertion: Verifying the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200);

        // Temporary Solution: Just verifying the status code for now
        // Further checks can be added later, like verifying response body, etc.
    }

    // Plan to reduce tech-debt: 
    // 1. Add assertions to validate the response body.
    // 2. Handle different response scenarios (e.g., non-200 status codes).
    // 3. Implement data-driven testing for multiple endpoints.

    // Improved version of the GET request test
    @Test
    public void testGetRequestImproved() {
        // Send GET request to JSONPlaceholder API
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

        // Verifying status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verifying response body is not null
        Assert.assertNotNull(response.getBody());

        // Verifying specific data in the response body
        Assert.assertTrue(response.getBody().asString().contains("userId"));
    }

    // Additional Test for all posts (another endpoint)
    @Test
    public void testAllPosts() {
        // Send GET request to JSONPlaceholder API for all posts
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");

        // Verifying status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verifying response contains posts
        Assert.assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    // Example POST request test with known issue documentation
    @Test
    public void testPostRequest() {
        // Known issue: This endpoint occasionally fails under load (documented for future fixes)
        Response response = RestAssured.given()
            .contentType("application/json")
            .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
            .post("https://jsonplaceholder.typicode.com/posts");

        // Verifying status code after POST request
        Assert.assertEquals(response.getStatusCode(), 201);
    }

}
