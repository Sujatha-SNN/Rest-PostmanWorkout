package paymentstack;

import io.restassured.RestAssured;

public class BaseClass {
	public static String id;

	public void loadURL() {
		RestAssured.baseURI = "https://api.paystack.co/";
		RestAssured.authentication = RestAssured.oauth2("sk_test_87cfdde2ed324a60065947e5586199871a144672");

	}
}
