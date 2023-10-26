package com.maps.poiservice.zone;

import com.maps.poiservice.exception.NoZoneFoundException;
import com.maps.poiservice.model.zone.Zone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZoneServiceTest {

    private final ZoneService zoneService = new ZoneService();

    @Test
    public void shouldReturnZoneForValidCoordinates() {
        // given
        double latitude = 49.0;
        double longitude = 3.0;

        // when
        Zone zone = zoneService.getZone(latitude, longitude);

        // then
        assertEquals(Zone.PARIS, zone);
    }

    @Test
    public void shouldThrowExceptionForInvalidCoordinates() {
        // given
        double latitude = 149.0;
        double longitude = 3.0;

        // when
        Assertions.assertThrows(NoZoneFoundException.class, () -> {
            zoneService.getZone(latitude, longitude);
        });
    }
}
