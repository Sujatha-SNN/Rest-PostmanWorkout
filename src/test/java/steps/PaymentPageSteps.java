package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.containsString;



public class PaymentPageSteps {
	public static RequestSpecification request;
	public static String id;
	public static Response response;

	@When("Set the body params")
	public void setTheBodyParams() {
		request = RestAssured.given().contentType(ContentType.JSON).body("{\"name\" : \"SNCOM DashBoard\"}");
	}

	@And("Call the post request")
	public void callThePostRequest() {
		response = request.post("page");
	}

	@And("Get the payment page id from response")
	public void setThePaymentPageIDFromResponse() {
		id = response.jsonPath().get("data.id").toString();
	}

	@Then("Check the status to be {int}")
	public void checkTheStatusToBe(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);

	}

	@When("Set the query params")
	public void setTheQueryParams() {
		request.queryParams("perPage", "50").queryParams("page", "1");

	}

	@When("Set the path params")
	public void setThePathParams() {
		System.out.println("id in path params" + id);
		request.log().all()
		.pathParam(":id_or_slug", id);
	}
	
	@When("Call the get method (.*)$")
	public void callTheGetMethod(String methodName) {
		if (methodName.equals("allPaymentsPages"))
			request.get("page");
		else
		{
			System.out.println("Here");
			request.get("page/" + "{:id_or_slug}");
		}
	}

	@When("Verify the created payment page id in response")
	public void verifyTheCreatedPaymentPageIdInResponse() {
		request.then().
		body(containsString(id));
		
	}

	

}
