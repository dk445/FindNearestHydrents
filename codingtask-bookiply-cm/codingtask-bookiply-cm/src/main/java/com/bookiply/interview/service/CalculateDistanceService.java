package com.bookiply.interview.service;

import com.bookiply.interview.dto.ResponseDto;
import com.bookiply.interview.error.InvalidInputException;

/**
 * This Interface contains the method findNearestHydrants
 * that is responsible for returning the nearest
 * n (number of trucks) hydrants from the fire spot. *
 */
public interface CalculateDistanceService {

    /**
     * This method will return the Dto that contains total
     * distance of fire hoses required and the list of
     * hydrants by processing the location of fire spot
     * and the number truck.
     * @param point_x
     * @param point_y
     * @param truck
     * @return
     * @throws InvalidInputException
     */
    ResponseDto findNearestHydrants(double point_x, double point_y, int truck) throws InvalidInputException;

}
