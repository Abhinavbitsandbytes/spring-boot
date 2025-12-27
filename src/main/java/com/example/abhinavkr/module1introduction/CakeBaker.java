package com.example.abhinavkr.module1introduction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    private final Frosting frosting;
    private final Syrup syrup;

    public CakeBaker(@Qualifier("chocolateFrosting") Frosting frosting,
                     @Qualifier("chocolateSyrup") Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Baking cake with " + frosting.getFrostingType() + " and " + syrup.getSyrupType());
    }
}
