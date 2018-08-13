package io.swagger.client.api;

import static java.util.Arrays.asList;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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

import org.junit.Test;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.model.CamtA0400103;
import io.swagger.client.model.CamtA0400203;
import io.swagger.client.model.ErrorCode;
import io.swagger.client.model.GetChangedPaymentTransactionsRequest;

public class GetChangedPaymentTransactionsApi2Test {

	private final GetChangedPaymentTransactionsApi api = new GetChangedPaymentTransactionsApi();
    //private final String ApplAPIKey = UUID.randomUUID().toString();
    private final String ApplAPIKey =  "yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B";
    private final String RBACRole = "[FullViewer/Scope/cclabeb0]";
    private static final String CRLF = "\r\n";
    private static final String LAU_VERSION = "1.0";
    private static final String LAU_KEY = "Abcd1234Abcd1234Abcd1234Abcd1234";
    private static final String X_API= "yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B";
    private static final String X_RECORD= "0";
    /**
     * Get Changed Payment Transactions
     *
     * This API is a delta query to get all payment update information starting from a given date and time. This API allows synchronization of a local database with the tracker database.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getChangedPaymentTransactionsPostTest() throws ApiException {
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
        
        // Calculate LAU (see Tracker API specification in PDF for the algorithm details)
        laUSignature = calculateLAU(laUApplicationID, laUCallTime,
                laURequestNonce, laUSigned, LAU_KEY,
                uri.getPath() + "/get_changed_payment_transactions",
                api.getApiClient().getJSON().serialize(requestBody));

        // TODO: Set always to 'true' and provide gpi Connector's certificate 
        // for a production environment
        api.getApiClient().setVerifyingSsl(true);
        //api.getApiClient().setSslCaCert(sslCaCert);
        //api.getApiClient().setKeyManagers(managers);
        
        
        try {
            ApiResponse<CamtA0400203> response
                    = api.getChangedPaymentTransactionsPostWithHttpInfo(laUApplicationID,
                            laUVersion, laUCallTime, laURequestNonce, laUSigned, laUSignature, requestBody);
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
