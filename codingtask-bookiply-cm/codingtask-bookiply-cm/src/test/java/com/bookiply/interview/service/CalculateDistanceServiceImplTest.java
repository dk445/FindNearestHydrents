package com.bookiply.interview.service;


import com.bookiply.interview.error.InvalidInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculateDistanceServiceImpl.class)
public class CalculateDistanceServiceImplTest {

    @Autowired
    private CalculateDistanceServiceImpl calculateDistanceService;

    @Test
    public void findNearestHydrantsTest1WithValidInput() throws InvalidInputException {
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getTotalFirehosesLength(),3812.333960280213);
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(2).getUnitid(),"H325222");
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(1).getUnitid(),"H325543");
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(0).getUnitid(),"H325449");
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(2).getDistanceToFire(),2381.7113405820514);
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(1).getDistanceToFire(),1429.208406135789);
        assertEquals(calculateDistanceService.findNearestHydrants(1008424.396, 174042.245, 3).getHydrants().get(0).getDistanceToFire(),1.4142135623730951);
    }

}