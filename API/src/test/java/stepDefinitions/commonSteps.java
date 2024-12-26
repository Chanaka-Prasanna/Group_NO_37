package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import static org.testng.Assert.assertEquals;

public class commonSteps {
    private final sharedState sharedState;

    public commonSteps(sharedState sharedState) {
        this.sharedState = sharedState;
    }


    @Given("I logged in to the system with admin credentials")
    public void iLoggedInToTheSystemWithAdminCredentials() {
        RestAssured.baseURI = "http://localhost:7081";
        sharedState.username = "admin";
        sharedState.password = "password";
    }

    @Given("I logged in to the system with user credentials")
    public void iLoggedInToTheSystemWithUserCredentials() {
        RestAssured.baseURI = "http://localhost:7081";
        sharedState.username = "user";
        sharedState.password = "password";
    }
    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int expectedStatusCode) {
        assertEquals(sharedState.response.getStatusCode(), expectedStatusCode);
    }
}
