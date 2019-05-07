package TestFramework;
import org.testng.annotations.Test;

import googleAPIs.payLoad;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

public class basics2 {

	
	@Test
	public void createPlaceAPI()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		
		queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
		body(payLoad.createPlaceData()).
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		

	}
}
