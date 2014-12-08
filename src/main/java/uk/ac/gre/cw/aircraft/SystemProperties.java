package uk.ac.gre.cw.aircraft;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemProperties {

	private static final Logger logger = Logger.getLogger(SystemProperties.class.getName());

	private static final String SYSTEM_PROPERTIES = "system.properties";
	
	private static Properties prop;

	public static String getValue(String key) {
		if (prop == null)
			getProperties(SYSTEM_PROPERTIES);
        try {
            return prop != null ? prop.getProperty(key).trim() : "";
        } catch (Exception ex) {
            return "";
        }
	}

	public static void getProperties(String propName) {
		prop = new Properties();
		try {
			// load a properties file from class path, inside static method
			prop.load(SystemProperties.class.getClassLoader().getResourceAsStream(
					propName));			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Cannot load properties", ex);
		}
	}
}
