package factorial;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	// To read values from config.properties file
	public String getProperty(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream f = new FileInputStream(
				"C:\\Users\\vidhu\\eclipse-workspace\\FactorialProject\\src\\test\\resources\\config.properties");
		prop.load(f);
		return prop.getProperty(key);
	}

}
