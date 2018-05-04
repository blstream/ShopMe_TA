package tests.helpers;

import com.google.gson.Gson;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import tests.objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class RestAssuredMethods {

    private String baseURI;

    public RestAssuredMethods(String baseURI) {
        this.baseURI = baseURI;
    }

    public Category[] getCategoryFromBE() {
        Response response = RestAssured.given().get(baseURI + "/categories");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Category[] categoryFromBE = gson.fromJson(body.asString(), Category[].class);
        return categoryFromBE;
    }

    public Category getCategoryByName(String name) {
        List<Category> categories = Arrays.asList(getCategoryFromBE());
        Category category = categories.stream().filter(item -> item.name.equals(name)).collect(Collectors.toList()).get(0);
        return category;
    }

    public void addService(MyService service) {
        MyService content = new MyService();
        User user2 = new User();

        content.title = service.getTitle();
        content.category = getCategoryByName(service.category.getName());
        content.baseDescription = service.getBaseDesription();
        content.basePrice = service.getBasePrice();
        String nameV = service.user.voivodeship.getName();
        user2.voivodeship = new Voivodeship(null, nameV);
        content.user = new User(service.user.getName(), service.user.getEmail(), service.user.getPhoneNumber(), service.user.getAdditionalInfo(), service.user.voivodeship, service.user.getCity());
        content.extendedDescription = service.getExtendedDesription();
        content.extendedPrice = service.getExtendedPrice();
        content.extraDescription = service.getExtraDesription();
        content.extraPrice = service.getExtraPrice();
        Gson gson = new Gson();
        String result = gson.toJson(content);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/offers").then().assertThat().statusCode(200);
    }

    public void addServices(DataTable services) {
        DataTable dt = services;
        MyService service = new MyService();
        User user1 = new User();
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {
            DataTableRow someRow = dt.getGherkinRows().get(i);

            service.title = someRow.getCells().get(0);
            service.category = new Category(null, someRow.getCells().get(1));
            user1.voivodeship = new Voivodeship("1511273a-bb97-4e8a-924b-e6ff7583f135", someRow.getCells().get(12));
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

    public void deleteService(String id) {
        MyService serviceToDelete = getService(id);

        if (serviceToDelete != null) {
            RestAssured.baseURI = this.baseURI;
            RestAssured.given().contentType("application/json").when().delete("/offers/" + id).then().assertThat().statusCode(200);
        }
    }

    public MyService getService(String id) {
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

    public Services searchForServicesWithPage(int pageNumber, int pageSize, String title) {
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
            if (allElements.get(i).title.toLowerCase().contains(title))
                allElementsTitle.add(allElements.get(i));
        }
        return allElementsTitle;
    }

    public void deleteAllServices() {
        List<MyService> deleteList = getAllServices();
        for (int i = 0; i < deleteList.size(); i++) {
            deleteService(deleteList.get(i).id);
        }
    }

    public void addALotOfServicesServices(DataTable services, int NumberOfServices) {
        DataTable dt = services;
        MyService service = new MyService();
        User user1 = new User();
        for (int i = 0; i < NumberOfServices; i++) {
            DataTableRow someRow = dt.getGherkinRows().get(0);

            service.title = someRow.getCells().get(0) + String.valueOf(i);
            service.category = new Category(null, someRow.getCells().get(1));
            user1.voivodeship = new Voivodeship("1511273a-bb97-4e8a-924b-e6ff7583f135", someRow.getCells().get(12));
            service.user = new User(someRow.getCells().get(2), someRow.getCells().get(3), someRow.getCells().get(4), someRow.getCells().get(5), user1.voivodeship, someRow.getCells().get(13));
            service.baseDescription = someRow.getCells().get(6);
            service.basePrice = Float.valueOf(someRow.getCells().get(7)) + i;
            service.extendedDescription = someRow.getCells().get(8);
            service.extendedPrice = Float.valueOf(someRow.getCells().get(9));
            service.extraDescription = someRow.getCells().get(10);
            service.extraPrice = Float.valueOf(someRow.getCells().get(11));

            addService(service);
        }
    }
}
