package com.maps.poiservice.model.route;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Set;

public class Route extends DefaultWeightedEdge {

    private final Set<MeanOfTransport> supportedMeansOfTransport;

    public Route(Set<MeanOfTransport> supportedMeansOfTransport) {
        this.supportedMeansOfTransport = supportedMeansOfTransport;
    }

    public Set<MeanOfTransport> getSupportedMeansOfTransport() {
        return supportedMeansOfTransport;
    }
}
