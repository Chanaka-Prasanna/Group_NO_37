package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;

public class commonSteps {
    private final sharedState sharedState;

    public commonSteps(sharedState sharedState) {
        this.sharedState = sharedState;
    }


    @Given("I am an Admin with Basic Authentication")
    public void iAmAnAdminWithBasicAuthentication() {
        RestAssured.baseURI = "http://localhost:7081";
        sharedState.username = "admin";
        sharedState.password = "password";
    }

    @Given("I am an User with Basic Authentication")
    public void iAmAnUserWithBasicAuthentication() {
        RestAssured.baseURI = "http://localhost:7081";
        sharedState.username = "user";
        sharedState.password = "password";
    }
    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int expectedStatusCode) {
        assertEquals(sharedState.response.getStatusCode(), expectedStatusCode);
    }
}
