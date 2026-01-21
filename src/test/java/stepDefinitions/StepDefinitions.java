package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification reqSpec;
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {
		TestDataBuild data = new TestDataBuild();
		req = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address)).log().all();
	}
	
	@When("User calls {string} with POST http request") 
    public void user_calls_with_POST_http_Request(String string){
        resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		response=req.when().post("/maps/api/place/add/json")
				.then().spec(resSpec).extract().response();
}
	@Then("The API call is success with the status code {int}")
	public void the_API_call_got_success_with_statuscode(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		}
	
   @Then("{string} inside the response body is {string}")
   public void inside_the_response_body_is(String key,String expectedValue) {
	   JsonPath js = response.jsonPath();
	   assertEquals(js.getString(key),expectedValue);
   }
}

