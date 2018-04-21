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

import static org.junit.Assert.assertTrue;

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

    public Services getServicesFromBE() {
        Response response = RestAssured.given().get(baseURI + "/offers");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Services servicesFromBE = gson.fromJson(body.asString(), Services.class);
        return servicesFromBE;
    }

    public void addService(MyService service) {
        MyService content = new MyService();
        content.title = service.getTitle();
        content.category = getCategoryByName(service.category.getName());
        content.baseDescription = service.getBaseDesription();
        content.basePrice = service.getBasePrice();
        content.user = new User(service.user.getName(), service.user.getEmail(), service.user.getPhoneNumber(), service.user.getAdditionalInfo());
        content.extendedDescription = service.getExtendedDesription();
        content.extendedPrice = service.getExtendedPrice();
        content.extraDescription = service.getExtraDesription();
        content.extraPrice = service.getExtraPrice();
        Gson gson = new Gson();
        String result = gson.toJson(content);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/offers").then().assertThat().statusCode(201);
    }

    public void addServices(DataTable services) {
        DataTable dt = services;
        MyService service = new MyService();
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {
            DataTableRow someRow = dt.getGherkinRows().get(i);

            service.title = someRow.getCells().get(0);
            service.category = new Category(null, someRow.getCells().get(1), null);
            service.user = new User(someRow.getCells().get(2), someRow.getCells().get(3), someRow.getCells().get(4), someRow.getCells().get(5));
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

    public void deleteAll() {
        RestAssured.baseURI = this.baseURI;
        Services services = getServicesFromBE();
        List<MyService> offers = services.content;
        Integer total = services.totalElements;

        if (total <= 0) {
            return;
        }

        for (; ; ) {

            for (int i = 0; i < offers.size(); i++) {
                String offer = offers.get(i).id;
                RestAssured.given().when().delete("/offers/" + offer);
            }
            services = getServicesFromBE();
            offers = services.content;
            total = services.totalElements;

            if (total <= 0) {
                break;
            }
        }
        assertTrue(total == 0);
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
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
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
}
