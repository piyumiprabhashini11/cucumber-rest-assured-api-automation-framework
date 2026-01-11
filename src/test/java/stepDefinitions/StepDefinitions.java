package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SetGoogleMapDetails;
import pojo.Location;

public class StepDefinitions {
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	@Given("Add Place Payload")
	public void add_Place_Payload() {
		SetGoogleMapDetails ob = new SetGoogleMapDetails();
		ob.setAccuracy(50);
		ob.setName("Frontline house");
		ob.setAddress("29, side layout, cohen 09");
		ob.setPhone_number("(+91) 983 893 3937");
		ob.setWebsite("http://google.com");
		ob.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		ob.setTypes(myList);
		
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		ob.setLocation(lc);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").build();
		RequestSpecification req=given().spec(reqSpec).body(ob).log().all();
		
	}
	
	@When("User calls {string} with POST http request") 
    public void user_calls_with_POST_http_Request(String string){
//		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		Response response=req.when().post("/maps/api/place/add/json")
				.then().spec(resSpec).extract().response();
}	
  
	@Then("The API call is success with the status code {int}")
	public void the_API_call_got_success_with_statuscode(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		}
	
   @Then("{string} inside the response body is {string}")
   public void inside_the_response_body_is(String key,String expectedValue) {
	   String resp=response.asString();
	   JsonPath js = new JsonPath(resp);
	   assertEquals(js.getString(key),expectedValue);
   }	

}

