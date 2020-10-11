package tests.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC003_CreateQuestionsRequest extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {
		testCaseName = "Create a new question for page created (REST)";
		testDescription = "Create a new question for the created survey and page ids and Verify";
		nodes = "CreateQuestionforSurvey";
		authors = "Sujatha";
		category = "API";
		dataFileName = "TC_003CQ";
		dataFileType = "JSON";
	}

	
	@Test(invocationCount=2,priority=2)
	public void createNewQuestion() {		
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(new Header("Content-Type", "application/json"));
		listHeader.add(new Header("Accept", "application/json"));
		//listHeader.add(new Header("X-Atlassian-Token", "no-check"));
		Headers headers = new Headers(listHeader);
		
		String pathParamSurveyId = loadProperties("./src/test/resources/reponseAttributes.properties","survey_id");
		String pathParamPageId = loadProperties("./src/test/resources/reponseAttributes.properties","page_id");
			
		System.out.println("Path Pparams" + pathParamSurveyId + "" + pathParamPageId);
		Map<String,String> pathParamMap =  new LinkedHashMap<String,String>();
		pathParamMap.put("survey_id", pathParamSurveyId);
		pathParamMap.put("page_id", pathParamPageId);
		// Post the request
		Response response = 
				postWithHeaderAndJsonBody(headers, pathParamMap,
						"TC_003CQ.json", "surveys/{survey_id}/pages/{page_id}/questions");
		
		
		response.prettyPrint();
		//Verify the Content by Specific Key
		String question_id = getContentWithKey(response, "id");
		System.out.println("Question ID" + question_id);
		//Saving data from response in a properties for request-chaining
		writeResponseDetailsToFile("question_id1",question_id);
		// Verify the response status code
		verifyResponseCode(response, 201);	
		// Verify the response time
		verifyResponseTime(response, 100000);
		
	}


}





