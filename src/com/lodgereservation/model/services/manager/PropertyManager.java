package com.lodgereservation.model.services.manager;

import com.lodgereservation.model.business.exception.PropertyFileNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties;

    /**
     * Load the properties file to make its contents available for classes anywhere in the model.
     *
     * @param propertyFileLocation              the location path name of the properties file
     * @throws PropertyFileNotFoundException    if
     */
    public static void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {
        properties = new Properties();
        FileInputStream sf = null;

        try {
            sf = new FileInputStream(propertyFileLocation);

        } catch (FileNotFoundException fnfe) {
            System.err.println("Property file not found: " + propertyFileLocation);
            throw new PropertyFileNotFoundException("Property file not found.", fnfe);

        } catch (Exception e) {
            System.err.println("Exception while loading properties file: " + e.getMessage());
            throw new PropertyFileNotFoundException("Exception while loading Properties file.", e);
        } finally {
            if (sf != null) {
                try {
                    sf.close();

                } catch (IOException e) {
                    System.err.println("IOException from PropertyManager: " + e.getMessage());
                }
            }
        }
    } // end loadProperties()


    /**
     * Return the Value for the passed Key
     *  //todo - use in week 6-8?
     * @param key
     * @return
     */
    static public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
