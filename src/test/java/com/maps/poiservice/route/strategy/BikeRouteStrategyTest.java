package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BikeRouteStrategyTest {

    @Test
    public void shouldReturnBikeAsSupportedMeanOfTransport() {
        // when
        BikeRouteStrategy bikeRouteStrategy = new BikeRouteStrategy();

        // then
        assertEquals(MeanOfTransport.BIKE, bikeRouteStrategy.getSupportedMeanOfTransport());
    }
}
