package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
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
	
	@When("User calls {string} with {string} http request")
    public void user_calls_with_http_Request(String apiType,String apiMethod) throws IOException {
		APIResources  apiResource = APIResources.valueOf(apiType);//Constructor of 'APIResources' enum java class will be invoked with the type
		// of apiType (AddPlace API, GetPlaceAPI etc) you pass
		System.out.println(apiResource.getResource());
		if(apiMethod.equalsIgnoreCase("POST")) {
			response=req.when().post(apiResource.getResource());
		} else if (apiMethod.equalsIgnoreCase("GET")) {
			response=req.when().get(apiResource.getResource());
		}
}
	@Then("The API call is success with the status code {int}")
	public void the_API_call_got_success_with_statuscode(Integer int1) {

		assertEquals(response.getStatusCode(),200);
		}
	
   @Then("{string} inside the response body is {string}")
   public void inside_the_response_body_is(String key,String expectedValue) throws IOException {
	     assertEquals(getJsonPath(response,key),expectedValue);
   }

	@And("Verify place_Id created maps to {string} using {string}")
	public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String apiType) throws IOException {
		String place_Id=getJsonPath(response,"place_id");
		req= given().spec(requestSpecification()).queryParam("place_id",place_Id);
		user_calls_with_http_Request(apiType,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
}

