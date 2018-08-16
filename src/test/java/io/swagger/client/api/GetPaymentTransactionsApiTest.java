package io.swagger.client.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.CamtA0100103;
import io.swagger.client.model.CamtA0100202;
import io.swagger.client.model.CamtA0300103;
import io.swagger.client.model.CamtA0300203;
import io.swagger.client.model.DateTimePeriodDetails;
import io.swagger.client.model.GetPaymentTransactionsRequest;

/**
 * API tests for GetPaymentTransactionsApi
 */
//@Ignore
public class GetPaymentTransactionsApiTest {

	private final static GetPaymentTransactionsApi api = new GetPaymentTransactionsApi();

	static String laUApplicationID;
	static String laUVersion;
	static String laUCallTime;
	static String laURequestNonce;
	static String laUResponseNonce;
	static String laUSigned;
	static String laUSignature;
	static String xApi;
	static String xRecord;
	static boolean signnature_required;
	static URI uri = null;

	@BeforeClass
	public static void setup() throws NoSuchAlgorithmException, IOException {
		laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
		laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
		laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
		laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
		laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
		laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
		laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
		xApi = UtilHelper.getInstance().mymap.get("xApi");
		xRecord = UtilHelper.getInstance().mymap.get("GetPaymentTransactionsApiTest.xRecord");
		signnature_required = Boolean
				.parseBoolean(UtilHelper.getInstance().mymap.get("GetPaymentTransactionsApiTest.signatureRequired"));
		uri = null;
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void getPaymentTransactionsPostTest() throws ApiException, NoSuchAlgorithmException, IOException {

		CamtA0300103 requestBody = new CamtA0300103();
		requestBody.setGetPaymentTransactionsRequest(new GetPaymentTransactionsRequest());
		List<String> myInstitution = asList("CCLABEB0");
		requestBody.getGetPaymentTransactionsRequest().setMyInstitution(myInstitution);
		requestBody.getGetPaymentTransactionsRequest().setTimeWindow(new DateTimePeriodDetails());
		requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setFromDateTime("2017-06-17T09:45:16.058Z");
		requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setToDateTime("2017-06-18T09:45:16.058Z");
		// requestBody.getGetPaymentTransactionsRequest().setEvent(PaymentTransactionState1Code.RCCA);
		requestBody.getGetPaymentTransactionsRequest().setMaximumNumber("10");

		// Print the JSON structure constructed
		System.out.println(api.getApiClient().getJSON().serialize(requestBody));

		// TODO: Set always to 'true' and provide gpi Connector's certificate
		// for a production environment
		api.getApiClient().setVerifyingSsl(true);
		// api.getApiClient().setSslCaCert(sslCaCert);
		// api.getApiClient().setKeyManagers(managers);

		// Call API

		ApiResponse<CamtA0300203> response = api.getPaymentTransactionsPostWithHttpInfo(laUApplicationID, laUVersion,
				laUCallTime, laURequestNonce, laUSigned, laUSignature, xApi, requestBody, xRecord);
		// Print response
		CamtA0300203 responseBody = response.getData();
		System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		ProcessingReport report = null;
		try {
			report = UtilHelper.getInstance().schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(report.isSuccess(), true);
	}

	@Test
	public void getPaymentTransactionsPost404ErrorTest()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {

		CamtA0300103 requestBody = new CamtA0300103();
		CamtA0300203 responseBody = null;
		try {
			ApiResponse<CamtA0300203> response = api.getPaymentTransactionsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, xApi, requestBody, "1");
			// Print response
			responseBody = response.getData();
			System.out.println(responseBody.getGetPaymentTransactionsResponse());
			System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println("Response Code =" + e.getCode() + " Response Message=" + e.getMessage());
			StringBuilder value = UtilHelper.getInstance().getErrorValue("/get_payment_transactions",
					String.valueOf(e.getCode()));
			// compare with response
			System.out.println(value);
			assertEquals(value.toString(), e.getMessage());
		}

		ProcessingReport report = UtilHelper.getInstance()
				.schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		assertEquals(report.isSuccess(), true);

	}

	@Test
	public void getPaymentTransactionsPost401ErrorTestWithInvalidXApi()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {

		CamtA0300103 requestBody = new CamtA0300103();
		CamtA0300203 responseBody = null;
		try {
			ApiResponse<CamtA0300203> response = api.getPaymentTransactionsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, "wrong", requestBody, xRecord);
			// Print response
			responseBody = response.getData();
			System.out.println(responseBody.getGetPaymentTransactionsResponse());
			System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println(e.getMessage());
			assertEquals(e.getMessage(), "Missing or invalid API key.");
		}

		ProcessingReport report = UtilHelper.getInstance()
				.schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		assertEquals(report.isSuccess(), true);

	}
}
