package com.maps.poiservice.model.poi;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PointOfInterest {
    private String name;
    private PointOfInterestType type;

    public PointOfInterest(String name, PointOfInterestType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PointOfInterestType getType() {
        return type;
    }

    public void setType(PointOfInterestType type) {
        this.type = type;
    }

    public String toString() {
        return "(name=" + name + ", type=" + type + ")";
    }
}