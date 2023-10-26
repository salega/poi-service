package com.maps.poiservice.route;

import com.maps.poiservice.model.route.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldMapTest {

    private final WorldMap worldMap = new WorldMap(Route.class);

    @Test
    public void shouldThrowExceptionForInvalidPlace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> worldMap.getPlaceForName("invalid place name"));
    }
}
