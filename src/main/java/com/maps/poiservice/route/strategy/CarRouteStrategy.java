package com.maps.poiservice.route.strategy;

import com.maps.poiservice.model.route.MeanOfTransport;

public class CarRouteStrategy extends RouteStrategy {

    @Override
    public MeanOfTransport getSupportedMeanOfTransport() {
        return MeanOfTransport.CAR;
    }
}
