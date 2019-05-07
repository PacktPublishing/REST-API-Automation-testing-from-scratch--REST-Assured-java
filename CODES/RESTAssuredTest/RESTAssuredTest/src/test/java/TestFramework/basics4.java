package TestFramework;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import resources.*;
public class basics4 {

	
	@Test
	public void postDataXML() throws IOException
	{
		String postdata=GenerateStringFromResource("C:\\Users\\rahul\\Documents\\postdata.xml");
		RestAssured.baseURI="https://maps.googleapis.com";
		Response resp=given().
		
		queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		XmlPath x=ReusableMethods.rawToXML(resp);
		System.out.println(x.get("PlaceAddResponse.place_id"));
		
		
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		

	}
	
	public static String GenerateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));

	}
}
