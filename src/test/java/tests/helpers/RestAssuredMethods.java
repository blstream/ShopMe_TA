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

    public Services getServiceFromBE() {
        Response response = RestAssured.given().get(baseURI + "/offers");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Services servicesFromBE = gson.fromJson(body.asString(), Services.class);
        return servicesFromBE;
    }

    public void addService(MyService service) {
        MyService content = new MyService();
        content.title = service.getTitle();
        content.category.name = service.category.getName();
        content.baseDescription = service.getBaseDesription();
        content.basePrice = service.getBasePrice();
        content.user.name = service.user.getName();
        content.user.email = service.user.getEmail();
        content.user.phoneNumber = service.user.getPhoneNumber();
        content.user.additionalInfo = service.user.getAdditionalInfo();
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
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {
            DataTableRow someRow = dt.getGherkinRows().get(i);

            service.title = someRow.getCells().get(0);
            service.category.name = someRow.getCells().get(1);
            service.user.name = someRow.getCells().get(2);
            service.user.email = someRow.getCells().get(3);
            service.user.phoneNumber = someRow.getCells().get(4);
            service.user.additionalInfo = someRow.getCells().get(5);
            service.baseDescription = someRow.getCells().get(6);
            service.basePrice = Float.valueOf(someRow.getCells().get(7));
            service.extendedDescription = someRow.getCells().get(8);
            service.extendedPrice = Float.valueOf(someRow.getCells().get(9));
            service.extraDescription = someRow.getCells().get(10);
            service.extraPrice = Float.valueOf(someRow.getCells().get(11));

            addService(service);
        }
    }

    public void deleteAll() {
        RestAssured.baseURI = this.baseURI;
        Services services = getServiceFromBE();
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
            services = getServiceFromBE();
            offers = services.content;
            total = services.totalElements;

            if (total <= 0) {
                break;
            }
        }
        assertTrue(total == 0);
    }

    public MyService getService(String id) {
        MyService service = new MyService();

        Services servicesFromBE = getServiceFromBE();
        for (int i = 0; i < servicesFromBE.content.size(); i++) {
            if (servicesFromBE.content.get(i).id.equals(id)) {
                service.setId(servicesFromBE.content.get(i).id);
                service.setTitle(servicesFromBE.content.get(i).title);
                service.setDate(getServiceFromBE().content.get(i).date);
                service.setBaseDescription(servicesFromBE.content.get(i).baseDescription);
                service.setBasePrice(servicesFromBE.content.get(i).basePrice);
                service.setExtendedDescription(servicesFromBE.content.get(i).extendedDescription);
                service.setExtendedPrice(servicesFromBE.content.get(i).extendedPrice);
                service.setExtraDescription(servicesFromBE.content.get(i).extraDescription);
                service.setExtraPrice(servicesFromBE.content.get(i).extraPrice);
                service.setUser(servicesFromBE.content.get(i).user);
            }

        }
        return service;
    }

    public Services getServices(int pageNumber, int pageSize) {

        Response response = RestAssured.given()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .get(baseURI + "/offers");
        ResponseBody body = response.getBody();
        Gson gson = new Gson();
        Services service = gson.fromJson(body.asString(), Services.class);
        return service;
    }

}
