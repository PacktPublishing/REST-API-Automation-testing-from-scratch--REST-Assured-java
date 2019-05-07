package TestFramework;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class basics8 extends basics7{
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C:\\Users\\rahul\\workspace\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPIUpdate()
	{
		//Creating Issue/Defect
	
		RestAssured.baseURI= "http://localhost:8080";
		Response res=given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID="+getSessionKEY()).
		pathParams("commentid","10103").
		
		body("{ \"body\": \"Updating comment from the automation code\","+
    "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\" }"+
"}").when().
		put("/rest/api/2/issue/10207/comment/{commentid}").then().statusCode(200).extract().response();
	
		
		
		
		
		
		
		
		
		  
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
	
