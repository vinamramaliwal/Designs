package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.Ticket;

public interface CostComputationStrategy {
    int findCost(Ticket t);
}
