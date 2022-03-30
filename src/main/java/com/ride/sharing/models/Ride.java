package com.ride.sharing.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    private String id;
    private String source;
    private String destination;
    private int availableSeats;
    @ManyToOne
    private User owner;
    @OneToOne
    private User takenBy;
    @OneToOne
    private Vehicle vehicle;
    private boolean taken;
}
