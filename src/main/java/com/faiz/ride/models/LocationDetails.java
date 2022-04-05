package com.faiz.ride.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LocationDetails {
    private String source;
    private String destination;

    public LocationDetails(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

}
