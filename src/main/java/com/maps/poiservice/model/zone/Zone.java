package com.maps.poiservice.model.zone;

// normally the zones would be dynamic and kept in persistent storage
public enum Zone {

    PARIS("Paris", 48.01, 50.05, 2.349014, 3.56),
    NEW_YORK("New York", 43.00, 44.05, -75.0, -71.56);

    private final String name;
    private final double latitudeBegin;
    private final double latitudeEnd;
    private final double longitudeBegin;
    private final double longitudeEnd;

    Zone(String name, double latitudeBegin, double latitudeEnd, double longitudeBegin, double longitudeEnd) {
        this.name = name;
        this.latitudeBegin = latitudeBegin;
        this.latitudeEnd = latitudeEnd;
        this.longitudeBegin = longitudeBegin;
        this.longitudeEnd = longitudeEnd;
    }

    public String getName() {
        return name;
    }

    public double getLatitudeBegin() {
        return latitudeBegin;
    }

    public double getLatitudeEnd() {
        return latitudeEnd;
    }

    public double getLongitudeBegin() {
        return longitudeBegin;
    }

    public double getLongitudeEnd() {
        return longitudeEnd;
    }
}
