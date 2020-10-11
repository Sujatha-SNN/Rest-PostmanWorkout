package tests.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC001_CreateSurveyRequest extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {
		testCaseName = "Create a new survey (REST)";
		testDescription = "Create a new survey and Verify";
		nodes = "CreateSurvey";
		authors = "Sujatha";
		category = "API";
	}

	
	@Test(priority=1)
	public void createNewSurvey() {		
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(new Header("Content-Type", "application/json"));
		listHeader.add(new Header("Accept", "application/json"));
		
		Headers headers = new Headers(listHeader);
		// Post the request
		Response response = postWithHeaderAndJsonBody(headers, "{    \"title\" : \"My Template\"}", "surveys");
		
		response.prettyPrint();
		//Verify the Content by Specific Key
		String survey_id = getContentWithKey(response, "id");
		System.out.println("Survey ID " + survey_id);
		
		//Saving data from response in a properties for request-chaining
		writeResponseDetailsToFile("survey_id",survey_id);
		// Verify the response status code
		verifyResponseCode(response, 201);	
		// Verify the response time
		verifyResponseTime(response, 100000);
		
	}


}





