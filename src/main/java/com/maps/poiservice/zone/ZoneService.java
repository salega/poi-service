package com.maps.poiservice.zone;

import com.maps.poiservice.exception.NoZoneFoundException;
import com.maps.poiservice.model.zone.Zone;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ZoneService {

    public Zone getZone(double latitude, double longitude) {
        Zone[] allZones = Zone.values();
        return Arrays.stream(allZones)
                .filter(zone -> isWithinZone(zone, latitude, longitude))
                .findFirst()
                .orElseThrow(NoZoneFoundException::new);
        // if on the border of a zone => search 2 or even 4 zones
    }

    private boolean isWithinZone(Zone zone, double latitude, double longitude) {
        return latitude > zone.getLatitudeBegin() && latitude < zone.getLatitudeEnd()
                && longitude > zone.getLongitudeBegin() && longitude < zone.getLongitudeEnd();
    }
}
