package config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    public Properties properties;
    String absPath = System.getProperty("user.dir");
    String relPath = File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";
    String configFilePath = absPath + relPath;

    public Config() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
