/*
 * SWIFT SCRL owns the copyright on the source code samples provided
 * as part of gpi Connector, and grants the customer a right to
 * copy, use, and adapt them for the purpose of implementing
 * integration with Tracker API.
 * SWIFT provides these source code samples as is and for the customerís
 * convenience only, without warranties as to their completeness, accuracy
 * fitness for a particular purpose, frequency of updates, ease
 * of maintenance, or absence of errors.
 */


/*
 * gpi API
 * Move your app forward with the gpi API
 *
 * OpenAPI spec version: 1.2.36
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.helper.UtilHelper;
import io.swagger.client.model.ActiveOrHistoricCurrencyAndAmount;
import io.swagger.client.model.CamtA0100103;
import io.swagger.client.model.CamtA0100202;
import io.swagger.client.model.ErrorCode;
import io.swagger.client.model.PaymentReason1Code;
import io.swagger.client.model.PaymentStatus3;
import io.swagger.client.model.PaymentStatusType2Choice;
import io.swagger.client.model.StatusDetails2;
import io.swagger.client.model.TransactionIndividualStatus4Code;
import io.swagger.client.model.UpdatePaymentStatusRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * API tests for StatusConfirmationsApi
 */
//@Ignore
public class StatusConfirmationsApiTest {

    private final StatusConfirmationsApi api = new StatusConfirmationsApi();
    
    @Test
    public void statusConfirmationsPostTest() throws ApiException, NoSuchAlgorithmException, IOException {
    	String laUApplicationID = UtilHelper.getInstance().mymap.get("laUApplicationID");
        String laUVersion = UtilHelper.getInstance().mymap.get("laUVersion");
        String laUCallTime = UtilHelper.getInstance().mymap.get("laUCallTime");
        String laURequestNonce = UtilHelper.getInstance().mymap.get("laURequestNonce");
        String laUResponseNonce = UtilHelper.getInstance().mymap.get("laUResponseNonce");
        String laUSigned = UtilHelper.getInstance().mymap.get("laUSigned");
        String laUSignature = UtilHelper.getInstance().mymap.get("laUSignature");
        String xApi = UtilHelper.getInstance().mymap.get("xApi");
        String xRecord= UtilHelper.getInstance().mymap.get("StatusConfirmationsApiTest.xRecord");
        boolean signnature_required = Boolean.parseBoolean(UtilHelper.getInstance().mymap.get("StatusConfirmationsApiTest.signatureRequired"));
        URI uri = null;


        // Provide URL of gpi Connector instance
        //api.getApiClient().setBasePath("https://WIN-SSV7RS8364L:8443/swift.apitracker/v1");
        api.getApiClient().setBasePath("https://virtserver.swaggerhub.com/Tracker-API/gpi-api/1.2.36");   

        try {
            uri = new URI(api.getApiClient().getBasePath());
        } catch (URISyntaxException ex) {
            Logger.getLogger("Tracker API").log(Level.SEVERE,
                    null, ex);
        }

        CamtA0100103 requestBody = new CamtA0100103();
        
        requestBody.setUpdatePaymentStatusRequest(new UpdatePaymentStatusRequest());
        requestBody.getUpdatePaymentStatusRequest().setFrom("CCLABEB0");
        requestBody.getUpdatePaymentStatusRequest().setBusinessService("121");
        //requestBody.getUpdatePaymentStatusRequest().setTransactionIdentification("97ed4827-7b6f-4491-a06f-b548d5a7512d");
        requestBody.getUpdatePaymentStatusRequest().setInstructionIdentification("CCLABEB0ACSPG000");
        requestBody.getUpdatePaymentStatusRequest().setPaymentStatus(new PaymentStatusType2Choice());
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().setDetailedStatus(new StatusDetails2());
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().setOriginator("CCLABEB0");
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().setConfirmedAmount(
                new ActiveOrHistoricCurrencyAndAmount());
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().getConfirmedAmount().setAmount("1000.");
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().getConfirmedAmount().setCurrency("USD");
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().setTransactionStatus(
                new PaymentStatus3());
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().getTransactionStatus().setStatus(
                TransactionIndividualStatus4Code.ACSC);
        requestBody.getUpdatePaymentStatusRequest().getPaymentStatus().getDetailedStatus().getTransactionStatus().setReason(
                PaymentReason1Code.G000);


        // Print the JSON structure constructed
        System.out.println(api.getApiClient().getJSON().serialize(requestBody));
        
       
        // TODO: Set always to 'true' and provide gpi Connector's certificate 
        // for a production environment
        api.getApiClient().setVerifyingSsl(true);
        //api.getApiClient().setSslCaCert(sslCaCert);
        //api.getApiClient().setKeyManagers(managers);

        // Call API
    
            ApiResponse<CamtA0100202> response
                    = api.statusConfirmationsPostWithHttpInfo(laUApplicationID,
                            laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature,signnature_required,xApi,requestBody,xRecord);
            // Print response
            CamtA0100202 responseBody = response.getData();
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
