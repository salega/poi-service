package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FootRouteStrategyTest {

    @Test
    public void shouldReturnFootAsSupportedMeanOfTransport() {
        // when
        FootRouteStrategy footRouteStrategy = new FootRouteStrategy();

        // then
        assertEquals(MeanOfTransport.FOOT, footRouteStrategy.getSupportedMeanOfTransport());
    }
}
