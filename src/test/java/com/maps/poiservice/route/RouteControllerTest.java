package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteControllerTest {

    private static final String START_PLACE_NAME = "someStartPlaceName";
    private static final String END_PLACE_NAME = "someEndPlaceName";
    private static final MeanOfTransport MEAN_OF_TRANSPORT = MeanOfTransport.CAR;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @Test
    public void shouldReturnShortestRoute() {
        // given
        Place startPlace = new Place(START_PLACE_NAME);
        Place endPlace = new Place(END_PLACE_NAME);
        Set<Place> expectedShortestRoute = Set.of(startPlace, endPlace);
        when(routeService.getShortestRoute(START_PLACE_NAME, END_PLACE_NAME, MEAN_OF_TRANSPORT)).thenReturn(expectedShortestRoute);

        // when
        ResponseEntity<Set<Place>> actualShortestRoute = routeController.getShortestRoute(START_PLACE_NAME, END_PLACE_NAME, MEAN_OF_TRANSPORT);

        // then
        assertEquals(HttpStatusCode.valueOf(200), actualShortestRoute.getStatusCode());
        assertEquals(expectedShortestRoute, actualShortestRoute.getBody());
    }

    @Test
    public void shouldReturnErrorAndNoRoute() {
        // given
        Set<Place> expectedShortestRoute = Collections.emptySet();
        when(routeService.getShortestRoute(START_PLACE_NAME, END_PLACE_NAME, MEAN_OF_TRANSPORT)).thenReturn(expectedShortestRoute);

        // when
        ResponseEntity<Set<Place>> shortestRoute = routeController.getShortestRoute(START_PLACE_NAME, END_PLACE_NAME, MEAN_OF_TRANSPORT);

        // then
        assertEquals(HttpStatusCode.valueOf(404), shortestRoute.getStatusCode());
        assertNull(shortestRoute.getBody());
    }
}
