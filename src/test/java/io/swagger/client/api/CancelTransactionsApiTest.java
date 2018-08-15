package io.swagger.client.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.CamtA0600102;
import io.swagger.client.model.CamtA0600202;
import io.swagger.client.model.CancelTransactionRequest;
import io.swagger.client.model.CancellationReason6Code;
import io.swagger.client.model.CancellationRequestDetails1;
import io.swagger.client.model.PendingPaymentCancellationReason2Code;

public class CancelTransactionsApiTest {
	private final CancelTransactionsApi api = new CancelTransactionsApi();

	@Test
	public void CancelTransactionsApiPostTest() throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {
		String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
		String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
		String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
		String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
		String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
		String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
		String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
		String xApi = UtilHelper.getInstance().mymap.get("xApi");
		String xRecord = UtilHelper.getInstance().mymap.get("CancellationStatusConfirmationsApiTest.xRecord");
		boolean signnature_required = Boolean
				.parseBoolean(UtilHelper.getInstance().mymap.get("CancelTransactionsApiTest.signatureRequired"));
		URI uri = null;

		// Provide URL of gpi Connector instance
		// api.getApiClient().setBasePath("https://WIN-SSV7RS8364L:8443/swift.apitracker/v1");
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}

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

		// Verify LAU
		Map<String, List<String>> headers = response.getHeaders();
		laUApplicationID = headers.get("LAUApplicationID").get(0);
		laUCallTime = headers.get("LAUCallTime").get(0);
		laURequestNonce = headers.get("LAURequestNonce").get(0);
		laUResponseNonce = headers.get("LAUResponseNonce").get(0);
		laUVersion = headers.get("LAUVersion").get(0);
		laUSignature = headers.get("LAUSignature").get(0);
		
		ProcessingReport report = UtilHelper.getInstance().schemaValidation(api.getApiClient().getJSON().serialize(responseBody));
		if(report.isSuccess())
			System.out.println("Response Validation Success");
		else
			System.out.println("Response Validation Failed");

	}
}
