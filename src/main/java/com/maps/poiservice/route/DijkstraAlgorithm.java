package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.model.route.Route;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraAlgorithm {

    public static Set<Place> findShortestPath(WorldMap worldMap, Place startPlace, Place endPlace,
                                              MeanOfTransport meanOfTransport) {
        Map<Place, Double> distanceMap = new HashMap<>();
        Map<Place, Place> predecessorMap = new HashMap<>();
        Set<Place> unvisitedPlaces = new HashSet<>();

        // in the real world example we would not load all vertices at once - that would be done on demand
        // eg. first only some subset of close vertices for the startPlace would be loaded
        for (Place place : worldMap.vertexSet()) {
            distanceMap.put(place, Double.MAX_VALUE);
            predecessorMap.put(place, null);
            unvisitedPlaces.add(place);
        }
        distanceMap.put(startPlace, 0.0);

        while (!unvisitedPlaces.isEmpty()) {
            Place currentPlace = getClosestPlace(unvisitedPlaces, distanceMap);
            unvisitedPlaces.remove(currentPlace);

            for (Route route : getOutgoingRoutesForMeanOfTransport(worldMap, currentPlace, meanOfTransport)) {
                Place neighbour = worldMap.getEdgeTarget(route);
                double distance = distanceMap.get(currentPlace) + worldMap.getEdgeWeight(route);

                if (distance < distanceMap.get(neighbour)) {
                    distanceMap.put(neighbour, distance);
                    predecessorMap.put(neighbour, currentPlace);
                }
            }
        }

        List<Place> shortestPath = new LinkedList<>();
        Place currentPlace = endPlace;
        while (currentPlace != null) {
            shortestPath.add(0, currentPlace);
            currentPlace = predecessorMap.get(currentPlace);
        }

        if (!shortestPath.isEmpty() && shortestPath.get(0).equals(startPlace)) {
            return new LinkedHashSet<>(shortestPath);
        } else {
            return Collections.emptySet(); // Path not found
        }
    }

    private static Set<Route> getOutgoingRoutesForMeanOfTransport(WorldMap worldMap, Place place, MeanOfTransport meanOfTransport) {
        return worldMap.outgoingEdgesOf(place)
                .stream()
                .filter(route -> route.getSupportedMeansOfTransport().contains(meanOfTransport))
                .collect(Collectors.toSet());
    }

    private static Place getClosestPlace(Set<Place> unvisited, Map<Place, Double> distanceMap) {
        Place closestPlace = null;
        double shortestDistance = Double.MAX_VALUE;
        for (Place place : unvisited) {
            double distance = distanceMap.get(place);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestPlace = place;
            }
        }
        return closestPlace;
    }
}
