package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class getSteps {
    private final sharedState sharedState;

    public getSteps(sharedState sharedState) {
        this.sharedState = sharedState;
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        sharedState.response = RestAssured.given()
                .auth().basic(sharedState.username, sharedState.password)
                .when()
                .get(endpoint);
    }
}
