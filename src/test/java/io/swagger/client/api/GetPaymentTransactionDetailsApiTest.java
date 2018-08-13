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
import io.swagger.client.model.CamtA0200103;
import io.swagger.client.model.CamtA0200203;
import io.swagger.client.model.ErrorCode;
import io.swagger.client.model.GetPaymentTransactionDetailsRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.Ignore;

import static java.util.Arrays.asList;
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
 * API tests for GetPaymentTransactionDetailsApi
 */
//@Ignore
public class GetPaymentTransactionDetailsApiTest {

    private final GetPaymentTransactionDetailsApi api = new GetPaymentTransactionDetailsApi();
    private final String ApplAPIKey = UUID.randomUUID().toString();
    private final String RBACRole = "[FullViewer/Scope/cclabeb0]";
    private static final String CRLF = "\r\n";
    private static final String LAU_VERSION = "1.0";
    private static final String LAU_KEY = "Abcd1234Abcd1234Abcd1234Abcd1234";
    
    /**
     * Get Payment Transaction Details
     *
     * This API is a payment query to get detailed information regarding a given payment. It requires the UETR to be known.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getPaymentTransactionDetailsPostTest() throws ApiException {

        String laUApplicationID = "001";
        String laUVersion = LAU_VERSION;
        String laUCallTime = getDateTimeInZulu();
        String laURequestNonce = UUID.randomUUID().toString();
        String laUResponseNonce = null;
        String laUSigned = "(ApplAPIKey=" + ApplAPIKey + "),(RBACRole=" + RBACRole + ")";
        String laUSignature = null;
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

        CamtA0200103 requestBody = new CamtA0200103();
        requestBody.setGetPaymentTransactionDetailsRequest(new GetPaymentTransactionDetailsRequest());
        List<String> myInstitution = asList("CCLABEB0");
        requestBody.getGetPaymentTransactionDetailsRequest().setMyInstitution(myInstitution);
        //requestBody.getGetPaymentTransactionDetailsRequest().setTransactionIdentification("a2949d20-53d6-4999-85f5-f4a0dd9adf40");
        
        // Print the JSON structure constructed
        System.out.println(api.getApiClient().getJSON().serialize(requestBody));
        
        // Calculate LAU (see Tracker API specification in PDF for the algorithm details)
        laUSignature = calculateLAU(laUApplicationID, laUCallTime,
                laURequestNonce, laUSigned, LAU_KEY,
                uri.getPath() + "/get_payment_transaction_details",
                api.getApiClient().getJSON().serialize(requestBody));

        // TODO: Set always to 'true' and provide gpi Connector's certificate 
        // for a production environment
        api.getApiClient().setVerifyingSsl(true);
        //api.getApiClient().setSslCaCert(sslCaCert);
        //api.getApiClient().setKeyManagers(managers);

        // Call API
        try {
            ApiResponse<CamtA0200203> response
                    = api.getPaymentTransactionDetailsPostWithHttpInfo(laUApplicationID,
                            laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, requestBody);
            // Print response
            CamtA0200203 responseBody = response.getData();
            System.out.println(api.getApiClient().getJSON().serialize(responseBody));
            
            // Verify LAU
            Map<String, List<String>> headers = response.getHeaders();
            laUApplicationID = headers.get("LAUApplicationID").get(0);
            laUCallTime = headers.get("LAUCallTime").get(0);
            laURequestNonce = headers.get("LAURequestNonce").get(0);
            laUResponseNonce = headers.get("LAUResponseNonce").get(0);
            laUVersion = headers.get("LAUVersion").get(0);
            laUSignature = headers.get("LAUSignature").get(0);
            if (verifyLAU(laUApplicationID, laUCallTime,
                    laURequestNonce, laUResponseNonce, laUVersion, laUSignature, LAU_KEY,
                    api.getApiClient().getJSON().serialize(responseBody)) == false) {
                Logger.getLogger("Tracker API").log(Level.WARNING,
                        "LAU received from gpi Connector cannnot be verified");
            }
        } catch (ApiException ex) {
            Logger.getLogger("Tracker API").log(Level.SEVERE,
                    ex.getResponseBody(), ex);
            ErrorCode errorCode = api.getApiClient().getJSON().deserialize(
                    ex.getResponseBody(), ErrorCode.class);
            if (errorCode != null) {
                System.out.println("Error code:" + errorCode.getStatus().getCode().toString());
            }
        }
   }

    private final SimpleDateFormat simpleDateFormat
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private String getDateTimeInZulu() {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Zulu"));
        return simpleDateFormat.format(new Date());

    }

    private String calculateLAU(String LAUApplicationID, String LAUCallTime,
            String LAURequestNonce, String LAUSigned, String LAUKey, String absPath,
            String requestBody) {

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keyspec = new SecretKeySpec(
                    LAUKey.getBytes(Charset.forName("US-ASCII")), "HmacSHA256");
            mac.init(keyspec);

            StringBuilder sb = new StringBuilder(2048);
            sb.append("LAUApplicationID:").append(LAUApplicationID.trim()).append(CRLF);
            sb.append("LAUCallTime:").append(LAUCallTime.trim()).append(CRLF);
            sb.append("LAURequestNonce:").append(LAURequestNonce.trim()).append(CRLF);
            sb.append("LAUSigned:").append(LAUSigned.trim()).append(CRLF);
            sb.append("LAUVersion:").append(LAU_VERSION.trim()).append(CRLF);
            sb.append(absPath.trim()).append(CRLF);
            sb.append(requestBody);

            byte[] lau = mac.doFinal(sb.toString().getBytes("UTF-8"));
            byte[] lau_to_encode = new byte[16];
            System.arraycopy(lau, 0, lau_to_encode, 0, 16);
            String LAU = DatatypeConverter.printBase64Binary(lau_to_encode);

            return LAU;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException ex) {
            Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private boolean verifyLAU(String LAUApplicationID, String LAUCallTime,
            String LAURequestNonce, String LAUResponseNonce, String LAUVersion,
            String LAUSignature, String LAUKey, String responseBody) {

        boolean result = false;

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keyspec = new SecretKeySpec(
                    LAUKey.getBytes(Charset.forName("US-ASCII")), "HmacSHA256");
            mac.init(keyspec);

            StringBuilder sb = new StringBuilder(2048);
            sb.append("LAUApplicationID:").append(LAUApplicationID.trim()).append(CRLF);
            sb.append("LAUCallTime:").append(LAUCallTime.trim()).append(CRLF);
            sb.append("LAURequestNonce:").append(LAURequestNonce.trim()).append(CRLF);
            sb.append("LAUResponseNonce:").append(LAUResponseNonce.trim()).append(CRLF);
            sb.append("LAUVersion:").append(LAUVersion.trim()).append(CRLF);
            sb.append(responseBody);

            byte[] lau = mac.doFinal(sb.toString().getBytes("UTF-8"));
            byte[] lau_to_encode = new byte[16];
            System.arraycopy(lau, 0, lau_to_encode, 0, 16);
            String LAU = DatatypeConverter.printBase64Binary(lau_to_encode);

            // Compare the signature received from gpi Connector with the one calculated
            result = LAUSignature.equals(LAU);
            return result;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException ex) {
            result = false;
            Logger.getLogger("Tracker API").log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    
}
