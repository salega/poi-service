package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;

public class FootRouteStrategy extends RouteStrategy {

    @Override
    public MeanOfTransport getSupportedMeanOfTransport() {
        return MeanOfTransport.FOOT;
    }
}
