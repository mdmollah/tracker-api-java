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

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.CamtA0500103;
import io.swagger.client.model.CamtA0500203;
import io.swagger.client.model.GetInvalidEventsRequest;

/**
 * API tests for GetInvalidEventsApi
 */
//@Ignore
public class GetInvalidEventsApiTest {

	private final GetInvalidEventsApi api = new GetInvalidEventsApi();

	@Test
	public void getInvalidEventsPostTest()
			throws ApiException, NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {

		String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
		String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
		String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
		String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
		String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
		String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
		String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
		String xApi = UtilHelper.getInstance().mymap.get("xApi");
		String xRecord = UtilHelper.getInstance().mymap.get("GetInvalidEventsApiTest.xRecord");

		URI uri = null;

		// Provide URL of gpi Connector instance
		// api.getApiClient().setBasePath("https://WIN-SSV7RS8364L:8443/swift.apitracker/v1");
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");

		try {
			uri = new URI(api.getApiClient().getBasePath());
		} catch (URISyntaxException ex) {
			Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
		}

		CamtA0500103 requestBody = new CamtA0500103();
		requestBody.setGetInvalidEventsRequest(new GetInvalidEventsRequest());
		List<String> myInstitution = asList("CCLABEB0");
		requestBody.getGetInvalidEventsRequest().setMyInstitution(myInstitution);
		requestBody.getGetInvalidEventsRequest().setFromDateTime("2017-05-25T09:00:00.000Z");
		requestBody.getGetInvalidEventsRequest().setToDateTime("2017-05-25T17:30:00.000Z");

		// Print the JSON structure constructed
		System.out.println(api.getApiClient().getJSON().serialize(requestBody));

		// TODO: Set always to 'true' and provide gpi Connector's certificate
		// for a production environment
		api.getApiClient().setVerifyingSsl(true);
		// api.getApiClient().setSslCaCert(sslCaCert);
		// api.getApiClient().setKeyManagers(managers);

		// Call API

		ApiResponse<CamtA0500203> response = api.getInvalidEventsPostWithHttpInfo(laUApplicationID, laUVersion,
				laUCallTime, laURequestNonce, laUSigned, laUSignature, xApi, requestBody, xRecord);
		// Print response
		CamtA0500203 responseBody = response.getData();
		System.out.println(api.getApiClient().getJSON().serialize(responseBody));

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
		if (report.isSuccess())
			System.out.println("Response Validation Success");
		else
			System.out.println("Response Validation Failed");
	}

}
