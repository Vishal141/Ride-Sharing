package com.faiz.ride.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserSelectedRideDetails {

    private int seats;
    private String source;
    private String destination;
    private String name;
    private String preferredVehicle;

    public UserSelectedRideDetails(int seats, String source, String destination, String name, String preferredVehicle) {
        this.seats = seats;
        this.source = source;
        this.destination = destination;
        this.name = name;
        this.preferredVehicle = preferredVehicle;
    }
}
