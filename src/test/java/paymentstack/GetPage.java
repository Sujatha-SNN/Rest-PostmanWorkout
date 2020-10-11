package paymentstack;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.containsString;

public class GetPage extends BaseClass{
@Test(dependsOnMethods = "paymentstack.GetAllPaymentPages.getAllPages")
public void getCreatedPage()
{
	loadURL();
	RestAssured.
	given()
	.contentType(ContentType.JSON)
	.pathParam(":id_or_slug", id)
	.log().all()
	.get("page/{:id_or_slug}")
	.then()
	.assertThat()
	.statusCode(200)
	.body(containsString(id))
	.log()
	.all()
	.extract()
	.response();
	
	
}
}
