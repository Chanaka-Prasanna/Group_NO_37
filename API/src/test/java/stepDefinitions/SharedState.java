package stepDefinitions;

public class SharedState {
    public String username;
    public String password;

    public String baseURI = "http://localhost:7081";
    public io.restassured.response.Response response;
}
