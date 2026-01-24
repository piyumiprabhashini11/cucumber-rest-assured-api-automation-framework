package stepDefinitions;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Write a code that will give you place_id
        //Execute this code only when place id is null
        StepDefinitions sd = new StepDefinitions();
        if (StepDefinitions.place_Id == null) {
            sd.add_Place_Payload("Ann", "French", "France");
            sd.user_calls_with_http_Request("AddPlaceAPI", "POST");
            sd.verify_place_id_created_maps_to_using("Ann", "getPlaceAPI");
        }
    }
}

