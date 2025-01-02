package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class DeleteSteps {
    private final SharedState sharedState;

    public DeleteSteps(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        sharedState.response = RestAssured.given()
                .auth().basic(sharedState.username, sharedState.password)
                .when()
                .delete(endpoint);
    }


}
