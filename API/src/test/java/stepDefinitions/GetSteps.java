package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class GetSteps {
    private final SharedState sharedState;

    public GetSteps(SharedState sharedState) {
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
