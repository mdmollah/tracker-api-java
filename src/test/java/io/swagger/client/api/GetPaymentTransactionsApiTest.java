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
import org.junit.runner.RunWith;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.AgentType1;
import io.swagger.client.model.AmountType1;
import io.swagger.client.model.CamtA0100103;
import io.swagger.client.model.CamtA0100202;
import io.swagger.client.model.CamtA0300103;
import io.swagger.client.model.CamtA0300203;
import io.swagger.client.model.DateTimePeriodDetails;
import io.swagger.client.model.GetPaymentTransactionsRequest;
import io.swagger.client.model.Location1Code;
import io.swagger.client.model.PaymentReason1Code;
import io.swagger.client.model.PaymentScenario2Code;
import io.swagger.client.model.PaymentStatus3;
import io.swagger.client.model.PaymentTransactionState1Code;
import io.swagger.client.model.PaymentsPartyType1Code;
import io.swagger.client.model.TypeOfAmount8Code;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

/**
 * API tests for GetPaymentTransactionsApi
 */
@RunWith(JUnitParamsRunner.class)
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
	public void getPaymentTransactionsPostTest()
			throws ApiException, NoSuchAlgorithmException, IOException, InterruptedException {
		Thread.sleep(2000);
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
	public void getPaymentTransactionsPost404ErrorTest() throws ApiException, NoSuchAlgorithmException, IOException,
			ProcessingException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
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
	public void getPaymentTransactionsPost401ErrorTestWithInvalidXApi() throws ApiException, NoSuchAlgorithmException,
			IOException, ProcessingException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
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

	@Test
	@FileParameters("src/test/resources/Get_Payment_Transactions.csv")
	public void requestApiWithDifferentRequestBody(String institute, String fromDate, String toDate,
			String payment_scenario_return_criteria, String bic, String role, String location,
			String instructionIdentification, String status, String reason, String type,String currency,String fromAmount,String toAmount,String event,boolean subject_cancel,String maxNumber,String more,String Error) throws InterruptedException {
		Thread.sleep(2000);
		CamtA0300103 requestBody = new CamtA0300103();
		requestBody.setGetPaymentTransactionsRequest(new GetPaymentTransactionsRequest());
		List<String> myInstitution = asList(institute);
		requestBody.getGetPaymentTransactionsRequest().setMyInstitution(myInstitution);
		requestBody.getGetPaymentTransactionsRequest().setTimeWindow(new DateTimePeriodDetails());
		requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setFromDateTime(fromDate);
		requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setToDateTime(toDate);
		requestBody.getGetPaymentTransactionsRequest()
				.setPaymentScenarioReturnCriteria(PaymentScenario2Code.valueOf(payment_scenario_return_criteria));
		List<String> anyBic = asList(bic);
		AgentType1 agent = new AgentType1();
		agent.setAnyBic(anyBic);
		agent.setRole(PaymentsPartyType1Code.valueOf(role));
		List<AgentType1> agents = asList(agent);
		requestBody.getGetPaymentTransactionsRequest().setAgent(agents);
		requestBody.getGetPaymentTransactionsRequest().setLocation(Location1Code.valueOf(location));
		requestBody.getGetPaymentTransactionsRequest().setInstructionIdentification(instructionIdentification);
		PaymentStatus3 status3 = new PaymentStatus3();
		status3.setReason(PaymentReason1Code.valueOf(status));
		status3.setReason(PaymentReason1Code.valueOf(reason));
		List<PaymentStatus3> status3s = asList(status3);
		requestBody.getGetPaymentTransactionsRequest().setTransactionStatus(status3s);
		AmountType1 amount = new AmountType1();
		amount.setType(TypeOfAmount8Code.valueOf(type));
		amount.setCurrency(currency);
		amount.setFromAmount(fromAmount);
		amount.setToAmount(toAmount);
		requestBody.getGetPaymentTransactionsRequest().setAmount(amount);
		requestBody.getGetPaymentTransactionsRequest().setEvent(PaymentTransactionState1Code.valueOf(event));
		requestBody.getGetPaymentTransactionsRequest().setSubjectToCancellationProcessIndicator(subject_cancel);
		requestBody.getGetPaymentTransactionsRequest().setMaximumNumber(maxNumber);
		requestBody.getGetPaymentTransactionsRequest().setMore(more);
		
		CamtA0300203 responseBody = null;
		try {
			ApiResponse<CamtA0300203> response = api.getPaymentTransactionsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, xApi, requestBody, xRecord);
			// Print response
			responseBody = response.getData();
			System.out.println(responseBody.getGetPaymentTransactionsResponse());
			System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println(e.getMessage());
			assertEquals(e.getMessage(), Error);
		}

	}

}
