package com.maps.poiservice.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.zone.Zone;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Set<PointOfInterest> pointsOfInterest = generateSamplePointsOfInterest(zone);
            String jsonPointsOfInterest = convertListToJson(pointsOfInterest);
            redisTemplate.opsForValue().set(zone.name(), jsonPointsOfInterest);
        }
    }

    private Set<PointOfInterest> generateSamplePointsOfInterest(Zone zone) {
        Set<PointOfInterest> pointsOfInterest = new HashSet<>();
        if (zone == Zone.PARIS) {
            pointsOfInterest.add(new PointOfInterest(1, "Eiffel Tower", TOURIST_ATTRACTION));
            pointsOfInterest.add(new PointOfInterest(2, "Louvre Museum", MUSEUM));
            pointsOfInterest.add(new PointOfInterest(3, "Some fancy restaurant", RESTAURANT));
            pointsOfInterest.add(new PointOfInterest(4, "Notre-Dame Cathedral", TOURIST_ATTRACTION));
        } else if (zone == Zone.NEW_YORK) {
            pointsOfInterest.add(new PointOfInterest(5, "Statue of Liberty", TOURIST_ATTRACTION));
            pointsOfInterest.add(new PointOfInterest(6, "Central Park", PARK));
            pointsOfInterest.add(new PointOfInterest(7, "Times Square", TOURIST_ATTRACTION));
        }

        return pointsOfInterest;
    }

    private String convertListToJson(Set<PointOfInterest> pointsOfInterest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(pointsOfInterest);
    }
}