package ParkingLotLLD.Entities;

import lombok.Data;

import java.time.Instant;

@Data
public class Ticket {

    int id;
    Instant entryTime;
    Vehicle vehicle;
    ParkingSpot parkingSpot;
    Instant endTime;
    int totalTicketPrice;
}
