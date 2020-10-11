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


public class TC005_DeleteQuestionsRequest extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {
		testCaseName = "Delete a new question for page created (REST)";
		testDescription = "Delete a new question by passing survey id,page id ,question id and Verify";
		nodes = "DeleteQuestionforSurvey";
		authors = "Sujatha";
		category = "API";
	}

	
	@Test(priority=5)
	public void deleteQuestion() {		
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(new Header("Content-Type", "application/json"));
		listHeader.add(new Header("Accept", "application/json"));
		//listHeader.add(new Header("X-Atlassian-Token", "no-check"));
		Headers headers = new Headers(listHeader);
		
		String pathParamSurveyId = loadProperties("./src/test/resources/reponseAttributes.properties","survey_id");
		String pathParamPageId = loadProperties("./src/test/resources/reponseAttributes.properties","page_id");
		String pathParamQuestionId = loadProperties("./src/test/resources/reponseAttributes.properties","question_id1");
			
		System.out.println("Path Pparams" + pathParamSurveyId + "" + pathParamPageId + " " + pathParamQuestionId );
		Map<String,String> pathParamMap =  new LinkedHashMap<String,String>();
		pathParamMap.put("survey_id", pathParamSurveyId);
		pathParamMap.put("page_id", pathParamPageId);
		pathParamMap.put("question_id1", pathParamQuestionId);
		// Post the request
		Response response = 
				deleteWithHeaderAndJsonBody(headers, pathParamMap,"surveys/{survey_id}/pages/{page_id}/questions/{question_id1}");
		response.prettyPrint();
		//Verify the Content by Specific Key
		String question_id = getContentWithKey(response, "id");
		System.out.println("Question ID" + question_id);
		// Verify the response status code
		verifyResponseCode(response, 200);	
		// Verify the response time
		verifyResponseTime(response, 100000);
		
	}


}





