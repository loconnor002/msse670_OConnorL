package com.lodgereservation.model.business.manager;

import com.lodgereservation.model.business.exception.PropertyFileNotFoundException;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.manager.PropertyManager;

public abstract class ManagerSuperType {

    // static initializer block, executes if ManagerSuperType is extended/referenced
    static {
        try {
            ManagerSuperType.loadProperties();

        } catch (PropertyFileNotFoundException pfnfe) {
            System.err.println("Application properties failed to load - Application exiting...");
            System.exit(1);
        }
    }


    /**
     * Perform a specified action (command). A generic method, for use by all clients
     * of ManagerSuperType.
     *
     * @param commandString the name of the action (command) being invoked
     * @param composite     the application-specific domain state
     * @return              true if action was successful, false otherwise
     */
    public abstract boolean performAction(String commandString, Composite composite);


    /**
     * Load the property file into memory to make it available for use by business tier and below.
     *
     * @throws PropertyFileNotFoundException    property file did not load, check run configuration
     */
    static void loadProperties() throws PropertyFileNotFoundException {

        // -Dprop_location=<path reference> added run configurations for Driver and Tests (VM option)
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
