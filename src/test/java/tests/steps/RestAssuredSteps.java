package tests.steps;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredSteps {
    @When("^I request for json with weather$")
    public void iRequestForJsonWithWeather() throws Throwable {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Szczecin");

        RestAssured.given().get("http://restapi.demoqa.com/utilities/weather/city/Szczecin").then().assertThat().statusCode(400);


        // Retrieve the body of the Response
        ResponseBody body = response.getBody();


        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());
    }
}
