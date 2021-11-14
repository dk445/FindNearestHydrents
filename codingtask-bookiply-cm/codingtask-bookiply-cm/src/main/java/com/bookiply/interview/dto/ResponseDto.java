package com.bookiply.interview.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This Dto is responsible for storing the response
 * that will be return by api.
 * This response contains the total firehose length
 * that will be required and the list of hydrants (objectId)
 * and its distance from fire spot.
 */
@Getter @Setter
@Component
public class ResponseDto {
    public double totalFirehosesLength;
    public List<HydrantsResult> hydrants;
}

