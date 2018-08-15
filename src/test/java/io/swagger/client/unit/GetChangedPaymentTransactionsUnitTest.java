package io.swagger.client.unit;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.GetChangedPaymentTransactionsApi;
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0400203;
import io.swagger.client.model.GetChangedPaymentTransactionsRequest;

public class GetChangedPaymentTransactionsUnitTest {

	@Test
	public void ValidateBaseUrl() {

		GetChangedPaymentTransactionsApi api = new GetChangedPaymentTransactionsApi();
		api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");
		String base = api.getApiClient().getBasePath();
		Assert.assertEquals(base, "https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");
		System.out.println("Passed Test ValidateBaseUrl");
	}

	@Test
	public void getChangedPaymentTra29nsactionsPostWithHttpInfoShouldReturnResponse() throws ApiException {
		CamtA0400103 requestBody = new CamtA0400103();
		requestBody.setGetChangedPaymentTransactionsRequest(new GetChangedPaymentTransactionsRequest());
		List<String> myInstitution = asList("CCLABEB0");
		requestBody.getGetChangedPaymentTransactionsRequest().setMyInstitution(myInstitution);
		requestBody.getGetChangedPaymentTransactionsRequest().setStartTime("2017-05-25T09:24:41.174Z");
		GetChangedPaymentTransactionsApi test = mock(GetChangedPaymentTransactionsApi.class);

		ApiResponse<CamtA0400203> response = mock(ApiResponse.class);

		when(test.getChangedPaymentTransactionsPostWithHttpInfo("001", "1.0", "2018-03-23T15:56:26.728Z",
				"e802ab96-bb3a-4965-9139-5214b9f0f074",
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])",
				"U1khA8h9Lm1PqzB99fG6uw", "", requestBody, "1")).thenReturn(response);

		Assert.assertEquals(test.getChangedPaymentTransactionsPostWithHttpInfo("001", "1.0", "2018-03-23T15:56:26.728Z",
				"e802ab96-bb3a-4965-9139-5214b9f0f074",
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])",
				"U1khA8h9Lm1PqzB99fG6uw", "", requestBody, "1"), response);
		System.out.println("Passed Test getChangedPaymentTransactionsPostWithHttpInfoShouldReturnResponse");
	}

	@Test
	public void getChangedPaymentTransactionsPostShouldReturnData() throws ApiException {
		CamtA0400103 requestBody = new CamtA0400103();
		requestBody.setGetChangedPaymentTransactionsRequest(new GetChangedPaymentTransactionsRequest());
		List<String> myInstitution = asList("CCLABEB0");
		requestBody.getGetChangedPaymentTransactionsRequest().setMyInstitution(myInstitution);
		requestBody.getGetChangedPaymentTransactionsRequest().setStartTime("2017-05-25T09:24:41.174Z");
		GetChangedPaymentTransactionsApi test = mock(GetChangedPaymentTransactionsApi.class);

		CamtA0400203 response = mock(CamtA0400203.class);

		when(test.getChangedPaymentTransactionsPost("001", "1.0", "2018-03-23T15:56:26.728Z",
				"e802ab96-bb3a-4965-9139-5214b9f0f074",
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])",
				"U1khA8h9Lm1PqzB99fG6uw", "", requestBody, "1")).thenReturn(response);

		Assert.assertEquals(test.getChangedPaymentTransactionsPost("001", "1.0", "2018-03-23T15:56:26.728Z",
				"e802ab96-bb3a-4965-9139-5214b9f0f074",
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])",
				"U1khA8h9Lm1PqzB99fG6uw", "", requestBody, "1"), response);
		System.out.println("Passed Test getChangedPaymentTransactionsPostShouldReturnData");

	}

}
