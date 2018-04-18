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

    public void addService(String title, String category, String userName,
                           String email, String phone, String additionalInfo,
                           String basicDescription, Float basicPrice,
                           String extendedDescription, Float extendedPrice,
                           String extraDescription, Float extraPrice) {
        MyService content = new MyService();
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

    public void addServices(DataTable services) {
        DataTable dt = services;
        for (int i = 0; i < dt.getGherkinRows().size(); i++) {
            DataTableRow someRow = dt.getGherkinRows().get(i);

            String title = someRow.getCells().get(0);
            String category = someRow.getCells().get(1);
            String userName = someRow.getCells().get(2);
            String email = someRow.getCells().get(3);
            String phone = someRow.getCells().get(4);
            String additionalInfo = someRow.getCells().get(5);
            String basicDescription = someRow.getCells().get(6);
            Float basicPrice = Float.valueOf(someRow.getCells().get(7));
            String extendedDescription = someRow.getCells().get(8);
            Float extendedPrice = Float.valueOf(someRow.getCells().get(9));
            String extraDescription = someRow.getCells().get(10);
            Float extraPrice = Float.valueOf(someRow.getCells().get(11));

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

    public MyService getService(String id){
        MyService service =new MyService();;
        Services servicesFromBE = getServiceFromBE();
        for(int i=0; i<servicesFromBE.content.size();i++)
        {
           if(servicesFromBE.content.get(i).id.equals(id)){
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

    public List<MyService> getServices(int pageNumber, int pageSize){
        MyService service = new MyService();
        Services servicesFromBE = getServiceFromBE();
        List<MyService> servicesOnPage = new ArrayList<MyService>();
        Integer beginPage = pageNumber*pageSize+1;
        Integer endPage = (pageNumber+1)*pageSize;
        for(int i = beginPage; i<endPage; i++)
        {
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
            servicesOnPage.add(i-beginPage, service);
        }
        return servicesOnPage;

    }
}
