package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findParking(List<ParkingSpot> parkingSpotList);
}
