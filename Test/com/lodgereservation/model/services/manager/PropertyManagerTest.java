package com.lodgereservation.model.services.manager;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.reservationService.IReservationService;
import com.lodgereservation.model.services.reservationService.ReservationServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

class PropertyManagerTest {

    private static Properties properties;

    @Before
    public void setUp() throws Exception {

    }
    @Test
    void loadProperties() {
        //todo implement loadPropertiesTest
    }

    @Test
    void getPropertyValue() {
        //todo implement getPropertyValueTest
    }
}
