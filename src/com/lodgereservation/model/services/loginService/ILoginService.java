/**
 * @author Mike.Prasad
 * @author Lauren.OConnor
 */
package com.lodgereservation.model.services.loginService;


import com.lodgereservation.model.domain.ReservationComposite;

public interface ILoginService {

    boolean authenticateUser(ReservationComposite composite, String password);
    boolean findUser(ReservationComposite composite);

}
