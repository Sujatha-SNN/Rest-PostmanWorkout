package hooks;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseClass {
	

	@Given("Set the url for payments page")
	public void setTheUrlForPaymentsPage() {
		RestAssured.baseURI = "https://api.paystack.co/";
		
	}

	@Given("Set the authentication")
	public void setTheAuthentication() {
		RestAssured.authentication = RestAssured.oauth2("sk_test_87cfdde2ed324a60065947e5586199871a144672");
	}
	
	@Given("Set the content type and enable logs")
	public void setTheContentTypeAndEnableLogs() {
		RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON);
	}

}
