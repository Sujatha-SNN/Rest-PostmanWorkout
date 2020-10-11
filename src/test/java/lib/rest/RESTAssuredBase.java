package lib.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

public class RESTAssuredBase extends PreAndTest{
	public static  String survey_id;

	public static RequestSpecification setLogs() {
		return RestAssured
				.given()
				.log()
				.all();
				/*.contentType(getContentType());*/
	}

	
	//Added for Cucumber + Rest flavor 
	public static RequestSpecification setShortDescription(Headers headers,File file) {
		return RestAssured
				.given()
				//.headers(headers)
				.header(new Header("Content-Type", "application/json"))
				.body(file)
				.log()
				.all();
				
	}
	public static Response get(String URL) {
		return setLogs()
				.when()
				.get(URL);
	}
	
	public static Response getWithQueryParam(String URL,String param1,String param1Value,String param2,String param2Value) {
		return setLogs()
				.queryParam(param1, param1Value)
				.queryParam(param2, param2Value)
				.when()
				.get(URL);
	}


	public static Response get() {
		return setLogs()
				.get();
	}

	public static Response getWithHeader(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.get(URL);
	}

	public static Response post() {

		return setLogs()
				.post();
	}

	public static Response post(String URL) {
		setLogs()
		.post(URL)
		.then()
		.log().all();
		
		return setLogs()
				.post(URL);
	}

	public static Response postWithBodyAsFile(File bodyFile) {

		return setLogs()
				.body(bodyFile)
				.post();
	}
	
	public static Response postWithBodyAsFileAndUrl(File bodyFile, String URL) {
		
	

		return setLogs()
				.body(bodyFile)
				.post(URL);
	}
	
	public static Response postWithHeaderAndForm(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}

	public static Response postWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderAndJsonBody(Map<String, String> headers,
			String jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}

	//created for TC001-Rest API
	public static Response postWithHeaderAndJsonBody(Headers headers,String jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}
	
	//created for TC002_createPage for survey-Rest API
		public static Response postWithHeaderAndJsonBody(Headers headers,String pathParam,String jsonObject, String URL) {

			return setLogs()
					.when()
					.headers(headers)
					.pathParam("survey_id", pathParam)
					.body(jsonObject)
					.post(URL);
		}
		
		
	//created for TC002_createSurvey for survey-Rest API
		
		
		public static void writeResponseDetailsToFile(String key,String value)
		{
			Properties prop = new Properties();
			prop.setProperty(key, value);
			try {
				prop.store(new FileOutputStream(new File("./src/test/resources/reponseAttributes.properties"),true), "Adding response id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//created for TC002_createPage for survey-Rest API
		public static String loadProperties(String fileName,String property)
		{
			String propertyRequested = "";
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(new File(fileName)));
				propertyRequested = (String)prop.get(property);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(prop.toString());
			return propertyRequested;
		}
		
		//created for TC004_ModifyQuestion for survey-Rest API
		public static Response putWithHeaderAndJsonBody(Headers headers,Map pathParamMap,String jsonFile,String URL) {
		
		 RequestSpecification request = setLogs()
							.when()
							.headers(headers)
							.pathParam("survey_id",pathParamMap.get("survey_id"))
							.pathParam("page_id",pathParamMap.get("page_id"))
							.pathParam("question_id1",pathParamMap.get("question_id1"))
							.body(new File("./data/" +jsonFile ));
		 					 return request.put(URL);
							
										
		}
		
		
		//created for TC003_CreateQuestion for survey-Rest API
		public static Response postWithHeaderAndJsonBody(Headers headers,Map pathParamMap,String jsonFile,String URL) {
		
		 RequestSpecification request = setLogs()
							.when()
							.headers(headers)
							.pathParam("survey_id",pathParamMap.get("survey_id"))
							.pathParam("page_id",pathParamMap.get("page_id"))
							.body(new File("./data/" +jsonFile ))
							.log()
							.all();
							 return request.post(URL);
						
										
		}
		
		//created for TC005_DeleteQuestion for survey-Rest API
		public static Response deleteWithHeaderAndJsonBody(Headers headers,Map pathParamMap,String URL) {
			return 	setLogs()
					.when()
					.headers(headers)
					.pathParam("survey_id",pathParamMap.get("survey_id"))
					.pathParam("page_id",pathParamMap.get("page_id"))
					.pathParam("question_id1",pathParamMap.get("question_id1"))
					 .delete(URL);		
								
												
				}
		
		
	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.post(URL);
	}
	
	public static Response delete(String URL) {
		return setLogs()
				.when()
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(
			Map<String, String> headers, String URL) throws Exception {
		return setLogs()
				.when()
				.headers(headers)
				.delete(URL);
	}

	public static Response putWithHeaderAndBodyParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return RestAssured.given().headers(headers).contentType(getContentType()).request()
				.body(jsonObject).when().put(URL);
	}
	
	public static ValidatableResponse enableResponseLog(Response response) {
		return response.then().log().all();
	}

	private static ContentType getContentType() {
		return ContentType.JSON;
	}

	public static void verifyContentType(Response response, String type) {
		if(response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportRequest("The Content type "+type+" matches the expected content type", "PASS");
		}else {
			reportRequest("The Content type "+type+" does not match the expected content type "+response.getContentType(), "FAIL");	
		}
	}

	public static void verifyResponseCode(Response response, int code) {
		if(response.statusCode() == code) {
			reportRequest("The status code "+code+" matches the expected code", "PASS");
		}else {
			reportRequest("The status code "+code+" does not match the expected code"+response.statusCode(), "FAIL");	
		}
	}
	
	public static void verifyResponseTime(Response response, long time) {
		if(response.time() <= time) {
			reportRequest("The time taken "+response.time() +" with in the expected time", "PASS");
		}else {
			reportRequest("The time taken "+response.time() +" is greater than expected SLA time "+time,"FAIL");		
		}
	}

	public static void verifyContentWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if(actValue.equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}
	
	public static void verifyContentsWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if(actValue.get(0).equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}
	
	public static List<String> getContentsWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);			
		}else {
			return null;
		}
	}
	
	public static String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);			
		}else {
			return null;
		}
	}

}
