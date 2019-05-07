package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jiraAPI.resources1;


public class basics7 {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C:\\Users\\rahul\\workspace\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	//@Test
	public void JiraAPICreateComment()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI= "http://localhost:8080";
		Response res=given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
		body("{ \"body\": \"Inserting comment from the automation code\","+
    "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\" }"+
"}").when().
		post(resources1.placePostData()).then().statusCode(201).extract().response();
		   JsonPath js= ReusableMethods.rawToJson(res);
		   String id=js.get("id");
		   System.out.println(id);
		
}
	
	//@Test
	public void AddBook()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI= "http://216.10.245.166";
		Response res=given().
				
		when().
		get("/Library/GetBook.php?ID=bczd227").then().statusCode(200).extract().response();
		System.out.println(res);
		   JsonPath js= ReusableMethods.rawToJson(res);
		   String id=js.get("isbn").toString();
		   System.out.println(id);
		   
		   
		
}
	
	@Test
	public void AddBooks()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI= "http://216.10.245.166";
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "John");
		jsonAsMap.put("isbn", "bcsremlkrzd");
		jsonAsMap.put("aisle", "808");
		jsonAsMap.put("author", "rakul");
		Response res=given().
				header("Content-Type", "application/json").
				body(jsonAsMap).
				
		when().
		post("/Library/Addbook.php").then().statusCode(200).extract().response();
		System.out.println(res);
		   JsonPath js= ReusableMethods.rawToJson(res);
		   String id=js.get("ID");
		   System.out.println(id);
		   
		   
		
}
	
}
