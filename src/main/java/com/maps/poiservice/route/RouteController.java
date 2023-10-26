package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Validated
@RequestMapping("/route")
public class RouteController {

    private final Logger logger = LoggerFactory.getLogger(RouteController.class);

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // for larger system this operation might be implemented as asynchronous and operationId would be returned
    // client would periodically fetch for that operationId to check if the shortest route has been calculated
    // also: validate input parameters and return 400 if invalid
    @GetMapping
    public ResponseEntity<Set<Place>> getShortestRoute(@RequestParam String startPlaceName,
                                                       @RequestParam String endPlaceName,
                                                       @RequestParam MeanOfTransport meanOfTransport) {
        logger.info("Called getShortestRoute endpoint for startPlaceName={}, endPlaceName={}, meanOfTransport={}", startPlaceName, endPlaceName, meanOfTransport);
        Set<Place> shortestRoute = routeService.getShortestRoute(startPlaceName, endPlaceName, meanOfTransport);
        if (shortestRoute.isEmpty()) {
            logger.info("Could not find the route from {} to {}", startPlaceName, endPlaceName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            logger.info("Found the shortest route: {}", shortestRoute);
            return ResponseEntity.ok(shortestRoute);
        }
    }
}
