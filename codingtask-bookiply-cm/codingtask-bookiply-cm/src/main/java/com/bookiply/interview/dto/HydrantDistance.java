package com.bookiply.interview.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This dto will store the specific Hydrant data along
 * with the distance to fire spot.
 */
@Getter @Setter
public class HydrantDistance {
    private HydrantsDataDto hydrantsDataDto;
    private Double distanceToFire;

    public HydrantDistance(HydrantsDataDto hydrantsDataDto, Double distanceToFire) {
        this.hydrantsDataDto = hydrantsDataDto;
        this.distanceToFire = distanceToFire;
    }
}
