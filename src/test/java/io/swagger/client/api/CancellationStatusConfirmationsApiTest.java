package io.swagger.client.api;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.CamtA0600102;
import io.swagger.client.model.CamtA0600202;
import io.swagger.client.model.CamtA0700102;
import io.swagger.client.model.CamtA0700202;
import io.swagger.client.model.CancellationResponseDetails1;
import io.swagger.client.model.InvestigationExecutionConfirmation5Code;
import io.swagger.client.model.InvestigationExecutionStatusReason1;
import io.swagger.client.model.PaymentCancellationRejection3Code;
import io.swagger.client.model.PendingPaymentCancellationReason1Code;
import io.swagger.client.model.TransactionCancellationStatusRequest;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CancellationStatusConfirmationsApiTest {
	private final static CancellationStatusConfirmationsApi api = new CancellationStatusConfirmationsApi();

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
		xRecord = UtilHelper.getInstance().mymap.get("CancelTransactionsApiTest.xRecord");
		signnature_required = Boolean.parseBoolean(
				UtilHelper.getInstance().mymap.get("CancellationStatusConfirmationsApiTest.signatureRequired"));
		uri = null;
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void cancellationStatusConfirmationsPostTest() throws ApiException, NoSuchAlgorithmException, IOException,
			ProcessingException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
		CamtA0700102 requestBody = new CamtA0700102();
		requestBody.setTransactionCancellationStatusRequest(new TransactionCancellationStatusRequest());
		requestBody.getTransactionCancellationStatusRequest().setFrom("CCLABEB0");
		requestBody.getTransactionCancellationStatusRequest().setBusinessService("121");
		requestBody.getTransactionCancellationStatusRequest().setUetr("");
		requestBody.getTransactionCancellationStatusRequest().assignmentIdentification("");
		requestBody.getTransactionCancellationStatusRequest().resolvedCaseIdentification("");
		requestBody.getTransactionCancellationStatusRequest()
				.setUnderlyingCancellationDetails(new CancellationResponseDetails1());
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.setInvestigationExecutionStatus(InvestigationExecutionConfirmation5Code.CNCL);
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.setInvestigationExecutionStatusReason(new InvestigationExecutionStatusReason1());
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.getInvestigationExecutionStatusReason().setRejected(PaymentCancellationRejection3Code.AGNT);
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.getInvestigationExecutionStatusReason().setPending(PendingPaymentCancellationReason1Code.PTNA);
		requestBody.getTransactionCancellationStatusRequest().setOriginator("");

		System.out.println(api.getApiClient().getJSON().serialize(requestBody));

		// TODO: Set always to 'true' and provide gpi Connector's certificate
		// for a production environment
		api.getApiClient().setVerifyingSsl(true);
		// api.getApiClient().setSslCaCert(sslCaCert);
		// api.getApiClient().setKeyManagers(managers);

		ApiResponse<CamtA0700202> response = api.cancellationStatusConfirmationsPostWithHttpInfo(laUApplicationID,
				laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi,
				requestBody, xRecord);
		// Print response
		CamtA0700202 responseBody = response.getData();
		System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		System.out.println(response.getStatusCode());
		// Verify LAU
		Map<String, List<String>> headers = response.getHeaders();
		laUApplicationID = headers.get("LAUApplicationID").get(0);
		laUCallTime = headers.get("LAUCallTime").get(0);
		laURequestNonce = headers.get("LAURequestNonce").get(0);
		laUResponseNonce = headers.get("LAUResponseNonce").get(0);
		laUVersion = headers.get("LAUVersion").get(0);
		laUSignature = headers.get("LAUSignature").get(0);

		ProcessingReport report = UtilHelper.getInstance()
				.schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		// System.out.println(report);
		assertEquals(report.isSuccess(), true);

	}

	@Test
	public void cancelTransactionsApiPost404ErrorTest() throws ApiException, NoSuchAlgorithmException, IOException,
			ProcessingException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
		CamtA0700102 requestBody = new CamtA0700102();
		CamtA0700202 responseBody = null;
		try {
			ApiResponse<CamtA0700202> response = api.cancellationStatusConfirmationsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi,
					requestBody, xRecord);
			// Print response
			responseBody = response.getData();
			// System.out.println(responseBody.getTransactionCancellationStatusResponse());
			// System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println("Response Code =" + e.getCode() + " Response Message=" + e.getMessage());
			StringBuilder value = UtilHelper.getInstance().getErrorValue("/cancellation_status_confirmations",
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
	public void cancelTransactionsApiPost401ErrorTestWithInvalidXApi() throws ApiException, NoSuchAlgorithmException,
			IOException, ProcessingException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
		CamtA0700102 requestBody = new CamtA0700102();
		CamtA0700202 responseBody = null;
		try {
			ApiResponse<CamtA0700202> response = api.cancellationStatusConfirmationsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi,
					requestBody, xRecord);
			// Print response
			responseBody = response.getData();
			// System.out.println(responseBody.getTransactionCancellationStatusResponse());
			// System.out.println(api.getApiClient().getJSON().serialize(responseBody));
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
	@FileParameters("src/test/resources/cancel_status.csv")
	public void requestApiWithDifferentRequestBody(String from, String business_service, String uetr,
			String assignment_identification, String resolved_case_identification,
			String investigation_execution_status, String rejected, String pending, String originator,
			String forwarded_to_agent, String error) throws JsonProcessingException, NoSuchAlgorithmException,
			IOException, URISyntaxException, InterruptedException {
		Thread.sleep(2000);
		CamtA0700102 requestBody = new CamtA0700102();
		requestBody.setTransactionCancellationStatusRequest(new TransactionCancellationStatusRequest());
		requestBody.getTransactionCancellationStatusRequest().setFrom(from);
		requestBody.getTransactionCancellationStatusRequest().setBusinessService(business_service);
		requestBody.getTransactionCancellationStatusRequest().setUetr(uetr);
		requestBody.getTransactionCancellationStatusRequest().assignmentIdentification(assignment_identification);
		requestBody.getTransactionCancellationStatusRequest().resolvedCaseIdentification(resolved_case_identification);
		requestBody.getTransactionCancellationStatusRequest()
				.setUnderlyingCancellationDetails(new CancellationResponseDetails1());
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.setInvestigationExecutionStatus(
						InvestigationExecutionConfirmation5Code.valueOf(investigation_execution_status));
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.setInvestigationExecutionStatusReason(new InvestigationExecutionStatusReason1());
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.getInvestigationExecutionStatusReason()
				.setRejected(PaymentCancellationRejection3Code.valueOf(rejected));
		requestBody.getTransactionCancellationStatusRequest().getUnderlyingCancellationDetails()
				.getInvestigationExecutionStatusReason()
				.setPending(PendingPaymentCancellationReason1Code.valueOf(pending));
		requestBody.getTransactionCancellationStatusRequest().setOriginator(originator);
		requestBody.getTransactionCancellationStatusRequest().setForwardedToAgent(forwarded_to_agent);

		CamtA0700202 responseBody = null;
		try {
			ApiResponse<CamtA0700202> response = api.cancellationStatusConfirmationsPostWithHttpInfo(laUApplicationID,
					laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi,
					requestBody, xRecord);
			// Print response
			responseBody = response.getData();
			// System.out.println(responseBody.getTransactionCancellationStatusResponse());
			// System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println("Response Code =" + e.getCode() + " Response Message=" + e.getMessage());
			// compare with response
			assertEquals(e.getMessage(), error);
		}

	}

}
