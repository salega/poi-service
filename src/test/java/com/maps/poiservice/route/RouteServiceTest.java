package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.route.strategy.RouteStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    private static final String START_PLACE_NAME = "someStartPlaceName";
    private static final String END_PLACE_NAME = "someEndPlaceName";

    @Mock
    WorldMap worldMap;

    @Mock
    Map<MeanOfTransport, RouteStrategy> strategies;

    @Mock
    RouteStrategy carStrategy;

    @InjectMocks
    RouteService routeService;

    @Test
    public void shouldBuildShortestRoute() {
        // given
        Place startPlace = new Place(START_PLACE_NAME);
        Place endPlace = new Place(END_PLACE_NAME);
        Set<Place> expectedShortestRoute = Set.of(startPlace, endPlace);
        when(worldMap.getPlaceForName(START_PLACE_NAME)).thenReturn(startPlace);
        when(worldMap.getPlaceForName(END_PLACE_NAME)).thenReturn(endPlace);
        when(strategies.get(MeanOfTransport.CAR)).thenReturn(carStrategy);
        when(carStrategy.buildShortestRoute(worldMap, startPlace, endPlace)).thenReturn(expectedShortestRoute);

        // when
        Set<Place> actualShortestRoute = routeService.getShortestRoute(START_PLACE_NAME, END_PLACE_NAME, MeanOfTransport.CAR);

        // then
        assertEquals(expectedShortestRoute, actualShortestRoute);
    }
}
