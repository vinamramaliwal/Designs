package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;

import java.util.List;

public class FourWheelerParkingManager extends ParkingManager{
    List<ParkingSpot> parkingSpotList;

    public FourWheelerParkingManager(List<ParkingSpot> parkingSpotList){
        super(parkingSpotList);
        this.parkingSpotList=parkingSpotList;
        costComputationStrategy= new CostComputationStrategy2();
        parkingStrategy = new ParkingStrategy2();
    }
    public FourWheelerParkingManager(){}

}
