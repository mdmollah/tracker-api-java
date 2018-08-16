package io.swagger.client.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class UtilHelper {

	Properties properties;
	public HashMap<String, String> mymap;

	private static UtilHelper single_instance = null;

	private UtilHelper() {

	}

	public static UtilHelper getInstance() throws NoSuchAlgorithmException, IOException {
		if (single_instance == null) {
			single_instance = new UtilHelper();
			single_instance.loadProperties();
		}
		return single_instance;
	}

	public void loadProperties() throws IOException, NoSuchAlgorithmException {
		String filename = "main.properties";
		InputStream st = this.getClass().getResourceAsStream("/" + filename);
		properties = new Properties();
		properties.load(st);
		mymap = new HashMap<String, String>();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			mymap.put(key, value);
			// System.out.println(key + " " + value);
		}
	}

	public ProcessingReport schemaValidation(String goodResponse)
			throws IOException, ProcessingException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		URL url = this.getClass().getResource("/SWIFT-API_gpi-api_2.0.2_swagger.json");
		final JsonNode fstabSchema = mapper.readTree(new File(url.toURI()));
		final JsonNode good = mapper.readTree(goodResponse);
		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		final JsonSchema schema = factory.getJsonSchema(fstabSchema);
		ProcessingReport report;
		report = schema.validate(good);
		System.out.println(report.isSuccess());
		return report;
	}

	public void getResponseSchemaFromSpec(String apiPath)
			throws JsonProcessingException, IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		URL url = this.getClass().getResource("/SWIFT-API_gpi-api_2.0.2_swagger.json");
		final JsonNode root = mapper.readTree(new File(url.toURI()));
		JsonNode update = root.path("definitions").path("UpdatePaymentStatusResponse");
		System.out.println(update);
		JsonNode nameNode = root.path("paths").path(apiPath).path("post").path("responses").path("200").path("schema");
		if (nameNode.isMissingNode()) {
			// if "name" node is missing
		} else {
			String response = nameNode.toString();
			System.out.println(response.substring(response.indexOf("camt"), response.length() - 2));
		}
	}

	public static void main(String s[])
			throws NoSuchAlgorithmException, IOException, ProcessingException, URISyntaxException {
		// UtilHelper.getInstance().getResponseSchemaFromSpec("/status_confirmations");
		UtilHelper.getInstance().getResponseSchemaFromSpec("/status_confirmations");
	}

}
