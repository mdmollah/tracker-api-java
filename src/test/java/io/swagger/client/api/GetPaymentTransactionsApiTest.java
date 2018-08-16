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
import io.swagger.client.model.CamtA0300103;
import io.swagger.client.model.CamtA0300203;
import io.swagger.client.model.DateTimePeriodDetails;
import io.swagger.client.model.GetPaymentTransactionsRequest;
/**
 * API tests for GetPaymentTransactionsApi
 */
//@Ignore
public class GetPaymentTransactionsApiTest {


    private final GetPaymentTransactionsApi api = new GetPaymentTransactionsApi();
    
    @Test
    public void getPaymentTransactionsPostTest() throws ApiException, NoSuchAlgorithmException, IOException {
    	String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
        String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
        String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
        String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
        String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
        String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
        String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
        String xApi = UtilHelper.getInstance().mymap.get("xApi");
        String xRecord= UtilHelper.getInstance().mymap.get("GetPaymentTransactionsApiTest.xRecord");
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

        CamtA0300103 requestBody = new CamtA0300103();
        requestBody.setGetPaymentTransactionsRequest(
                new GetPaymentTransactionsRequest());
        List<String> myInstitution = asList("CCLABEB0");
        requestBody.getGetPaymentTransactionsRequest().setMyInstitution(myInstitution);
        requestBody.getGetPaymentTransactionsRequest().setTimeWindow(new DateTimePeriodDetails());
        requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setFromDateTime(
            "2017-06-17T09:45:16.058Z");
        requestBody.getGetPaymentTransactionsRequest().getTimeWindow().setToDateTime(
            "2017-06-18T09:45:16.058Z");
        //requestBody.getGetPaymentTransactionsRequest().setEvent(PaymentTransactionState1Code.RCCA);
        requestBody.getGetPaymentTransactionsRequest().setMaximumNumber("10");
        

        // Print the JSON structure constructed
        System.out.println(api.getApiClient().getJSON().serialize(requestBody));
        
        

        // TODO: Set always to 'true' and provide gpi Connector's certificate 
        // for a production environment
        api.getApiClient().setVerifyingSsl(true);
        //api.getApiClient().setSslCaCert(sslCaCert);
        //api.getApiClient().setKeyManagers(managers);

        // Call API
       
            ApiResponse<CamtA0300203> response
                    = api.getPaymentTransactionsPostWithHttpInfo(laUApplicationID,
                            laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature,  xApi,requestBody,xRecord);
            // Print response
            CamtA0300203 responseBody = response.getData();
            System.out.println(api.getApiClient().getJSON().serialize(responseBody));
            
            // Verify LAU
            Map<String, List<String>> headers = response.getHeaders();
            laUApplicationID = headers.get("LAUApplicationID").get(0);
            laUCallTime = headers.get("LAUCallTime").get(0);
            laURequestNonce = headers.get("LAURequestNonce").get(0);
            laUResponseNonce = headers.get("LAUResponseNonce").get(0);
            laUVersion = headers.get("LAUVersion").get(0);
            laUSignature = headers.get("LAUSignature").get(0);
            

        // TODO: test validations
    }
    

}
