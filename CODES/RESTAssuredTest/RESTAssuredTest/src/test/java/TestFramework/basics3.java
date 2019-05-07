package TestFramework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import googleAPIs.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics3 {
	  private static Logger log =LogManager.getLogger(basics3.class.getName());
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		//C:\work\RESTAssuredTest\env.properties
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}

	@Test
	public void AddandDeletePlace()
	{
		
		//Task 1- Grab the response
		log.info("Host information"+prop.getProperty("HOST"));
		RestAssured.baseURI= prop.getProperty("HOST");
		Response res=given().
		
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources1.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		// Task 2- Grab the Place ID from response
		
		String responseString=res.asString();
		log.info(responseString);
		JsonPath js= new JsonPath(responseString);
		String placeid=js.get("place_id");
		log.info(placeid);
		
		
		//Task 3 place this place id in the Delete request
		given().
		queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
		
		body("{"+
  "\"place_id\": \""+placeid+"\""+
"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
		
	}
}
