package stepDefinitions;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class postSteps {

        private final sharedState sharedState;

        public postSteps(sharedState sharedState){
            this.sharedState = sharedState;
        }

        @When("I send a POST request to {string} with body:")
        public void iSendAPostRequestToWithBody(String endpoint, String requestBody){
            sharedState.response = RestAssured.given()
                    .auth().basic(sharedState.username, sharedState.password)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .post(endpoint);
        }
}
