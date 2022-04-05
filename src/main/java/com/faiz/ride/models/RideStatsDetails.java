package com.faiz.ride.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RideStatsDetails {
    private Integer offered;
    private Integer taken;

    public RideStatsDetails(Integer offered, Integer taken) {
        this.offered = offered;
        this.taken = taken;
    }

}
