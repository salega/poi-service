package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.model.route.Route;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.Set;

import static com.maps.poiservice.model.route.MeanOfTransport.*;

public class WorldMap extends DirectedWeightedMultigraph<Place, Route> {

    public static final Place PARIS = new Place("Paris");
    public static final Place MADRID = new Place("Madrid");
    public static final Place BERLIN = new Place("Berlin");
    public static final Place WARSAW = new Place("Warsaw");
    public static final Place VILNIUS = new Place("Vilnius");
    public static final Place BUDAPEST = new Place("Budapest");

    public WorldMap(Class edgeClass) {
        super(edgeClass);
        initWorldMap();
    }

    public Place getPlaceForName(String placeName) {
        return switch (placeName) {
            case "Paris" -> PARIS;
            case "Madrid" -> MADRID;
            case "Berlin" -> BERLIN;
            case "Warsaw" -> WARSAW;
            case "Vilnius" -> VILNIUS;
            case "Budapest" -> BUDAPEST;
            default -> throw new IllegalArgumentException("No place found for placeName=" + placeName);
        };
    }

    private void initWorldMap() {
        addPlaces();
        addRoutes();
    }

    private void addPlaces() {
        addVertex(PARIS);
        addVertex(MADRID);
        addVertex(BERLIN);
        addVertex(WARSAW);
        addVertex(VILNIUS);
        addVertex(BUDAPEST);
    }

    private void addRoutes() {
        addOneWayRoute(PARIS, MADRID, Set.of(BIKE, FOOT), 7);
        addTwoWayRoute(PARIS, BERLIN, Set.of(CAR, BIKE, FOOT), 5);
        addTwoWayRoute(BERLIN, MADRID, Set.of(CAR, FOOT), 6);
        addTwoWayRoute(PARIS, WARSAW, Set.of(CAR, BIKE, FOOT), 11);
        addOneWayRoute(BERLIN, WARSAW, Set.of(CAR, BIKE, FOOT), 3);
        addOneWayRoute(VILNIUS, PARIS, Set.of(CAR), 15);
        addTwoWayRoute(BERLIN, VILNIUS, Set.of(BIKE, FOOT), 9);
        addOneWayRoute(WARSAW, VILNIUS, Set.of(BIKE, FOOT), 5);
        addTwoWayRoute(BERLIN, BUDAPEST, Set.of(CAR, BIKE), 6);
        addOneWayRoute(BUDAPEST, MADRID, Set.of(CAR, BIKE), 15);
        addOneWayRoute(WARSAW, BUDAPEST, Set.of(CAR, BIKE), 4);
        addTwoWayRoute(VILNIUS, BUDAPEST, Set.of(CAR, BIKE, FOOT), 8);
    }

    private void addOneWayRoute(Place source, Place target, Set<MeanOfTransport> supportedMeansOfTransport, double weight) {
        Route route = new Route(supportedMeansOfTransport);
        addEdge(source, target, route);
        setEdgeWeight(route, weight);
    }

    private void addTwoWayRoute(Place source, Place target, Set<MeanOfTransport> supportedMeansOfTransport, double weight) {
        addOneWayRoute(source, target, supportedMeansOfTransport, weight);
        addOneWayRoute(target, source, supportedMeansOfTransport, weight);
    }
}
