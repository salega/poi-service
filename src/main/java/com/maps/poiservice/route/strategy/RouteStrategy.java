package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.route.DijkstraAlgorithm;
import com.maps.poiservice.route.WorldMap;

import java.util.Set;

public abstract class RouteStrategy {

    public Set<Place> buildShortestRoute(WorldMap worldMap, Place startPlace, Place endPlace) {
        MeanOfTransport supportedMeanOfTransport = getSupportedMeanOfTransport();
        return DijkstraAlgorithm.findShortestPath(worldMap, startPlace, endPlace, supportedMeanOfTransport);
    }

    public abstract MeanOfTransport getSupportedMeanOfTransport();
}
