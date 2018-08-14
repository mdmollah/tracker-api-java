package io.swagger.client.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Properties;

public class UtilHelper {
	
	 Properties properties;
	 public HashMap<String,String> mymap;
	
	private static UtilHelper single_instance = null;
	
	private UtilHelper()
	{
		
	}
	public static UtilHelper getInstance() throws NoSuchAlgorithmException, IOException
	{
		if (single_instance == null) {
			single_instance = new UtilHelper();
			single_instance.loadProperties();
		}	
        return single_instance;
	}
	
	public void loadProperties() throws IOException,NoSuchAlgorithmException
	{
		String path= "main.properties";
		properties = new Properties();
		FileInputStream file;
		file= new FileInputStream("./"+path);
		properties.load(file);
		file.close();
		
		mymap = new HashMap<String, String>();
		
		for (String key : properties.stringPropertyNames()) {
		    String value = properties.getProperty(key);
		    mymap.put(key, value);
		    //System.out.println(key + " " + value);
		}
	}
	
}
