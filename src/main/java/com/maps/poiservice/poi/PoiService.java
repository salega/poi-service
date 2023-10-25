package com.maps.poiservice.poi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.poi.PointOfInterestType;
import com.maps.poiservice.model.zone.Zone;
import com.maps.poiservice.zone.ZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoiService {

    private final Logger logger = LoggerFactory.getLogger(PoiService.class);
    private final ZoneService zoneService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public PoiService(ZoneService zoneService, RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.zoneService = zoneService;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<PointOfInterest> getPois(double latitude, double longitude, PointOfInterestType poiType) throws JsonProcessingException {
        Zone zone = zoneService.getZone(latitude, longitude);
        logger.info("Found zoneId={} for latitude={} and longitude={}", zone, latitude, longitude);
        String poisJson = (String) redisTemplate.opsForValue().get(zone.name());
        List<PointOfInterest> pois = objectMapper.readValue(poisJson, new TypeReference<>() {
        });

        if (poiType == null) {
            return pois;
        } else {
            return filterPoisByType(pois, poiType);
        }
    }

    private List<PointOfInterest> filterPoisByType(List<PointOfInterest> pois, PointOfInterestType poiType) {
        return pois.stream()
                .filter(poi -> poi.getType() == poiType)
                .collect(Collectors.toList());
    }
}
