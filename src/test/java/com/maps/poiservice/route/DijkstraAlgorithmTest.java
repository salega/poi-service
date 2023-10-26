package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.model.route.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.maps.poiservice.route.WorldMap.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DijkstraAlgorithmTest {

    private final WorldMap worldMap = new WorldMap(Route.class);

    @Test
    public void shouldBuildShortestRoute() {
        // given
        Set<Place> expectedShortestRoute = new LinkedHashSet<>();
        expectedShortestRoute.add(PARIS);
        expectedShortestRoute.add(BERLIN);
        expectedShortestRoute.add(BUDAPEST);

        // when
        Set<Place> actualShortestRoute =
                DijkstraAlgorithm.findShortestPath(worldMap, PARIS, BUDAPEST, MeanOfTransport.CAR);

        // then
        assertEquals(expectedShortestRoute, actualShortestRoute);
    }

    @Test
    public void shouldBuildShortestRouteWithMoreHopsButLessWeight() {
        // given
        Set<Place> expectedShortestRoute = new LinkedHashSet<>();
        expectedShortestRoute.add(BUDAPEST);
        expectedShortestRoute.add(BERLIN);
        expectedShortestRoute.add(MADRID);

        // when
        Set<Place> actualShortestRoute =
                DijkstraAlgorithm.findShortestPath(worldMap, BUDAPEST, MADRID, MeanOfTransport.CAR);

        // then
        assertEquals(expectedShortestRoute, actualShortestRoute);
    }

    @Test
    public void shouldBuildShortestRouteForFoot() {
        // given
        Set<Place> expectedShortestRoute = new LinkedHashSet<>();
        expectedShortestRoute.add(BUDAPEST);
        expectedShortestRoute.add(VILNIUS);
        expectedShortestRoute.add(BERLIN);
        expectedShortestRoute.add(MADRID);

        // when
        Set<Place> actualShortestRoute =
                DijkstraAlgorithm.findShortestPath(worldMap, BUDAPEST, MADRID, MeanOfTransport.FOOT);

        // then
        assertEquals(expectedShortestRoute, actualShortestRoute);
    }

    @Test
    public void shouldReturnEmptySetForNotConnectedPlaces() {
        // given
        Set<Place> expectedShortestRoute = Collections.emptySet();

        // when
        Set<Place> actualShortestRoute =
                DijkstraAlgorithm.findShortestPath(worldMap, BUDAPEST, new Place("not connected place"), MeanOfTransport.CAR);

        // then
        assertEquals(expectedShortestRoute, actualShortestRoute);
    }
}
