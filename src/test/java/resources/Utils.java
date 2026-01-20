package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Utils {
    RequestSpecification reqSpec;
    public RequestSpecification requestSpecification(){
        reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").build();
        return reqSpec;
    }
}
