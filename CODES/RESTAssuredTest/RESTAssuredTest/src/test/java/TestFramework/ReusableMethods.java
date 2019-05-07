package TestFramework;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	
	public static XmlPath rawToXML(Response r)
	{
	
		
		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
		
	}
	
	public static JsonPath rawToJson(Response r)
	{ 
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		System.out.println(respon);
		return x;
	}
	public static String getSessionKEY()
	{
		RestAssured.baseURI= "http://localhost:8080";
		Response res=given().header("Content-Type", "application/json").
		body("{ \"username\": \"rahulonlinetutor\", \"password\": \"Jira12345\" }").
		when().
		post("/rest/auth/1/session").then().statusCode(200).
		extract().response();
		 JsonPath js= ReusableMethods.rawToJson(res);
		String sessionid= js.get("session.value");
		return sessionid;
	}
	
	

}
