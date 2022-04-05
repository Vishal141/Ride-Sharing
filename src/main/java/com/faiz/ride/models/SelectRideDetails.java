package com.faiz.ride.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SelectRideDetails extends LocationDetails{

    private String name;

    public SelectRideDetails(String name) {
        this.name = name;
    }
}
