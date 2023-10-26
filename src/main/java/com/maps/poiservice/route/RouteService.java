package com.maps.poiservice.route;

import com.maps.poiservice.model.route.MeanOfTransport;
import com.maps.poiservice.model.route.Place;
import com.maps.poiservice.model.route.Route;
import com.maps.poiservice.route.strategy.BikeRouteStrategy;
import com.maps.poiservice.route.strategy.CarRouteStrategy;
import com.maps.poiservice.route.strategy.FootRouteStrategy;
import com.maps.poiservice.route.strategy.RouteStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RouteService {

    // normally that would be initialized on startup from some Graph DB
    private final WorldMap worldMap;
    private final Map<MeanOfTransport, RouteStrategy> strategies;

    public RouteService(WorldMap worldMap, Map<MeanOfTransport, RouteStrategy> strategies) {
        this.worldMap = worldMap;
        this.strategies = strategies;
    }

    public RouteService() {
        this.worldMap = new WorldMap(Route.class);
        this.strategies = initializeDefaultStrategies();
    }

    public Set<Place> getShortestRoute(String startPlaceName, String endPlaceName, MeanOfTransport meanOfTransport) {
        Place startPlace = worldMap.getPlaceForName(startPlaceName);
        Place endPlace = worldMap.getPlaceForName(endPlaceName);
        RouteStrategy routeStrategy = strategies.get(meanOfTransport);
        return routeStrategy.buildShortestRoute(worldMap, startPlace, endPlace);
    }

    private Map<MeanOfTransport, RouteStrategy> initializeDefaultStrategies() {
        return new HashMap<>() {{
            put(MeanOfTransport.FOOT, new FootRouteStrategy());
            put(MeanOfTransport.BIKE, new BikeRouteStrategy());
            put(MeanOfTransport.CAR, new CarRouteStrategy());
        }};
    }
}
