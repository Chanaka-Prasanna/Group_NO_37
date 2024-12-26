package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class putSteps{

    private final sharedState sharedState;

    public putSteps(sharedState sharedState) {
        this.sharedState = sharedState;
    }
    @When("I send a PUT request to {string} with body:")
    public void iSendAPUTRequestToWithBody(String endpoint, String requestBody) {
        sharedState.response = RestAssured.given()
                                .auth().basic(sharedState.username, sharedState.password)
                                .header("Content-Type", "application/json")
                                .body(requestBody)
                                .put(endpoint);
    }

}
