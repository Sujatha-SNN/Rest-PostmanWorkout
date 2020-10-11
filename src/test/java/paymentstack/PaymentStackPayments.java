package paymentstack;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PaymentStackPayments extends BaseClass {
	@Test
	public void createPaymentsPage() {
		loadURL();
		Response response = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON)
				.body("{\"name\" : \"SNCOM DashBoard\"}")
				.post("page");
		id = response.jsonPath().get("data.id").toString();
		System.out.println(id);
		response.prettyPrint();
		response
		.then()
		.assertThat()
		.statusCode(200);

	}
}
