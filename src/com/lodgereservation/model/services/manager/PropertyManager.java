package com.lodgereservation.model.services.manager;

import com.lodgereservation.model.business.exception.PropertyFileNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties;

    /**
     * Load the properties file to make its contents available for classes in the model layer.
     *
     * @param propertyFileLocation              the location path name of the properties file
     * @throws PropertyFileNotFoundException    //todo explain
     */
    public static void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {
        properties = new Properties();
        FileInputStream sf = null;

        try {
            sf = new FileInputStream(propertyFileLocation);

        } catch (FileNotFoundException fnfe) {
            System.out.println("Property file not found: " + propertyFileLocation);
            throw new PropertyFileNotFoundException("Property file not found.", fnfe);

        } catch (IOException ioe) {
            System.out.println("IOException while loading Properties file.");
            throw new PropertyFileNotFoundException("IOException while loading Properties file.", ioe);

        } catch (Exception e) {
            System.out.println("Exception while loading Properties file.");
            throw new PropertyFileNotFoundException("Exception while loading Properties file.", e);

        } finally {
            if (sf != null) {
                try {
                    sf.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
