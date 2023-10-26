package com.maps.poiservice.model.poi;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PointOfInterest {

    private int id;
    private String name;
    private PointOfInterestType type;

    public PointOfInterest(int id, String name, PointOfInterestType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "(id=" + id + ", name=" + name + ", type=" + type + ")";
    }
}