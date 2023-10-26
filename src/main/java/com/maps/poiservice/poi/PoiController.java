package com.maps.poiservice.poi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.poi.PointOfInterestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Validated
@RequestMapping("/points-of-interest")
public class PoiController {

    private final Logger logger = LoggerFactory.getLogger(PoiController.class);

    private final PoiService poiService;

    @Autowired
    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }

    // validate input parameters and return 400 if invalid
    @GetMapping
    public Set<PointOfInterest> getPois(@RequestParam double latitude,
                                        @RequestParam double longitude,
                                        @RequestParam(required = false) PointOfInterestType poiType)
            throws JsonProcessingException {
        logger.info("Called getPois endpoint for latitude={}, longitude={}, poiType={}", latitude, longitude, poiType);
        Set<PointOfInterest> pois = poiService.getPois(latitude, longitude, poiType);
        logger.info("Found the following points of interest: {}", pois);

        return pois;
    }
}
