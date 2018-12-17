package org.framework.adib.selendroidTestApp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class Config {
    static Properties CONFIG = new Properties();

    /**
     * Method to initialize configuration.
     *
     */

    /// testing
    public static Properties initializeConfig() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            CONFIG.load(fis);

            String platformProperty = System.getProperty("platform");

            String platform = (platformProperty != null) ? platformProperty : "Test_Pixel_2";
            System.out.println("Hello");
            /*
             * Loading the properties from Sessions.yml file. Note: If a config parameter is
             * not provided then default parameter will be picked as Test_Pixel_2
             */
            Map<String, String> additonlaConfig = parseYaml("src/main/resources/Sessions.yml", platform);
            loadDeviceProperty(additonlaConfig);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return CONFIG;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> parseYaml(String filename, String parameter) throws IOException {
        FileInputStream fis = null;
        Map<String, Object> platforms = null;
        Map<String, String> configs = null;
        System.out.println("Hello123");
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/" + filename);
            platforms = (Map<String, Object>) new Yaml().load(fis);
            for (Entry<String, Object> key : platforms.entrySet()) {
                if (key.getKey().equalsIgnoreCase(parameter)) {
                    configs = (Map<String, String>) key.getValue();
                    break;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return configs;
    }

    /**
     * Method to load properties specific for the emulator/device
     *
     * @param: properties
     *             the map of emulator properties read from the Sessions.yml file
     *
     */

    public static void loadDeviceProperty(Map<String, String> properties) {
    	 System.out.println("Hello456");
        for (Entry<String, String> config : properties.entrySet()) {
            CONFIG.setProperty(config.getKey(), config.getValue());
        }
    }

}
