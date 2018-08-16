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

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0400203;
import io.swagger.client.model.CamtA0600102;
import io.swagger.client.model.CamtA0600202;
import io.swagger.client.model.CancelTransactionRequest;
import io.swagger.client.model.CancellationReason6Code;
import io.swagger.client.model.CancellationRequestDetails1;
import io.swagger.client.model.PendingPaymentCancellationReason2Code;

public class CancelTransactionsApiTest {
	private final static CancelTransactionsApi api = new CancelTransactionsApi();

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
		signnature_required = Boolean
				.parseBoolean(UtilHelper.getInstance().mymap.get("CancelTransactionsApiTest.signatureRequired"));
		uri = null;
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void cancelTransactionsApiPostTest()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {
		CamtA0600102 requestBody = new CamtA0600102();
		requestBody.setCancelTransactionRequest(new CancelTransactionRequest());
		requestBody.getCancelTransactionRequest().setFrom("");
		requestBody.getCancelTransactionRequest().setBusinessService("");
		requestBody.getCancelTransactionRequest().setCaseIdentification("");
		requestBody.getCancelTransactionRequest().setUetr("");
		requestBody.getCancelTransactionRequest().setOriginalInstructionIdentification("");
		requestBody.getCancelTransactionRequest().setUnderlyingCancellationDetails(new CancellationRequestDetails1());
		requestBody.getCancelTransactionRequest().getUnderlyingCancellationDetails()
				.setCancellationReasonInformation(CancellationReason6Code.AGNT);
		requestBody.getCancelTransactionRequest().getUnderlyingCancellationDetails()
				.setIndemnityAgreement(PendingPaymentCancellationReason2Code.INDM);

		System.out.println(api.getApiClient().getJSON().serialize(requestBody));

		api.getApiClient().setVerifyingSsl(true);
		// api.getApiClient().setSslCaCert(sslCaCert);
		// api.getApiClient().setKeyManagers(managers);

		ApiResponse<CamtA0600202> response = api.cancelTransactionsPostWithHttpInfo(laUApplicationID, laUVersion,
				laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi, requestBody, xRecord);

		// Print response
		CamtA0600202 responseBody = response.getData();
		System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		System.out.println(response.getStatusCode());
		// Verify LAU
		ProcessingReport report = UtilHelper.getInstance()
				.schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		assertEquals(report.isSuccess(), true);

	}

	@Test
	public void cancelTransactionsApiPost404ErrorTest()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {

		CamtA0600102 requestBody = new CamtA0600102();
		CamtA0600202 responseBody = null;
		try {
			ApiResponse<CamtA0600202> response = api.cancelTransactionsPostWithHttpInfo(laUApplicationID, laUVersion,
					laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi, requestBody, "1");

			// Print response
			responseBody = response.getData();
			System.out.println(responseBody.getCancelTransactionResponse());
			System.out.println(api.getApiClient().getJSON().serialize(responseBody));
		} catch (ApiException e) {
			// TODO: handle exception
			// reading from Swagger.json
			System.out.println("Response Code =" + e.getCode() + " Response Message=" + e.getMessage());
			StringBuilder value = UtilHelper.getInstance().getErrorValue("/cancel_transactions",
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
	public void cancelTransactionsApiPost401ErrorTestWithInvalidXApi()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {

		CamtA0600102 requestBody = new CamtA0600102();
		CamtA0600202 responseBody = null;
		try {
			ApiResponse<CamtA0600202> response = api.cancelTransactionsPostWithHttpInfo(laUApplicationID, laUVersion,
					laUCallTime, laURequestNonce, laUSigned, laUSignature, signnature_required, xApi, requestBody,
					xRecord);
			// Print response
			responseBody = response.getData();
			System.out.println(responseBody.getCancelTransactionResponse());
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
