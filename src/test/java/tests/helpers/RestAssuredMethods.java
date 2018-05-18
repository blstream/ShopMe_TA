package tests.helpers;

import com.google.gson.Gson;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import tests.objects.*;
import java.util.ArrayList;
import java.util.List;

public class RestAssuredMethods {

    private String baseURI;

    public RestAssuredMethods(String baseURI) {
        this.baseURI = baseURI;
    }


    public void addService(MyService service) {
        MyService content = new MyService();

        content.title = service.getTitle();
        content.category = new Category(service.category.getName());
        content.baseDescription = service.getBaseDesription();
        content.basePrice = service.getBasePrice();
        content.user = new User(service.user.getName(), service.user.getEmail(), service.user.getPhoneNumber(), service.user.getAdditionalInfo(), service.user.voivodeship, service.user.getCity());
        content.extendedDescription = service.getExtendedDesription();
        content.extendedPrice = service.getExtendedPrice();
        content.extraDescription = service.getExtraDesription();
        content.extraPrice = service.getExtraPrice();
        Gson gson = new Gson();
        String result = gson.toJson(content);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given()
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYTA3Y2Y4OC0zYmM0LTRmOTItOWU3NS0zNTY4ZjhiNWM3OGEiLCJlbWFpbCI6InRAdC50Iiwic2NvcGVzIjpbIlVTRVIiXSwiaWF0IjoxNTI2NTg4NTkzLCJleHAiOjE1MjY1OTU3OTN9.iH7NjRoN75clpGvpl8WpGhBCKPM7ER2RSTPYI1OALL9ss_oYSZCNcS1E4jTUeH_ETCeX0_lJn1ZuuoFjpEg1iQ")
                .contentType("application/json")
                .body(result)
                .when()
                .post("/offers")
                .then().assertThat().statusCode(200);
    }

    public void addServices(DataTable services) {
        DataTable dt = services;
        MyService service = new MyService();
        User user1 = new User();
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {
            DataTableRow someRow = dt.getGherkinRows().get(i);

            service.title = someRow.getCells().get(0);
            service.category = new Category(someRow.getCells().get(1));
            user1.voivodeship = new Voivodeship(someRow.getCells().get(12));
            service.user = new User(someRow.getCells().get(2), someRow.getCells().get(3), someRow.getCells().get(4), someRow.getCells().get(5), user1.voivodeship, someRow.getCells().get(13));
            service.baseDescription = someRow.getCells().get(6);
            service.basePrice = Float.valueOf(someRow.getCells().get(7));
            service.extendedDescription = someRow.getCells().get(8);
            service.extendedPrice = Float.valueOf(someRow.getCells().get(9));
            service.extraDescription = someRow.getCells().get(10);
            service.extraPrice = Float.valueOf(someRow.getCells().get(11));

            addService(service);
        }
    }

    public void deleteServiceById(String id) {
        MyService serviceToDelete = getServiceById(id);

        if (serviceToDelete != null) {
            RestAssured.baseURI = this.baseURI;
            RestAssured.given().contentType("application/json").when().delete("/offers/" + id).then().assertThat().statusCode(200);
        }
    }

    public MyService getServiceById(String id) {
        Response response = RestAssured.given().pathParam("id", id).get(baseURI + "/offers/{id}");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        MyService service = gson.fromJson(body.asString(), MyService.class);
        return service;
    }

    public Services getServices(int pageNumber, int pageSize) {
        Response response = RestAssured.given()
                .queryParam("page", pageNumber)
                .queryParam("pageSize", pageSize)
                .get(baseURI + "/offers");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Services servicesOnPage = gson.fromJson(body.asString(), Services.class);
        return servicesOnPage;
    }

    public Services searchForServicesOnPage(int pageNumber, int pageSize, String title) {
        Response response = RestAssured.given()
                .queryParam("page", pageNumber)
                .queryParam("pageSize", pageSize)
                .queryParam("title", title)
                .get(baseURI + "/offers");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Services servicesOnPage = gson.fromJson(body.asString(), Services.class);
        return servicesOnPage;
    }

    public List<MyService> getAllServices() {
        List<MyService> offersList = new ArrayList<>();
        for (int i = 1; ; i++) {
            Services services = getServices(i, 100);
            if (services.content == null)
                break;
            offersList.addAll(services.content);
        }
        return offersList;
    }

    public List<MyService> searchForServices(String title) {
        List<MyService> allElements = getAllServices();
        List<MyService> allElementsTitle = new ArrayList<>();
        for (int i = 0; i < allElements.size(); i++) {
            if (allElements.get(i).title.equals(title))
                allElementsTitle.add(allElements.get(i));
        }
        return allElementsTitle;
    }

    public void deleteAllServices() {
        List<MyService> deleteList = getAllServices();
        for (int i = 0; i < deleteList.size(); i++) {
            deleteServiceById(deleteList.get(i).id);
        }
    }

    public void deleteService(MyService myService) {
        deleteServiceById(myService.id);
    }

    public void addUserWithEmail(String email) {
        Address address = new Address("5d214c01-95c3-4ec4-8f68-51dfb80b191c", "Niepodległości", "12/1", "Szczecin", "70-125");
        User user = new User();

        user.id = "5d214c01-95c3-4ec4-8f68-51dfb80b191c";
        user.name = "Johnnie";
        user.surname = "Walkier";
        user.email = email;
        user.password = "a123456";
        user.phoneNumber = "0234567890";
        user.bankAccount = "01234567890123456789012345";
        user.address = address;
        user.voivodeship = new Voivodeship("WesternPomeranian");
        user.invoiceRequest = true;
        user.invoice = new Invoice("5d214c01-95c3-4ec4-8f68-51dfb80b191c", "Fight Club Sp.z.o.o.", "123-456-78-90", address);
        Gson gson = new Gson();
        String result = gson.toJson(user);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/users").then().assertThat().statusCode(200);
    }
}
