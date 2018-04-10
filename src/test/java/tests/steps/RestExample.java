package tests.steps;

import com.google.gson.Gson;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import tests.objects.MyServices;
import tests.pages.SearchResultsPage;

import java.util.HashMap;
import java.util.List;

public class RestExample {

    private RequestSpecification spec = RestAssured.with();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    @Given("^that there are some services$")
    public void thatThereAreNoServicesAdded() {
        RestAssured.baseURI = "https://patronage2018.intive-projects.com/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/offers");
        ResponseBody body = response.getBody();
        HashMap offersResponse = new JsonPath(body.asString()).get(".");
        List<HashMap> offers = (List<HashMap>) offersResponse.get("content");
        Integer total = Integer.parseInt(offersResponse.get("totalElements").toString());
        System.out.println(body);

        if (total <= 0) {
            return;
        }

        for (; ; ) {

            for (int i = 0; i < offers.size(); i++) {
                String offer = offers.get(i).get("id").toString();
                RestAssured.given().when().delete("/offers/" + offer);
            }
            response = httpRequest.get("/offers");
            body = response.getBody();
            offersResponse = new JsonPath(body.asString()).get(".");
            offers = (List<HashMap>) offersResponse.get("content");
            total = Integer.parseInt(offersResponse.get("totalElements").toString());

            if (total <= 0) {
                break;
            }
        }
    }

    @Given("^Json config is provided")
    public void Json_config_is_provided() {
        spec.given().header("Version", 1);
    }

    @When("^I call GET on Jsonplaceholder/posts/1")
    public void I_call_GET_on_Jsonplaceholder_post_1() {
        spec.when().get("http://jsonplaceholder.typicode.com/posts/1");
    }

    @Then("^I get Status code (\\d+)")
    public void I_get_Status_code(int statusCode) {
        spec.then().expect().statusCode(statusCode);
    }

    @When("^I request for json with weather$")


    public void iRequestForJsonWithWeather() throws Throwable {

//        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        Response response = RestAssured.given().get("https://patronage2018.intive-projects.com/api/offers/fbcd5197-b82b-4bf9-ab00-f48f2dd072fc");
      //  RestAssured.given().get("http://restapi.demoqa.com/utilities/weather/city/Szczecin").then().assertThat().statusCode(200);

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();


        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());

        Gson gson = new Gson();

        MyServices services  = gson.fromJson(body.asString(), MyServices.class);
        System.out.println("OBJECT is: " + services.toString());
//        Staff staff = gson.fromJson(body.asString(), Staff.class);
//        System.out.println("OBJECT is: " + staff.toString());
    }

    @Then("^I can see service with data$")
    public void iCanSeeServiceWithData(DataTable services) throws Throwable {

        Response response = RestAssured.given().get("https://patronage2018.intive-projects.com/api/offers/fbcd5197-b82b-4bf9-ab00-f48f2dd072fc");
        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        Gson gson = new Gson();

        MyServices servicesFromBE  = gson.fromJson(body.asString(), MyServices.class);
        System.out.println("OBJECT is: " + servicesFromBE.toString());

        MyServices serviceFromFE = searchResultsPage.getService(0);
        System.out.println("OBJECT is: " + serviceFromFE.toString());

        serviceFromFE.equalsOnList(servicesFromBE);


        DataTable dt = services;
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {

            DataTableRow someRow = dt.getGherkinRows().get(i);
            String name = someRow.getCells().get(0);
            String koszt = someRow.getCells().get(1);
            String data = someRow.getCells().get(2);



        }
    }
}
