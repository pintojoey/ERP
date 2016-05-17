/**
 * 
 */
package settings.propertySheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Joey
 *
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.properties.PropertyValueEncryptionUtils;

public class PropertiesFile {
	public static String getProperty(String property) throws IOException{
		  SimplePBEConfig config = new SimplePBEConfig(); 
			//  config.setAlgorithm("PBEWithMD5AndTripleDES");
			//  config.setKeyObtentionIterations(1000);
			  config.setPassword("iiitkota");

			  StandardPBEStringEncryptor encryptor = new org.jasypt.encryption.pbe.StandardPBEStringEncryptor();
			  encryptor.setConfig(config);
			  encryptor.initialize();
			  Properties prop=new Properties();
			  InputStream input = new FileInputStream("config.properties");

					// load a properties file
					prop.load(input);
					String cipher=prop.getProperty(property);
					return PropertyValueEncryptionUtils.decrypt(cipher, encryptor);
					
	}
  public static void main(String[] args) {
	  SimplePBEConfig config = new SimplePBEConfig(); 
	//  config.setAlgorithm("PBEWithMD5AndTripleDES");
	//  config.setKeyObtentionIterations(1000);
	  config.setPassword("iiitkota");

	  StandardPBEStringEncryptor encryptor = new org.jasypt.encryption.pbe.StandardPBEStringEncryptor();
	  encryptor.setConfig(config);
	  encryptor.initialize();
	
	
	

	try {

		OutputStream output = new FileOutputStream("config.properties");
        Properties prop=new Properties();
		// set the properties value
		prop.setProperty("dbserver", PropertyValueEncryptionUtils.encrypt("192.168.0.104", encryptor));
		prop.setProperty("dbuser", PropertyValueEncryptionUtils.encrypt("postgres", encryptor));
		prop.setProperty("dbpassword", PropertyValueEncryptionUtils.encrypt("iiitk_2013", encryptor));
		prop.setProperty("dbport", PropertyValueEncryptionUtils.encrypt("5432", encryptor));
		prop.setProperty("ldap", PropertyValueEncryptionUtils.encrypt("192.168.0.104", encryptor));
		prop.setProperty("ldapport", PropertyValueEncryptionUtils.encrypt("589", encryptor));
		// save properties to project root folder
		prop.store(output, "Configuration File");
        output.close();
	} catch (IOException io) {
		io.printStackTrace();
	
	}
  }
}
