package resources;

import pojo.Location;
import pojo.SetGoogleMapDetails;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public SetGoogleMapDetails addPlacePayload(){
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

        return ob;
          }
}
