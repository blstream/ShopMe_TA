package tests.helpers;

import com.google.gson.Gson;
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
        content.title = service.getTitle();
        content.category = getCategoryByName(service.category.getName());
        content.baseDescription = service.getBaseDesription();
        content.basePrice = service.getBasePrice();
        content.user.voivodeship = new Voivodeship(service.user.voivodeship.getName());
        content.user = new User(service.user.getName(), service.user.getEmail(), service.user.getPhoneNumber(), service.user.getAdditionalInfo(), service.user.voivodeship, service.user.getCity());
        content.extendedDescription = service.getExtendedDesription();
        content.extendedPrice = service.getExtendedPrice();
        content.extraDescription = service.getExtraDesription();
        content.extraPrice = service.getExtraPrice();
        Gson gson = new Gson();
        String result = gson.toJson(content);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/offers").then().assertThat().statusCode(201);
    }

    public void addServices(List<MyService> myServiceList) {
        for (int i = 0; i < myServiceList.size(); i++) {
            addService(myServiceList.get(i));
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

    public void addUser(){
        Address address = new Address("5d214c01-95c3-4ec4-8f68-51dfb80b191c","Niepodległości", "12/1", "Szczecin", "70-125");
        User user = new User();

        user.id = "5d214c01-95c3-4ec4-8f68-51dfb80b191c";
        user.name = "Johnnie";
        user.surname = "Walkier";
        user.email = "veryunknowne@gmail.com";
        user.password = "a123456";
        user.phoneNumber = "0234567890";
        user.bankAccount = "01234567890123456789012345";
        user.address = address;
        user.voivodeship = new Voivodeship("1511273a-bb97-4e8a-924b-e6ff7583f135", "WesternPomeranian");
        user.invoiceRequest = true;
        user.invoice = new Invoice("5d214c01-95c3-4ec4-8f68-51dfb80b191c","Fight Club Sp.z.o.o.", "123-456-78-90", address);

        Gson gson = new Gson();
        String result = gson.toJson(user);
System.out.println("------------------------------- "+ result);
        RestAssured.baseURI = this.baseURI;
        RestAssured.given().contentType("application/json").body(result).when().post("/users").then().assertThat().statusCode(200);
    }
}