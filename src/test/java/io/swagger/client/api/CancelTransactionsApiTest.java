package io.swagger.client.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import io.swagger.client.ApiException;
import java.text.SimpleDateFormat;


public class CancelTransactionsApiTest {
	
	private final StatusConfirmationsApi api = new StatusConfirmationsApi();
    private final String ApplAPIKey = UUID.randomUUID().toString();
    private final String RBACRole = "[FullViewer/Scope/cclabeb0]";
    private static final String CRLF = "\r\n";
    private static final String LAU_VERSION = "1.0";
    private static final String LAU_KEY = "Abcd1234Abcd1234Abcd1234Abcd1234";
    
    @Test
    public void CancelTransactionPostTest() throws ApiException {
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
        api.getApiClient().setBasePath("https://sandbox.swiftlab-api-developer.com/swift-apitracker-pilot/v2/");
        
        try {
            uri = new URI(api.getApiClient().getBasePath());
        } catch (URISyntaxException ex) {
            Logger.getLogger("Tracker API").log(Level.SEVERE,
                    null, ex);
        }
        
    }
    
    private final SimpleDateFormat simpleDateFormat
    = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private String getDateTimeInZulu() {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Zulu"));
        return simpleDateFormat.format(new Date());

    }
}
