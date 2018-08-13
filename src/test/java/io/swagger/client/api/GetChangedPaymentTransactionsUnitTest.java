package io.swagger.client.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.squareup.okhttp.internal.framed.Http2;

import static org.mockito.Mockito.*;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0400203;
import io.swagger.client.model.CamtA0500103;
import io.swagger.client.model.GetChangedPaymentTransactionsRequest;
import okhttp3.OkHttpClient;



public class GetChangedPaymentTransactionsUnitTest {

	
	@Test
	public void ValidateBaseUrl()
	{
		
		GetChangedPaymentTransactionsApi api = new GetChangedPaymentTransactionsApi();
		String base = api.getApiClient().getBasePath();
		Assert.assertEquals(base, "https://local-api-domain/swift-apitracker-pilot/v2");
	}
	@Test
	public void getChangedPaymentTransactionsPostWithHttpInfoShouldReturnResponse() throws ApiException
	{
		CamtA0400103 requestBody = new CamtA0400103();
        requestBody.setGetChangedPaymentTransactionsRequest(new GetChangedPaymentTransactionsRequest());
        List<String> myInstitution = asList("CCLABEB0");
        requestBody.getGetChangedPaymentTransactionsRequest().setMyInstitution(myInstitution);
        requestBody.getGetChangedPaymentTransactionsRequest().setStartTime("2017-05-25T09:24:41.174Z");
		GetChangedPaymentTransactionsApi test = mock(GetChangedPaymentTransactionsApi.class);
		
		ApiResponse<CamtA0400203> response = mock(ApiResponse.class);
						
		when(test.getChangedPaymentTransactionsPostWithHttpInfo("001","1.0", 
				"2018-03-23T15:56:26.728Z", "e802ab96-bb3a-4965-9139-5214b9f0f074", 
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])", "U1khA8h9Lm1PqzB99fG6uw",
				requestBody)).thenReturn(response);
		
		Assert.assertEquals(test.getChangedPaymentTransactionsPostWithHttpInfo
				("001","1.0","2018-03-23T15:56:26.728Z", "e802ab96-bb3a-4965-9139-5214b9f0f074", 
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])", "U1khA8h9Lm1PqzB99fG6uw",
				requestBody),response);
	
	}
	@Test
	public void getChangedPaymentTransactionsPostShouldReturnData() throws ApiException
	{
		CamtA0400103 requestBody = new CamtA0400103();
        requestBody.setGetChangedPaymentTransactionsRequest(new GetChangedPaymentTransactionsRequest());
        List<String> myInstitution = asList("CCLABEB0");
        requestBody.getGetChangedPaymentTransactionsRequest().setMyInstitution(myInstitution);
        requestBody.getGetChangedPaymentTransactionsRequest().setStartTime("2017-05-25T09:24:41.174Z");
		GetChangedPaymentTransactionsApi test = mock(GetChangedPaymentTransactionsApi.class);
		
		CamtA0400203 response = mock(CamtA0400203.class);
						
		when(test.getChangedPaymentTransactionsPost("001","1.0", 
				"2018-03-23T15:56:26.728Z", "e802ab96-bb3a-4965-9139-5214b9f0f074", 
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])", "U1khA8h9Lm1PqzB99fG6uw",
				requestBody)).thenReturn(response);
		
		Assert.assertEquals(test.getChangedPaymentTransactionsPost
				("001","1.0","2018-03-23T15:56:26.728Z", "e802ab96-bb3a-4965-9139-5214b9f0f074", 
				"(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])", "U1khA8h9Lm1PqzB99fG6uw",
				requestBody),response);
	
	}
	
	
}
