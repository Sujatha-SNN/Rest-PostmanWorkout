package tests.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC002_CreatePageRequest extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {
		testCaseName = "Create a new page for survey created (REST)";
		testDescription = "Create a new page and Verify";
		nodes = "CreatePageforSurvey";
		authors = "Sujatha";
		category = "API";
	}

	
	@Test(priority=2)
	public void createNewPage() {		
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(new Header("Content-Type", "application/json"));
		listHeader.add(new Header("Accept", "application/json"));
	
		Headers headers = new Headers(listHeader);
		
		String pathParam = loadProperties("./src/test/resources/reponseAttributes.properties","survey_id");
			
		System.out.println("Path Pparam" + pathParam);
		
		// Post the request with body parameter passed a String
		Response response = 
				postWithHeaderAndJsonBody(headers, pathParam,
						"{    \"title\" : \"Working From Home Check-In\"}", "surveys/{survey_id}/pages");
		
		
		response.prettyPrint();
		//Verify the Content by Specific Key
		String page_id = getContentWithKey(response, "id");
		System.out.println("Page ID" + page_id);
		
		// Verify the response status code
		verifyResponseCode(response, 201);
		//Saving data from response in a properties for request-chaining
		writeResponseDetailsToFile("page_id",page_id);
		// Verify the response time
		verifyResponseTime(response, 100000);
		
	}


}





