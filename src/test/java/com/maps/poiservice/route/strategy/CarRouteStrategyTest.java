package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarRouteStrategyTest {

    @Test
    public void shouldReturnCarAsSupportedMeanOfTransport() {
        // when
        CarRouteStrategy carRouteStrategy = new CarRouteStrategy();

        // then
        assertEquals(MeanOfTransport.CAR, carRouteStrategy.getSupportedMeanOfTransport());
    }
}
