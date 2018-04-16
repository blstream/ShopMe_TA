package tests.helpers;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import tests.objects.Category;
import tests.objects.Service;
import tests.objects.Services;
import tests.objects.User;

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

    public void addService(String title, String category, String userName,
                           String email, String phone, String additionalInfo,
                           String basicDescription, Float basicPrice,
                           String extendedDescription, Float extendedPrice,
                           String extraDescription, Float extraPrice) {
        Service content = new Service();
        content.title = title;
        content.category = getCategoryByName(category);
        content.baseDescription = basicDescription;
        content.basePrice = basicPrice;
        content.user = new User(userName, email, phone, additionalInfo);
        content.extendedDescription = extendedDescription;
        content.extendedPrice = extendedPrice;
        content.extraDescription = extraDescription;
        content.extraPrice = extraPrice;
        Gson gson = new Gson();
        String result = gson.toJson(content);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/offers").then().assertThat().statusCode(200);
    }

    public void addServices(Integer numberOfServicesToAdd, String title, String category, String userName,
                            String email, String phone, String additionalInfo,
                            String basicDescription, Float basicPrice,
                            String extendedDescription, Float extendedPrice,
                            String extraDescription, Float extraPrice) {
        for (int i = 0; i < numberOfServicesToAdd; i++) {
            addService(title, category, userName,
                    email, phone, additionalInfo,
                    basicDescription, basicPrice,
                    extendedDescription, extendedPrice,
                    extraDescription, extraPrice);
        }
    }

    public void deleteAll() {
        RestAssured.baseURI = this.baseURI;
        Services services = getServiceFromBE();
        List<Service> offers = services.content;
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
}