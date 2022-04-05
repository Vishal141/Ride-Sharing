package com.faiz.ride.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.List;

public final class TotalRidesDetails {
    private static TotalRidesDetails totalRidesDetails;
    @Getter @Setter
    private HashMap<String, RideStatsDetails> users = new HashMap<>();

    private TotalRidesDetails() {
    }

    public static TotalRidesDetails getInstance(){
        if(totalRidesDetails==null){
            totalRidesDetails = new TotalRidesDetails();
        }
        return totalRidesDetails;
    }
}
