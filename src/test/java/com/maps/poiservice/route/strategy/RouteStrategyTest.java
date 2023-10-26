package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.route.DijkstraAlgorithm;
import com.maps.poiservice.route.WorldMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RouteStrategyTest {

    @Mock
    WorldMap worldMap;

    @Mock
    Place startPlace;

    @Mock
    Place endPlace;

    @Test
    public void shouldBuildShortestRoute() {
        // given
        Set<Place> expectedShortestRoute = Set.of(new Place("a"), new Place("b"));
        try (MockedStatic<DijkstraAlgorithm> dijkstraAlgorithmMocked = Mockito.mockStatic(DijkstraAlgorithm.class)) {
            dijkstraAlgorithmMocked.when(() -> DijkstraAlgorithm.findShortestPath(worldMap, startPlace, endPlace, MeanOfTransport.BIKE))
                    .thenReturn(expectedShortestRoute);

            // when
            BikeRouteStrategy bikeRouteStrategy = new BikeRouteStrategy();
            Set<Place> actualShortestRoute = bikeRouteStrategy.buildShortestRoute(worldMap, startPlace, endPlace);

            // then
            assertEquals(expectedShortestRoute, actualShortestRoute);
        }
    }
}
