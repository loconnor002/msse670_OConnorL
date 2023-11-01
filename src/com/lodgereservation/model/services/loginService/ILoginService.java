/**
 * @author Mike.Prasad
 * @author Lauren.OConnor
 */
package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.LodgeGuest;

public interface ILoginService {

    String findUser();
    boolean authenticateLodgeGuest(LodgeGuest guest);

}
