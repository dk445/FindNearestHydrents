package com.bookiply.interview.assignment;

import com.bookiply.interview.service.CalculateDistanceServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CalculateDistanceControllerTest extends TestCase {
    private static CalculateDistanceServiceImpl calculateDistanceServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CalculateDistanceController calculateDistanceController;


    /**
     * Test cases for valid input
     */

    @Test
    public void calculateDistanceControlTest1WithValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=174042.245&truck=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalFirehosesLength", is(3812.333960280213)))
                .andExpect(jsonPath("hydrants[2].unitid", is("H325222")))
                .andExpect(jsonPath("hydrants[1].unitid", is("H325543")))
                .andExpect(jsonPath("hydrants[0].unitid", is("H325449")))
                .andExpect(jsonPath("hydrants[2].distanceToFire", is(2381.7113405820514)))
                .andExpect(jsonPath("hydrants[1].distanceToFire", is(1429.208406135789)))
                .andExpect(jsonPath("hydrants[0].distanceToFire", is(1.4142135623730951)));
    }


    /**
     * Test cases for Invalid input
     */

    @Test
    public void calculateDistanceControlTest1WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=174042.245&truck=-3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest2WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=174042.245&truck="))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest3WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=174042.245&truck=3rfs"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest4WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=&truck=3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest5WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=abc&truck=3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest6WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=&point_y=&truck=3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest7WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=abc&point_y=&truck=3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateDistanceControlTest8WithInValidInput() throws Exception {
        mockMvc.perform(get("/new-york/nearestHydrants?point_x=1008424.396&point_y=174042.245&truck=0"))
                .andExpect(status().isBadRequest());
    }
}