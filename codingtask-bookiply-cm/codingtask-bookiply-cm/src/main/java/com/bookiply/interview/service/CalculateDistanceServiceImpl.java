package com.bookiply.interview.service;

import com.bookiply.interview.dto.HydrantDistance;
import com.bookiply.interview.dto.HydrantsDataDto;
import com.bookiply.interview.dto.HydrantsResult;
import com.bookiply.interview.dto.ResponseDto;
import com.bookiply.interview.error.ExternalDataError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class is responsible for following tasks
 * Fetching external data for hydrants
 * Finding the distance of each hydtants from the fire spot
 * Returning the nearest n (number of trucks) hydrants *
 */
@Service
public class CalculateDistanceServiceImpl implements CalculateDistanceService{
    private ArrayList<HydrantsDataDto> hydrantsDataDtos = new ArrayList<>();

    @PostConstruct
    protected void getDataFromExternalSource() throws ExternalDataError {
        this.hydrantsDataDtos = fetchHydrantsData();
    }

    /**
     * This method is responsible for fetching hydrant data
     * from external api.
     * This method will only called once when app will starts.
     * And same data will be used for finding the nearest
     * hydrants.
     * If there is some change in hydrant data then app should must
     * be restarted to get updated hydrants data.
     * @return
     * @throws ExternalDataError
     */
    public ArrayList<HydrantsDataDto> fetchHydrantsData() throws ExternalDataError {
        try {
            URL url = new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            hydrantsDataDtos = new ObjectMapper().readValue(String.valueOf(response), new TypeReference<List<HydrantsDataDto>>() {});

        } catch (Exception e) {
            throw new ExternalDataError("Hydrants Data is not available");
        }
        return hydrantsDataDtos;
    }

    /**
     * This method is responsible for calculating the
     * distance between hydrant and fire-spot using
     * vector mathematics.
     * @param point_x1
     * @param point_x2
     * @param point_y1
     * @param point_y2
     * @return
     */
    protected double calculateDistance(double point_x1, double point_x2, double point_y1, double point_y2){
        return Math.sqrt(Math.pow(point_x2-point_x1,2)+Math.pow(point_y2-point_y1,2));
    }


    /**
     * This method is responsible for returning the list of
     * nearest n (number of truck) hydrants from the fire spot.
     * @param point_x
     * @param point_y
     * @param truck
     * @return
     */
    @Override
    public ResponseDto findNearestHydrants(double point_x, double point_y, int truck){

        /* This PriorityQueue will store the HydrantDistance object in descending
        * order of distance to fire spot, So that the hydrant with max distance
        * will be in the front if the queue.
        */
        PriorityQueue<HydrantDistance> hydrantDistances = new PriorityQueue<>((o1, o2) -> {
            return o2.getDistanceToFire().compareTo(o1.getDistanceToFire());
        });

        /*
        * Calculating distance of each hydrant from fire spot by calling
        * calculateDistance method and also storing them in queue.
        * If the size of queue crosses the number of truck then it will
        * simply remove the front element that is basically a hydrant which
        * has the max distance from the fire spot.
        * It means at any moment the queue can not hold hydrants
        * more than the number of trucks.
        */
        for (HydrantsDataDto hydrantsDataDto : hydrantsDataDtos) {
            double distanceToFire = calculateDistance(point_x,Double.parseDouble(hydrantsDataDto.getPoint_x()),point_y,Double.parseDouble(hydrantsDataDto.getPoint_y()));
            hydrantDistances.add(new HydrantDistance(hydrantsDataDto, distanceToFire));
            if(hydrantDistances.size() > truck){
                hydrantDistances.poll();
            }
        }

        /*
        * Calculating total distance to fire by
        * simply adding distance to fire spot
        * for each hydrant which are present in
        * queue.
        */
        double totalDistance = 0;
        List<HydrantsResult> hydrants = new ArrayList<>();
        while (hydrantDistances.size()>0) {
            HydrantDistance hydrantDistance = hydrantDistances.poll();
            hydrants.add(new HydrantsResult(hydrantDistance.getHydrantsDataDto().getUnitid(), hydrantDistance.getDistanceToFire()));
            totalDistance += hydrantDistance.getDistanceToFire();
        }

        /*
        * Building the response
        */
        ResponseDto responseDto = new ResponseDto();
        Collections.reverse(hydrants);
        responseDto.setHydrants(hydrants);
        responseDto.setTotalFirehosesLength(totalDistance);
        return responseDto;
    }

}
