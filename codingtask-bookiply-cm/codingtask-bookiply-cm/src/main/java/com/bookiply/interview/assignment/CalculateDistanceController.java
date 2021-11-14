package com.bookiply.interview.assignment;

import com.bookiply.interview.dto.ResponseDto;
import com.bookiply.interview.error.ErrorResponseDto;
import com.bookiply.interview.error.InvalidInputException;
import com.bookiply.interview.service.CalculateDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateDistanceController {

    @Autowired
    CalculateDistanceService calculateDistanceService;

    /**
     * This api handling will take arguments in following format and will
     * return the ResponseDto
     * @param point_x
     * @param point_y
     * @param truck
     * @return
     * @throws InvalidInputException
     */
    @GetMapping("/new-york/nearestHydrants")
    public ResponseDto calculateTotalDistance(@RequestParam double point_x, @RequestParam double point_y, @RequestParam int truck) throws InvalidInputException {
        if(truck <= 0) {
            throw new InvalidInputException("Invalid input");
        }
        return calculateDistanceService.findNearestHydrants(point_x,point_y,truck);
    }

    /**
     * This method will return the status 400 (Bad Request)
     * with message "Invalid input" for the requests which
     * contains the invalid input.
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(InvalidInputException e) {
        return new ErrorResponseDto(e.getMessage());
    }
}
