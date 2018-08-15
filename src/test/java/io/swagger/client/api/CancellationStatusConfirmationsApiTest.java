package io.swagger.client.api;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.ActiveOrHistoricCurrencyAndAmount;
import io.swagger.client.model.CamtA0100202;
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0700102;
import io.swagger.client.model.CamtA0700202;
import io.swagger.client.model.CancellationResponseDetails1;
import io.swagger.client.model.GetChangedPaymentTransactionsRequest;
import io.swagger.client.model.InvestigationExecutionConfirmation5Code;
import io.swagger.client.model.InvestigationExecutionStatusReason1;
import io.swagger.client.model.PaymentCancellationRejection3Code;
import io.swagger.client.model.PaymentReason1Code;
import io.swagger.client.model.PaymentStatus3;
import io.swagger.client.model.PaymentStatusType2Choice;
import io.swagger.client.model.PendingPaymentCancellationReason1Code;
import io.swagger.client.model.StatusDetails2;
import io.swagger.client.model.TransactionCancellationStatusRequest;
import io.swagger.client.model.TransactionIndividualStatus4Code;
import io.swagger.client.model.UpdatePaymentStatusRequest;

public class CancellationStatusConfirmationsApiTest {
	private final CancellationStatusConfirmationsApi api = new CancellationStatusConfirmationsApi();

	@Test
	public void cancellationStatusConfirmationsPostTest() throws ApiException, NoSuchAlgorithmException, IOException {
		String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
		String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
		String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
		String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
		String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
		String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
		String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
		String xApi = UtilHelper.getInstance().mymap.get("xApi");
		String xRecord = UtilHelper.getInstance().mymap.get("CancellationStatusConfirmationsApiTest.xRecord");
		boolean signnature_required = Boolean.parseBoolean(UtilHelper.getInstance().mymap.get("CancellationStatusConfirmationsApiTest.signatureRequired"));
		URI uri = null;

		// Provide URL of gpi Connector instance
		// api.getApiClient().setBasePath("https://WIN-SSV7RS8364L:8443/swift.apitracker/v1");
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}

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

		// Verify LAU
		Map<String, List<String>> headers = response.getHeaders();
		laUApplicationID = headers.get("LAUApplicationID").get(0);
		laUCallTime = headers.get("LAUCallTime").get(0);
		laURequestNonce = headers.get("LAURequestNonce").get(0);
		laUResponseNonce = headers.get("LAUResponseNonce").get(0);
		laUVersion = headers.get("LAUVersion").get(0);
		laUSignature = headers.get("LAUSignature").get(0);

	}

}
