package com.bookiply.interview.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This Dto is used in response where api is
 * returning the list of objectId of hydrant with
 * distance to fire spot from that specific hydrant.
 */
@Getter @Setter
public class HydrantsResult {
    private String unitid;
    private double distanceToFire;

    public HydrantsResult(String unitid, Double distanceToFire) {
        this.unitid = unitid;
        this.distanceToFire = distanceToFire;
    }
}
