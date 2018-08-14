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
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0400203;
import io.swagger.client.model.GetChangedPaymentTransactionsRequest;

public class GetChangedPaymentTransactionsApiTest {

	private final GetChangedPaymentTransactionsApi api = new GetChangedPaymentTransactionsApi();
   
    @Test
    public void getChangedPaymentTransactionsPostTest() throws ApiException, NoSuchAlgorithmException, IOException {
    	
    	
        String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
        String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
        String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
        String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
        String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
        String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
        String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
        String xApi = UtilHelper.getInstance().mymap.get("xApi");
        String xRecord= UtilHelper.getInstance().mymap.get("GetChangedPaymentTransactionsApiTest.xRecord");
        		
        URI uri = null;

        // Provide URL of gpi Connector instance
        //api.getApiClient().setBasePath("https://WIN-SSV7RS8364L:8443/swift.apitracker/v1");
        api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2");
        
        try {
            uri = new URI(api.getApiClient().getBasePath());
        } catch (URISyntaxException ex) {
            Logger.getLogger("Tracker API").log(Level.SEVERE,
                    null, ex);
        }
        
        
        CamtA0400103 requestBody = new CamtA0400103();
        requestBody.setGetChangedPaymentTransactionsRequest(new GetChangedPaymentTransactionsRequest());
        List<String> myInstitution = asList("CCLABEB0");
        requestBody.getGetChangedPaymentTransactionsRequest().setMyInstitution(myInstitution);
        requestBody.getGetChangedPaymentTransactionsRequest().setStartTime("2017-05-25T09:24:41.174Z");

        // Print the JSON structure constructed
        System.out.println(api.getApiClient().getJSON().serialize(requestBody));
        
       
        // TODO: Set always to 'true' and provide gpi Connector's certificate 
        // for a production environment
        api.getApiClient().setVerifyingSsl(true);
        //api.getApiClient().setSslCaCert(sslCaCert);
        //api.getApiClient().setKeyManagers(managers);
        
        
      
            ApiResponse<CamtA0400203> response
                    = api.getChangedPaymentTransactionsPostWithHttpInfo(laUApplicationID,
                            laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature,xApi,requestBody,xRecord);
            // Print response
            CamtA0400203 responseBody = response.getData();
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
    
    

