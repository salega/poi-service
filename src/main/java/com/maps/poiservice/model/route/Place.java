package com.maps.poiservice.model.route;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Place {

    private String name;

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "(name=" + name + ")";
    }
}