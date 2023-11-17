package com.lodgereservation.model.business.manager;

import com.lodgereservation.model.business.exception.PropertyFileNotFoundException;
import com.lodgereservation.model.services.manager.PropertyManager;

public class ManagerSuperType {

    // static initializer block
    static {
        try {
            ManagerSuperType.loadProperties();

        } catch (PropertyFileNotFoundException pfnfe) {
            pfnfe.printStackTrace();
            System.out.println("Application properties failed to load - Application exiting...");
            System.exit(1);
        }
    }

    private static void loadProperties() throws PropertyFileNotFoundException {

        String propertyFileLocation = System.getProperty("prop_location");
        if (propertyFileLocation != null) {
            PropertyManager.loadProperties(propertyFileLocation);
        }
        else {
            System.out.println("Property file location not set. Passed value is: " + propertyFileLocation);
            throw new PropertyFileNotFoundException("Property file location not set", null);
        }
    }
}
