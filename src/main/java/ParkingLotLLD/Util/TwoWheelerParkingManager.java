package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;

import java.util.List;

public class TwoWheelerParkingManager extends ParkingManager{
    List<ParkingSpot> parkingSpotList;
    public TwoWheelerParkingManager(List<ParkingSpot> parkingSpotList){
        super(parkingSpotList);
        this.parkingSpotList=parkingSpotList;
        costComputationStrategy= new CostComputationStrategy1();
        parkingStrategy = new ParkingStrategy1();
    }
    public TwoWheelerParkingManager(){}
}
