package stepDefinitions;

import utility.Config;

public class SharedState {
    public String username;
    public String password;

    public String baseURI = Config.getEnvValue("BASE_URI");
    public io.restassured.response.Response response;
}
