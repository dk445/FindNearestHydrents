package com.bookiply.interview.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Dto is responsible for storing the mandatory
 * fields from external data api response.
 * This data will be evaluated against the fire spot
 * location given by user
 */
@NoArgsConstructor
@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HydrantsDataDto {

    private String objectid;
    private String unitid;
    private String point_x;
    private String point_y;

}
