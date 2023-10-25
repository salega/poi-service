package com.maps.poiservice.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.zone.Zone;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maps.poiservice.model.poi.PointOfInterestType.*;

// normally data would be initialized on startup by retrieving the data from some persistent DB
@Component
public class DataInitializer implements CommandLineRunner {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public DataInitializer(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Zone> zones = Arrays.asList(Zone.PARIS, Zone.NEW_YORK);

        for (Zone zone : zones) {
            List<PointOfInterest> pointsOfInterest = generateSamplePointsOfInterest(zone);
            String jsonPointsOfInterest = convertListToJson(pointsOfInterest);
            redisTemplate.opsForValue().set(zone.name(), jsonPointsOfInterest);
        }
    }

    private List<PointOfInterest> generateSamplePointsOfInterest(Zone zone) {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        if (zone == Zone.PARIS) {
            pointsOfInterest.add(new PointOfInterest("Eiffel Tower", TOURIST_ATTRACTION));
            pointsOfInterest.add(new PointOfInterest("Louvre Museum", MUSEUM));
            pointsOfInterest.add(new PointOfInterest("Some fancy restaurant", RESTAURANT));
            pointsOfInterest.add(new PointOfInterest("Notre-Dame Cathedral", TOURIST_ATTRACTION));
        } else if (zone == Zone.NEW_YORK) {
            pointsOfInterest.add(new PointOfInterest("Statue of Liberty", TOURIST_ATTRACTION));
            pointsOfInterest.add(new PointOfInterest("Central Park", PARK));
            pointsOfInterest.add(new PointOfInterest("Times Square", TOURIST_ATTRACTION));
        }

        return pointsOfInterest;
    }

    private String convertListToJson(List<PointOfInterest> pointsOfInterest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(pointsOfInterest);
    }
}