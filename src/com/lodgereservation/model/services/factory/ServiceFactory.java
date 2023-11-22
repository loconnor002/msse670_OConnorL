package com.lodgereservation.model.services.factory;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.services.IService;

public class ServiceFactory {

    // part of Singleton Pattern
    private ServiceFactory() {}

    //Part of Singleton Pattern
    private static ServiceFactory serviceFactory = new ServiceFactory();

    //Part of Singleton Pattern
    public static ServiceFactory getInstance() {
        return serviceFactory;
    }


    /**
     * Get a service object.
     *
     * @param serviceName   the name of a service
     * @return              the service object which matches serviceName
     */
    public IService getService(String serviceName) throws ServiceLoadException {
        try {
            // c is a parameterizable class of any type ('?' is a wildcard).
            Class<?> c = Class.forName(getImplName(serviceName));

            return (IService) c.newInstance();
        }
        catch (Exception e) {
            serviceName += " not loaded";
            throw new ServiceLoadException(serviceName, e);
        }
    }

    private String getImplName(String serviceName) throws Exception {

        java.util.Properties props = new java.util.Properties();
        String propertyFileLocation = System.getProperty("prop_location"); //todo System.getProperty
        //String propertyFileLocation = "C:\\Users\\lo\\IdeaProjects\\LodgeReservation\\config\\application.properties";
        //System.out.println("Property passed with -D:" + System.getProperty("prop_location"));

        java.io.FileInputStream fis = new java.io.FileInputStream(propertyFileLocation);

        props.load(fis);
        fis.close();
        return props.getProperty(serviceName);
    }
}
